package com.gradation.lift.feature.myInfo.myInfo.data.state

/**
 * [MyInfoUiState]
 * UI에 표시할 사용자 상세정보 상태
 * @since 2023-08-25 13:30:45
 */
sealed interface MyInfoUiState {
    data class Success(val myInfoState: MyInfoState) : MyInfoUiState
    data class Fail(val message: String) : MyInfoUiState
    data object Loading : MyInfoUiState
}