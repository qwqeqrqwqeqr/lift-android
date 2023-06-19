package com.gradation.lift.network.datasource

import com.gradation.lift.model.routine.CreateRoutineSetRoutine
import com.gradation.lift.model.routine.Routine
import com.gradation.lift.model.routine.RoutineSet
import com.gradation.lift.model.routine.RoutineSetRoutine
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.dto.routine.GetRoutineSetRoutineByDateResponseDto
import com.gradation.lift.network.dto.routine.GetRoutineSetRoutineResponseDto
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate
import retrofit2.http.GET
import retrofit2.http.Query

interface RoutineDataSource {
    suspend fun getRoutineSet(userId: String): Flow<APIResult<List<RoutineSet>>>

    suspend fun createRoutineSet(createRoutineSetRoutine: CreateRoutineSetRoutine): Flow<APIResult<Boolean>>

    suspend fun getRoutineSetByDate(userId: String, date: LocalDate): Flow<APIResult<List<RoutineSet>>>

    suspend fun getRoutineSetByRoutineSetId(userId: String, routineSetId: Int): Flow<APIResult<RoutineSet>>

    suspend fun getRoutine(userId: String): Flow<APIResult<List<Routine>>>

    suspend fun getRoutineByDate(userId: String, date: LocalDate, ): Flow<APIResult<List<Routine>>>

    suspend fun getRoutineByRoutineSetId(userId: String, routineSetId: Int): Flow<APIResult<List<Routine>>>

    suspend fun getRoutineByDateAndRoutineSetId(userId: String, date: LocalDate, routineSetId: Int): Flow<APIResult<List<Routine>>>

    suspend fun getRoutineSetRoutine(userId: String): Flow<APIResult<List<RoutineSetRoutine>>>

    suspend fun getRoutineSetRoutineByDate(userId: String, date: LocalDate) : Flow<APIResult<List<RoutineSetRoutine>>>




}


