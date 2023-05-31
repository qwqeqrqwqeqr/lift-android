package com.gradation.lift.domain.usecase.routine

import com.gradation.lift.domain.model.common.DataState
import com.gradation.lift.domain.model.routine.Routine
import com.gradation.lift.domain.repository.RoutineRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetRoutineByDateUseCase @Inject constructor(
    private val routineRepository: RoutineRepository
) {
    operator fun invoke(date: LocalDate): Flow<DataState<List<Routine>>> =
        routineRepository.getRoutineByDate(date)
}