package com.gradation.lift.feature.updateRoutine.common.data.state

import com.gradation.lift.model.model.routine.RoutineSetRoutine


sealed interface RoutineUiState {
    data class Success(val routineSetRoutine: RoutineSetRoutine) : RoutineUiState
    data class Fail(val message: String) : RoutineUiState
    data object Loading : RoutineUiState
}