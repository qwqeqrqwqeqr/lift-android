package com.gradation.lift.feature.work.routine_selection.data

import com.gradation.lift.feature.work.work.data.model.RoutineSetRoutineSelection


sealed interface RoutineSetRoutineSelectionUiState {
    data class Success(val routineSetRoutineSelection: List<RoutineSetRoutineSelection>) :
        RoutineSetRoutineSelectionUiState

    data class Fail(val message: String) : RoutineSetRoutineSelectionUiState
    object Loading : RoutineSetRoutineSelectionUiState
    object Empty : RoutineSetRoutineSelectionUiState
}



