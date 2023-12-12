package com.gradation.lift.domain.usecase.routine

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.RoutineRepository
import com.gradation.lift.model.model.routine.UpdateRoutineSetRoutine
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateRoutineSetRoutineUseCase @Inject constructor(
    private val routineRepository: RoutineRepository
) {
    operator fun invoke(updateRoutineSetRoutine: UpdateRoutineSetRoutine): Flow<DataState<Unit>> =
        routineRepository.updateRoutineSetRoutine(updateRoutineSetRoutine)
}