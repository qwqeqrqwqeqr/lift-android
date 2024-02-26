package com.gradation.lift.feature.badge.badge.data.state

import com.gradation.lift.feature.badge.badge.data.model.FilterType
import com.gradation.lift.feature.badge.badge.data.model.SortType
import com.gradation.lift.feature.badge.badge.data.model.UserBadge
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * [BadgeStoreState]
 * 뱃지와 관련한 상태들을 정의한 클래스
 * @property sortType 뱃지 정렬 타입 (기본,이름순,최신순,오래된순)
 * @property filterType 뱃지 필터 타입 (전체,획득뱃지,미획득뱃지)
 * @property badgeList 화면에서 보여줄 뱃지 리스트 [sortType]과 [filterType]에 따라 각기 다르게 보여줌
 * @property currentTotalBadgeCount 현재 필터링된 총 뱃지 개수
 * @property acquiredBadgeCount 획득한 뱃지 개수
 * @property unacquiredBadgeCount 미획득한 뱃지 개수
 * @since 2024-02-26 14:40:35
 */
class BadgeStoreState(
    badgeUiState: StateFlow<BadgeUiState>,
    viewModelScope: CoroutineScope,
    private val badgeList: StateFlow<List<UserBadge>> = badgeUiState.map {
        when (it) {
            is BadgeUiState.Fail -> emptyList()
            BadgeUiState.Loading -> emptyList()
            is BadgeUiState.Success -> it.badgeList
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList()
    ),
    val sortType: MutableStateFlow<SortType> = MutableStateFlow(SortType.Number()),
    val filterType: MutableStateFlow<FilterType> = MutableStateFlow(FilterType.All()),
    val userBadgeList: StateFlow<List<UserBadge>> = combine(
        badgeList,
        sortType,
        filterType
    ) { badge, sort, filter ->
        when (filter) {
            is FilterType.Acquired -> {
                when (sort) {
                    is SortType.Name -> badge.filterIsInstance<UserBadge.AcquireBadge>()
                        .sortedBy { it.name }

                    is SortType.Newest -> badge.filterIsInstance<UserBadge.AcquireBadge>()
                        .sortedByDescending { it.badgeTimeStamp }

                    is SortType.Number -> badge.filterIsInstance<UserBadge.AcquireBadge>()
                        .sortedBy { it.id }

                    is SortType.Oldest -> badge.filterIsInstance<UserBadge.AcquireBadge>()
                        .sortedBy { it.badgeTimeStamp }
                }
            }

            is FilterType.All -> {
                when (sort) {
                    is SortType.Name -> badge.sortedBy { it.name }
                    is SortType.Newest -> badge.filterIsInstance<UserBadge.AcquireBadge>()
                        .sortedByDescending { it.badgeTimeStamp } + badge.filterIsInstance<UserBadge.UnacquiredBadge>()
                        .sortedBy { it.id }

                    is SortType.Number -> badge.sortedBy { it.id }
                    is SortType.Oldest -> badge.filterIsInstance<UserBadge.AcquireBadge>()
                        .sortedBy { it.badgeTimeStamp } + badge.filterIsInstance<UserBadge.UnacquiredBadge>()
                        .sortedBy { it.id }
                }
            }

            is FilterType.UnAcquired -> {
                when (sort) {
                    is SortType.Name -> badge.filterIsInstance<UserBadge.UnacquiredBadge>()
                        .sortedBy { it.name }

                    is SortType.Newest -> badge.filterIsInstance<UserBadge.UnacquiredBadge>()
                        .sortedBy { it.id }

                    is SortType.Number -> badge.filterIsInstance<UserBadge.UnacquiredBadge>()
                        .sortedBy { it.id }

                    is SortType.Oldest -> badge.filterIsInstance<UserBadge.UnacquiredBadge>()
                        .sortedBy { it.id }
                }

            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList()
    ),

    val currentTotalBadgeCount: StateFlow<Int> = userBadgeList.map { it.count() }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = 0
    ),
    val acquiredBadgeCount: StateFlow<Int> = badgeList.map { it.count { it is UserBadge.AcquireBadge } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = 0
        ),
    val unacquiredBadgeCount: StateFlow<Int> = badgeList.map { it.count { it is UserBadge.UnacquiredBadge } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = 0
        ),
) {

    val updateSortType: (SortType) -> Unit = { sortType.value = it }
    val updateFilterType: (FilterType) -> Unit = { filterType.value = it }

}