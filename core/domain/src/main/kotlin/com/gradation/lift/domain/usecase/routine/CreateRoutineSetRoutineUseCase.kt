package com.gradation.lift.domain.usecase.routine

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.RoutineRepository
import com.gradation.lift.model.model.routine.CreateRoutineSetRoutine
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateRoutineSetRoutineUseCase @Inject constructor(
    private val routineRepository: RoutineRepository
) {
    operator fun invoke(createRoutineSetRoutine: CreateRoutineSetRoutine): Flow<DataState<Unit>> =
        routineRepository.createRoutineSetRoutine(createRoutineSetRoutine)
}