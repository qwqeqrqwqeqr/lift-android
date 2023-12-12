package com.gradation.lift.feature.badge.badge.data.state


/**
 * [BadgeUiState]
 * 뱃지 화면 Ui 상태 초기값은 [Loading] 을 가진다.
 */
sealed interface BadgeUiState {

    data class Success(val badgeState: BadgeState) : BadgeUiState
    data class Fail(val message: String) : BadgeUiState
    object Loading : BadgeUiState

}