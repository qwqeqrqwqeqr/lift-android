package com.gradation.lift.network.datasource

import com.gradation.lift.model.routine.CreateRoutineSetRoutine
import com.gradation.lift.model.routine.Routine
import com.gradation.lift.model.routine.RoutineSet
import com.gradation.lift.model.routine.RoutineSetRoutine
import com.gradation.lift.network.common.APIResult
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

interface RoutineDataSource {

    suspend fun createRoutineSet(createRoutineSetRoutine: CreateRoutineSetRoutine): Flow<APIResult<Boolean>>

    suspend fun getRoutine(): Flow<APIResult<List<Routine>>>

    suspend fun getRoutineSetRoutine(): Flow<APIResult<List<RoutineSetRoutine>>>

    suspend fun getRoutineSetRoutineByWeekday(weekday: String): Flow<APIResult<List<RoutineSetRoutine>>>

    suspend fun getRoutineSetRoutineByRoutineSetId(routineSetId: String): Flow<APIResult<List<RoutineSetRoutine>>>


}


