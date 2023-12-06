package com.gradation.lift.feature.routineDetail.routine.data

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.routine.GetRoutineSetRoutineByRoutineSetIdUseCase
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

fun routineUiState(
    routineSetId: StateFlow<Int?>,
    getRoutineSetRoutineByRoutineSetIdUseCase: GetRoutineSetRoutineByRoutineSetIdUseCase
): Flow<RoutineUiState> =
    routineSetId.flatMapLatest {
        getRoutineSetRoutineByRoutineSetIdUseCase(
            it?.let { setOf(it) } ?: emptySet()
        )
    }.map { routineSetRoutine ->
        when (routineSetRoutine) {
            is DataState.Fail -> RoutineUiState.Fail(routineSetRoutine.message)
            is DataState.Success -> with(routineSetRoutine.data) {
                if (this.isEmpty()) RoutineUiState.Fail("루틴이 존재하지 않습니다.")
                else RoutineUiState.Success(this.first())
            }
        }
    }



sealed interface RoutineUiState {
    data class Success(val routineSetRoutine: RoutineSetRoutine) : RoutineUiState
    data class Fail(val message: String) : RoutineUiState
    data object Loading : RoutineUiState
}