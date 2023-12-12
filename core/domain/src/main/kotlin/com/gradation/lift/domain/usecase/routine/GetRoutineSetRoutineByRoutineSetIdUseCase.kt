package com.gradation.lift.domain.usecase.routine

import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.model.routine.Routine
import com.gradation.lift.domain.repository.RoutineRepository
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRoutineSetRoutineByRoutineSetIdUseCase @Inject constructor(
    private val routineRepository: RoutineRepository
) {
    operator fun invoke(routineSetIdList: Set<Int>): Flow<DataState<List<RoutineSetRoutine>>> =
        routineRepository.getRoutineSetRoutineByRoutineSetId(routineSetIdList)
}