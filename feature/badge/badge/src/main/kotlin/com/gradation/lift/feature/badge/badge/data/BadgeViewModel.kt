package com.gradation.lift.feature.badge.badge.data

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.badge.GetBadgeUseCase
import com.gradation.lift.domain.usecase.badge.GetUserBadgeUseCase
import com.gradation.lift.domain.usecase.badge.UpdateUserBadgeMainFlagUseCase
import com.gradation.lift.feature.badge.badge.data.model.BadgeState
import com.gradation.lift.feature.badge.badge.data.state.BadgeCaseState
import com.gradation.lift.feature.badge.badge.data.state.BadgeStoreState
import com.gradation.lift.feature.badge.badge.data.state.BadgeUiState
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * [BadgeViewModel]
 * @property badgeUiState 뱃지 관련 상태 정보 해당 상태를 바탕으로 뱃지 관련 정보들이 조회됨
 * @since 2024-02-26 14:27:19
 */
@HiltViewModel
class BadgeViewModel @Inject constructor(
    getUserBadgeUseCase: GetUserBadgeUseCase,
    getBadgeUseCase: GetBadgeUseCase,
    savedStateHandle: SavedStateHandle,
    updateUserBadgeMainFlagUseCase: UpdateUserBadgeMainFlagUseCase,
) : ViewModel() {

    internal val initialPage: Int =
        savedStateHandle.get<Int>(SavedStateHandleKey.Badge.BADGE_PAGE_KEY) ?: 0

    val badgeUiState: StateFlow<BadgeUiState> = combine(
        getBadgeUseCase(),
        getUserBadgeUseCase(),
    ) { badge, userBadge ->
        when (badge) {
            is DataState.Fail -> BadgeUiState.Fail(badge.message)
            is DataState.Success -> when (userBadge) {
                is DataState.Fail -> BadgeUiState.Fail(userBadge.message)
                is DataState.Success -> {
                    BadgeUiState.Success(
                        badgeStateList =
                        userBadge.data.map {
                            BadgeState.AcquireBadge(
                                id = it.badge.id,
                                name = it.badge.name,
                                description = it.badge.description,
                                hint = it.badge.hint,
                                url = it.badge.url,
                                color = Color(android.graphics.Color.parseColor(it.badge.color)),
                                backgroundColor = Color(android.graphics.Color.parseColor(it.badge.backgroundColor)),
                                badgeTimeStamp = it.badgeTimeStamp
                            )
                        } + badge.data.subtract(userBadge.data.map { it.badge }.toSet()).map {
                            BadgeState.UnacquiredBadge(
                                id = it.id,
                                name = it.name,
                                description = it.description,
                                hint = it.hint,
                                url = it.url,
                            )
                        },
                        userBadgeList = userBadge.data
                    )
                }
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = BadgeUiState.Loading
    )


    val badgeStoreState = BadgeStoreState(badgeUiState, viewModelScope)
    val badgeCaseState =
        BadgeCaseState(updateUserBadgeMainFlagUseCase, badgeUiState, viewModelScope)
}





