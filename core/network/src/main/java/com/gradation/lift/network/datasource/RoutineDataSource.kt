package com.gradation.lift.network.datasource

import com.gradation.lift.model.routine.CreateRoutineSetRoutine
import com.gradation.lift.model.routine.Routine
import com.gradation.lift.model.routine.RoutineSet
import com.gradation.lift.model.routine.RoutineSetRoutine
import com.gradation.lift.network.common.AuthAPIResult
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

interface RoutineDataSource {
    suspend fun getRoutineSet(userId: String): Flow<AuthAPIResult<List<RoutineSet>>>

    suspend fun createRoutineSet(createRoutineSetRoutine: CreateRoutineSetRoutine): Flow<AuthAPIResult<Boolean>>

    suspend fun getRoutineSetByDate(userId: String, date: LocalDate): Flow<AuthAPIResult<List<RoutineSet>>>

    suspend fun getRoutineSetByRoutineSetId(userId: String, routineSetId: Int): Flow<AuthAPIResult<RoutineSet>>

    suspend fun getRoutine(userId: String): Flow<AuthAPIResult<List<Routine>>>

    suspend fun getRoutineByDate(userId: String, date: LocalDate, ): Flow<AuthAPIResult<List<Routine>>>

    suspend fun getRoutineByRoutineSetId(userId: String, routineSetId: Int): Flow<AuthAPIResult<List<Routine>>>

    suspend fun getRoutineByDateAndRoutineSetId(userId: String, date: LocalDate, routineSetId: Int): Flow<AuthAPIResult<List<Routine>>>

    suspend fun getRoutineSetRoutine(userId: String): Flow<AuthAPIResult<List<RoutineSetRoutine>>>

    suspend fun getRoutineSetRoutineByDate(userId: String, date: LocalDate) : Flow<AuthAPIResult<List<RoutineSetRoutine>>>




}


