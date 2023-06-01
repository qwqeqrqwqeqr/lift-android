package com.gradation.lift.domain.usecase.routine

import com.gradation.lift.domain.model.common.DataState
import com.gradation.lift.domain.model.routine.RoutineSetRoutine
import com.gradation.lift.domain.repository.RoutineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRoutineSetRoutineUseCase  @Inject constructor(
    private val routineRepository: RoutineRepository
) {
    operator fun invoke(): Flow<DataState<List<RoutineSetRoutine>>> =
        routineRepository.getRoutineSetRoutine()
}