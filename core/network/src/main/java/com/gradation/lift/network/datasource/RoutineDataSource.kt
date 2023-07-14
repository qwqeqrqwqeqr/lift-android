package com.gradation.lift.network.datasource

import com.gradation.lift.model.common.Weekday
import com.gradation.lift.model.routine.*
import com.gradation.lift.network.common.APIResult
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

interface RoutineDataSource {

    suspend fun createRoutineSet(createRoutineSetRoutine: CreateRoutineSetRoutine): Flow<APIResult<Boolean>>

    suspend fun getRoutine(): Flow<APIResult<List<Routine>>>

    suspend fun getRoutineSetRoutine(): Flow<APIResult<List<RoutineSetRoutine>>>

    suspend fun getRoutineSetRoutineByWeekday(weekday: Weekday): Flow<APIResult<List<RoutineSetRoutine>>>

    suspend fun getRoutineSetRoutineByRoutineSetId(routineSetId: Int): Flow<APIResult<List<RoutineSetRoutine>>>


}


