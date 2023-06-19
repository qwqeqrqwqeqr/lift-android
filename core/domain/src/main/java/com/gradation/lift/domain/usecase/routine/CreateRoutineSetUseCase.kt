package com.gradation.lift.domain.usecase.routine

import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.routine.CreateRoutineSetRoutine
import com.gradation.lift.domain.repository.RoutineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateRoutineSetUseCase @Inject constructor(
    private val routineRepository: RoutineRepository
) {
    operator fun invoke(createRoutineSetRoutine: CreateRoutineSetRoutine): Flow<DataState<Boolean>> =
        routineRepository.createRoutineSet(createRoutineSetRoutine)
}