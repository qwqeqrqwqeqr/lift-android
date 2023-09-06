package com.gradation.lift.domain.usecase.routine

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.RoutineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteRoutineSetUseCase @Inject constructor(
    private val routineRepository: RoutineRepository
) {
    operator fun invoke(routineSetId: Int): Flow<DataState<Unit>> =
        routineRepository.deleteRoutineSetRoutine(routineSetId = routineSetId)
}