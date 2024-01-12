package com.gradation.lift.feature.myInfo.myInfo.data.state

import com.gradation.lift.model.model.user.UserDetail

/**
 * [UserDetailUiState]
 * UI에 표시할 사용자 상세정보 상태
 * @since 2023-08-25 13:30:45
 */
sealed interface UserDetailUiState {
    data class Success(val userDetail: UserDetail) : UserDetailUiState
    data class Fail(val message: String) : UserDetailUiState
    object Loading : UserDetailUiState
}