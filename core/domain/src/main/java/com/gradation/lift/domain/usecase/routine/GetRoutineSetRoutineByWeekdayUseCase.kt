package com.gradation.lift.domain.usecase.routine

import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.routine.Routine
import com.gradation.lift.domain.repository.RoutineRepository
import com.gradation.lift.model.routine.RoutineSetRoutine
import com.gradation.lift.model.routine.Weekday
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRoutineSetRoutineByWeekdayUseCase @Inject constructor(
    private val routineRepository: RoutineRepository,
) {
    operator fun invoke(weekday: Weekday): Flow<DataState<List<RoutineSetRoutine>>> =
        routineRepository.getRoutineSetRoutineByWeekday(weekday)
}