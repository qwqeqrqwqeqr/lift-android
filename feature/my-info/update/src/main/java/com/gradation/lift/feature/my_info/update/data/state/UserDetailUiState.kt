package com.gradation.lift.feature.my_info.update.data.state

sealed interface UserDetailUiState {
    object Success: UserDetailUiState
    object Fail : UserDetailUiState
    object Loading : UserDetailUiState
}