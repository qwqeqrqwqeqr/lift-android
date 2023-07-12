package com.gradation.lift.domain.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.routine.CreateRoutineSetRoutine
import com.gradation.lift.model.routine.Routine
import com.gradation.lift.model.routine.RoutineSet
import com.gradation.lift.model.routine.RoutineSetRoutine
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

interface RoutineRepository {

    suspend fun createRoutineSet(createRoutineSetRoutine: CreateRoutineSetRoutine): Flow<DataState<Boolean>>

    suspend fun getRoutine(): Flow<DataState<List<Routine>>>

    suspend fun getRoutineSetRoutine(): Flow<DataState<List<RoutineSetRoutine>>>

    suspend fun getRoutineSetRoutineByWeekday(weekday: String): Flow<DataState<List<RoutineSetRoutine>>>

    suspend fun getRoutineSetRoutineByRoutineSetId(routineSetId: Int): Flow<DataState<List<RoutineSetRoutine>>>
}