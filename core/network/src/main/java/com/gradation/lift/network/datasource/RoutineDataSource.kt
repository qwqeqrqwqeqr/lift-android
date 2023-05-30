package com.gradation.lift.network.datasource

import com.gradation.lift.domain.model.routine.Routine
import com.gradation.lift.domain.model.routine.RoutineSet
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.dto.routine.*
import java.time.LocalDate

interface RoutineDataSource {
    suspend fun getRoutineSet(userId: String): APIResult<List<RoutineSet>>

    suspend fun createRoutineSet(createRoutineSetRequestDto: CreateRoutineSetRequestDto): APIResult<Boolean>

    suspend fun getRoutineSetByDate(userId: String, date: LocalDate): APIResult<List<RoutineSet>>

    suspend fun getRoutineSetByRoutineSetId(userId: String, routineSetId: Int): APIResult<RoutineSet>

    suspend fun getRoutine(userId: String): APIResult<List<Routine>>

    suspend fun getRoutineByDateResponseDto(userId: String, date: LocalDate, ): APIResult<List<Routine>>

    suspend fun getRoutineByRoutineSetIdResponseDto(userId: String, routineSetId: Int): APIResult<List<Routine>>

    suspend fun getRoutineByDateAndRoutineSetIdResponseDto(userId: String, date: LocalDate, routineSetId: Int, ): APIResult<List<Routine>>
}