package com.gradation.lift.feature.badge.setting.data.state

import com.gradation.lift.model.model.badge.UserBadge

/**
 * [BadgeUiState]
 * 뱃지 화면 Ui 상태 초기값은 [Loading] 을 가진다.
 * @since 2023-09-26 20:58:36
 */
sealed interface BadgeUiState {

    data class Success(val badgeList: List<UserBadge>) : BadgeUiState
    data class Fail(val message: String) : BadgeUiState
    object Loading : BadgeUiState

}