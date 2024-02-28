package com.gradation.lift.domain.usecase.routine

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.RoutineRepository
import com.gradation.lift.model.model.routine.UpdateUsedRoutineSet
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateUsedRoutineSetUseCase @Inject constructor(
    private val routineRepository: RoutineRepository,
) {
    operator fun invoke(updateUsedRoutineSet: UpdateUsedRoutineSet): Flow<DataState<Unit>> =
        routineRepository.updateUsedRoutineSet(updateUsedRoutineSet)
}