package com.gradation.lift.feature.home.data.state

import com.gradation.lift.model.model.user.UserDetail

sealed interface UserDetailUiState {
    data class Success(val userDetail: UserDetail) : UserDetailUiState
    data class Fail(val message: String) : UserDetailUiState
    object Loading : UserDetailUiState
}