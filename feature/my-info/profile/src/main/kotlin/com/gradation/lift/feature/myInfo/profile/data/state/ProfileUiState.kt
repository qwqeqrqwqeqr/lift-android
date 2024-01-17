package com.gradation.lift.feature.myInfo.profile.data.state

/**
 * [ProfileUiState]
 * UI에 표시할 사용자 상세정보 상태
 * @since 2024-01-12 15:34:31
 */
sealed interface ProfileUiState {
    data class Success(val profileState: ProfileState) : ProfileUiState
    data class Fail(val message: String) : ProfileUiState
    data object Loading : ProfileUiState
}