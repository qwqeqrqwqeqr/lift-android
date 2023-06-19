package com.gradation.lift.domain.usecase.routine

import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.routine.RoutineSet
import com.gradation.lift.domain.repository.RoutineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRoutineSetUseCase @Inject constructor(
    private val routineRepository: RoutineRepository
) {
    operator fun invoke(): Flow<DataState<List<RoutineSet>>> =
        routineRepository.getRoutineSet()
}