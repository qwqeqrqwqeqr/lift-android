package com.gradation.lift.domain.usecase.routine

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.model.routine.RoutineSet
import com.gradation.lift.domain.repository.RoutineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRoutineSetByRoutineSetIdUseCase @Inject constructor(
    private val routineRepository: RoutineRepository
) {
    operator fun invoke(routineSetId: Int): Flow<DataState<RoutineSet>> =
        routineRepository.getRoutineSetByRoutineSetId(routineSetId)
}