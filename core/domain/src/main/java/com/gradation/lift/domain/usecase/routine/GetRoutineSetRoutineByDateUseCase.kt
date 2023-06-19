package com.gradation.lift.domain.usecase.routine

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.model.routine.RoutineSetRoutine
import com.gradation.lift.domain.repository.RoutineRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate
import javax.inject.Inject

class GetRoutineSetRoutineByDateUseCase  @Inject constructor(
    private val routineRepository: RoutineRepository
) {
    operator fun invoke(date: LocalDate): Flow<DataState<List<RoutineSetRoutine>>> =
        routineRepository.getRoutineSetRoutineByDate(date)

}