package com.gradation.lift.feature.work.routine_selection.data.state

import com.gradation.lift.feature.work.work.data.model.RoutineSetRoutineSelection


/**
 * [RoutineSetRoutineSelectionUiState]
 * [Success] 성공적으로 조건에 맞는 루틴세트를 불러온 상태
 * [Empty] 루틴 세트가 존재하지 않을 떄의 상태
 * @since 2023-08-22 12:39:45
 */
sealed interface RoutineSetRoutineSelectionUiState {
    data class Success(val routineSetRoutineSelection: List<RoutineSetRoutineSelection>) :
        RoutineSetRoutineSelectionUiState

    data class Fail(val message: String) : RoutineSetRoutineSelectionUiState
    object Loading : RoutineSetRoutineSelectionUiState
    object Empty : RoutineSetRoutineSelectionUiState
}



