package com.gradation.lift.feature.work.change_order.data

import com.gradation.lift.model.routine.RoutineSetRoutine


sealed interface RoutineSetRoutineUiState {
    data class Success(val routineSetRoutine: List<RoutineSetRoutine>) :
        RoutineSetRoutineUiState
    data class Fail(val message: String) : RoutineSetRoutineUiState
    object Loading : RoutineSetRoutineUiState
    object Empty : RoutineSetRoutineUiState
}

