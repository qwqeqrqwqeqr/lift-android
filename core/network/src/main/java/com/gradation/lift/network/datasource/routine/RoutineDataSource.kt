package com.gradation.lift.network.datasource.routine

import com.gradation.lift.model.model.common.Weekday
import com.gradation.lift.model.model.routine.CreateRoutineSetRoutine
import com.gradation.lift.model.model.routine.Routine
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.network.common.NetworkResult
import kotlinx.coroutines.flow.Flow

interface RoutineDataSource {

    suspend fun createRoutineSet(createRoutineSetRoutine: CreateRoutineSetRoutine): Flow<NetworkResult<Boolean>>

    suspend fun getRoutine(): Flow<NetworkResult<List<Routine>>>

    suspend fun getRoutineSetRoutine(): Flow<NetworkResult<List<RoutineSetRoutine>>>

    suspend fun getRoutineSetRoutineByWeekday(weekday: Weekday): Flow<NetworkResult<List<RoutineSetRoutine>>>

    suspend fun getRoutineSetRoutineByRoutineSetId(routineSetIdList: Set<Int>): Flow<NetworkResult<List<RoutineSetRoutine>>>


}


