package com.gradation.lift.feature.badge.badge.data.state

import com.gradation.lift.feature.badge.badge.data.model.BadgeState
import com.gradation.lift.feature.badge.badge.data.state.BadgeUiState.Loading
import com.gradation.lift.model.model.badge.UserBadge


/**
 * [BadgeUiState]
 * 뱃지 화면 Ui 상태 초기값은 [Loading] 을 가진다.
 */
sealed interface BadgeUiState {

    data class Success(
        val badgeStateList: List<BadgeState>,
        val userBadgeList: List<UserBadge>,
    ) : BadgeUiState

    data class Fail(val message: String) : BadgeUiState
    data object Loading : BadgeUiState

}