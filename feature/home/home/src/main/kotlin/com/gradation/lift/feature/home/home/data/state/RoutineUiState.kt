package com.gradation.lift.feature.home.home.data.state

import com.gradation.lift.model.model.routine.RoutineSetRoutine

/**
 * [RoutineUiState]
 * UI에 표시할 루틴 상태
 * @since 2023-10-19 12:20:16
 */
sealed interface RoutineUiState {
    data class Success(val routineList: List<RoutineSetRoutine>) : RoutineUiState
    data class Fail(val message: String) : RoutineUiState
    data object Loading : RoutineUiState
    data object Empty : RoutineUiState
}