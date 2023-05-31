package com.gradation.lift.domain.usecase.routine

import com.gradation.lift.domain.model.common.DataState
import com.gradation.lift.domain.model.routine.CreateRoutineSetRoutine
import com.gradation.lift.domain.model.routine.Routine
import com.gradation.lift.domain.repository.RoutineRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetRoutineByDateAndRoutineSetIdUseCase @Inject constructor(
    private val routineRepository: RoutineRepository
) {
    operator fun invoke(date: LocalDate, routineSetId: Int): Flow<DataState<List<Routine>>> =
        routineRepository.getRoutineByDateAndRoutineSetId(date,routineSetId)
}