package com.gradation.lift.domain.usecase.routine

import com.gradation.lift.domain.model.common.DataState
import com.gradation.lift.domain.model.routine.Routine
import com.gradation.lift.domain.repository.RoutineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRoutineUseCase @Inject constructor(
    private val routineRepository: RoutineRepository
) {
    operator fun invoke(): Flow<DataState<List<Routine>>> =
        routineRepository.getRoutine()
}