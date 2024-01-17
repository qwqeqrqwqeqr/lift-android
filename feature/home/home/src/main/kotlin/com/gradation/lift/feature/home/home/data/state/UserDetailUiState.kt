package com.gradation.lift.feature.home.home.data.state

import com.gradation.lift.model.model.user.UserDetail

/**
 * [UserDetailUiState]
 * UI에 표시할 사용자 상세정보 상태
 * @since 2023-08-18 20:42:08
 */
sealed interface UserDetailUiState {
    data class Success(val userDetail: UserDetail) : UserDetailUiState
    data class Fail(val message: String) : UserDetailUiState
    data object Loading : UserDetailUiState
}