package com.gradation.lift.feature.home.data.state

import com.gradation.lift.model.model.routine.RoutineSetRoutine


sealed interface WeekDateRoutineUiState {
    data class Success(val weekDateRoutine: List<RoutineSetRoutine>) : WeekDateRoutineUiState
    data class Fail(val message: String) : WeekDateRoutineUiState
    object Loading : WeekDateRoutineUiState
    object Empty: WeekDateRoutineUiState
}



