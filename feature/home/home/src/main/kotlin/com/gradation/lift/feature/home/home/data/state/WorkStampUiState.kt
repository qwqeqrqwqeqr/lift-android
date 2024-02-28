package com.gradation.lift.feature.home.home.data.state

/**
 * [WorkStampUiState]
 * UI에 연속 운동일 수를 표기하기 위한 상태
 * @since 2023-09-25 21:18:35
 */
sealed interface WorkStampUiState {
    data class Success(val workStampState: WorkStampState) : WorkStampUiState
    data class Fail(val message: String) : WorkStampUiState
    data object Loading : WorkStampUiState
}