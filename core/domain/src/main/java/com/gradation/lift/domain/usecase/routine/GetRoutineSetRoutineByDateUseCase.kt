package com.gradation.lift.domain.usecase.routine

import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.routine.RoutineSetRoutine
import com.gradation.lift.domain.repository.RoutineRepository
import dagger.Provides
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

class GetRoutineSetRoutineByDateUseCase  @Inject constructor(
    private val routineRepository: RoutineRepository
) {
    operator fun invoke(date: LocalDate): Flow<DataState<List<RoutineSetRoutine>>> =
        routineRepository.getRoutineSetRoutineByDate(date)

}