package com.gradation.lift.feature.work.work.data.state

import com.gradation.lift.model.model.routine.RoutineSetRoutine

sealed interface WorkUiState {
    data class Success(val routineSetRoutine: List<RoutineSetRoutine>) : WorkUiState
    data class Fail(val message: String) : WorkUiState
    data object Loading : WorkUiState
}