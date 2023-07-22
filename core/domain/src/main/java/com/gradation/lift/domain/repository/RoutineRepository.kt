package com.gradation.lift.domain.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.common.Weekday
import com.gradation.lift.model.routine.*
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

interface RoutineRepository {

     fun createRoutineSet(createRoutineSetRoutine: CreateRoutineSetRoutine): Flow<DataState<Boolean>>

     fun getRoutine(): Flow<DataState<List<Routine>>>

     fun getRoutineSetRoutine(): Flow<DataState<List<RoutineSetRoutine>>>

     fun getRoutineSetRoutineByWeekday(weekday: Weekday): Flow<DataState<List<RoutineSetRoutine>>>

     fun getRoutineSetRoutineByRoutineSetId(routineSetIdList: Set<Int>): Flow<DataState<List<RoutineSetRoutine>>>



}