package com.gradation.lift.feature.badge.badge.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.badge.GetBadgeUseCase
import com.gradation.lift.domain.usecase.badge.GetUserBadgeUseCase
import com.gradation.lift.feature.badge.badge.data.model.AllBadge
import com.gradation.lift.feature.badge.badge.data.model.FilterType
import com.gradation.lift.feature.badge.badge.data.model.SortType
import com.gradation.lift.feature.badge.badge.data.state.BadgeState
import com.gradation.lift.feature.badge.badge.data.state.BadgeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * @property sortType 뱃지 정렬 타입 (기본,이름순,최신순,오래된순)
 * @property filterType 뱃지 필터 타입 (전체,획득뱃지,미획득뱃지)
 * @property onVisibleBadgeDialog 뱃지 팝업, 뱃지 클릭시 나타나는 뱃지 정보 팝업을 의미함
 * @property onVisibleFilterBottomSheet 필터 바텀사트 가시성
 * @property onVisibleSortBottomSheet 정렬 바텀시트 가시성
 * @property selectedBadge 선택된 뱃지, 팝업에서 보여줄 뱃지
 * @property badgeUiState 뱃지 관련 상태 정보 해당 상태를 바탕으로 뱃지 관련 정보들이 조회됨
 * @since 2023-09-30 16:22:35
 */
@HiltViewModel
class BadgeBadgeViewModel @Inject constructor(
    private val getUserBadgeUseCase: GetUserBadgeUseCase,
    private val getBadgeUseCase: GetBadgeUseCase
) : ViewModel() {

    val sortType: MutableStateFlow<SortType> = MutableStateFlow<SortType>(SortType.Number())
    val filterType: MutableStateFlow<FilterType> = MutableStateFlow<FilterType>(FilterType.All())

    val onVisibleFilterBottomSheet: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val onVisibleSortBottomSheet: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val onVisibleBadgeDialog: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val selectedBadge: MutableStateFlow<AllBadge> =
        MutableStateFlow(AllBadge.UnacquiredBadge(0, "", "", "", ""))

    val badgeUiState: StateFlow<BadgeUiState> = combine(
        getBadgeUseCase(),
        getUserBadgeUseCase(),
        sortType,
        filterType
    ) { badge, userBadge, sortType, filterType ->
        when (badge) {
            is DataState.Fail -> BadgeUiState.Fail(badge.message)
            is DataState.Success -> when (userBadge) {
                is DataState.Fail -> BadgeUiState.Fail(userBadge.message)
                is DataState.Success -> {
                    BadgeUiState.Success(
                        BadgeState(
                            badge = userBadge.data.map {
                                AllBadge.AcquireBadge(
                                    id = it.badge.id,
                                    name = it.badge.name,
                                    description = it.badge.description,
                                    hint = it.badge.hint,
                                    url = it.badge.url,
                                    badgeTimeStamp = it.badgeTimeStamp
                                )
                            } + badge.data.subtract(userBadge.data.map { it.badge }.toSet()).map {
                                AllBadge.UnacquiredBadge(
                                    id = it.id,
                                    name = it.name,
                                    description = it.description,
                                    hint = it.hint,
                                    url = it.url,
                                )
                            },
                            sortType = sortType,
                            filterType = filterType
                        )
                    )
                }
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = BadgeUiState.Loading
    )

    val updateSortType: (SortType) -> Unit = { sortType.value = it }
    val updateFilterType: (FilterType) -> Unit = { filterType.value = it }
    val updateSelectedBadge: (AllBadge) -> Unit = { selectedBadge.value = it }
    val updateVisibleFilterBottomSheet: (Boolean) -> Unit =
        { onVisibleFilterBottomSheet.value = it }
    val updateVisibleSortBottomSheet: (Boolean) -> Unit = { onVisibleSortBottomSheet.value = it }
    val updateVisibleBadgeDialog: (Boolean) -> Unit = { onVisibleBadgeDialog.value = it }


}





