package com.gradation.lift.feature.home.data.state

import com.gradation.lift.model.model.routine.RoutineSetRoutine

/**
 * [WeekDateRoutineUiState]
 * UI에 표시할 요일별 루틴 목록 상태
 * 비어있을 때 또한 다르게 처리해주기 위해 Empty 상태 추가
 * @since 2023-08-18 20:42:53
 */
sealed interface WeekDateRoutineUiState {
    data class Success(val weekDateRoutine: List<RoutineSetRoutine>) : WeekDateRoutineUiState
    data class Fail(val message: String) : WeekDateRoutineUiState
    object Loading : WeekDateRoutineUiState
    object Empty : WeekDateRoutineUiState
}



