package com.gradation.lift.feature.home.data.state

import com.gradation.lift.model.model.badge.UserBadge

/**
 * [BadgeUiState]
 * UI에 표시할 뱃지 상태
 * @since 2023-09-25 21:18:35
 */
sealed interface BadgeUiState {
    data class Success(val userBadge: List<UserBadge>) : BadgeUiState
    data class Fail(val message: String) : BadgeUiState
    object Loading : BadgeUiState
}