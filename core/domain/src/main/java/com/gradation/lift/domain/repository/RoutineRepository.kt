package com.gradation.lift.domain.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.model.routine.CreateRoutineSetRoutine
import com.gradation.lift.model.model.routine.Routine
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import kotlinx.coroutines.flow.Flow

interface RoutineRepository {

     fun createRoutineSet(createRoutineSetRoutine: CreateRoutineSetRoutine): Flow<DataState<Boolean>>

     fun getRoutine(): Flow<DataState<List<Routine>>>

     fun getRoutineSetRoutine(): Flow<DataState<List<RoutineSetRoutine>>>

     fun getRoutineSetRoutineByWeekday(weekday: Weekday): Flow<DataState<List<RoutineSetRoutine>>>

     fun getRoutineSetRoutineByRoutineSetId(routineSetIdList: Set<Int>): Flow<DataState<List<RoutineSetRoutine>>>



}