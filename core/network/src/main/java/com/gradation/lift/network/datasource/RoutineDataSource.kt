package com.gradation.lift.network.datasource

import com.gradation.lift.model.model.common.Weekday
import com.gradation.lift.model.model.routine.CreateRoutineSetRoutine
import com.gradation.lift.model.model.routine.Routine
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.network.common.APIResult
import kotlinx.coroutines.flow.Flow

interface RoutineDataSource {

    suspend fun createRoutineSet(createRoutineSetRoutine: CreateRoutineSetRoutine): Flow<APIResult<Boolean>>

    suspend fun getRoutine(): Flow<APIResult<List<Routine>>>

    suspend fun getRoutineSetRoutine(): Flow<APIResult<List<RoutineSetRoutine>>>

    suspend fun getRoutineSetRoutineByWeekday(weekday: Weekday): Flow<APIResult<List<RoutineSetRoutine>>>

    suspend fun getRoutineSetRoutineByRoutineSetId(routineSetIdList: Set<Int>): Flow<APIResult<List<RoutineSetRoutine>>>


}


