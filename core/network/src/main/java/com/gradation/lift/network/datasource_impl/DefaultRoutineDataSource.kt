package com.gradation.lift.network.datasource_impl

import com.gradation.lift.domain.model.routine.Routine
import com.gradation.lift.domain.model.routine.RoutineSet
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.common.NetworkResultHandler
import com.gradation.lift.network.datasource.RoutineDataSource
import com.gradation.lift.network.dto.routine.CreateRoutineSetRequestDto
import com.gradation.lift.network.service.RoutineService
import java.time.LocalDate
import javax.inject.Inject

class DefaultRoutineDataSource @Inject constructor(
    private val routineService: RoutineService,
    private val networkResultHandler: NetworkResultHandler
) : RoutineDataSource {
    override suspend fun getRoutineSet(userId: String): APIResult<List<RoutineSet>> {
        TODO("Not yet implemented")
    }

    override suspend fun createRoutineSet(createRoutineSetRequestDto: CreateRoutineSetRequestDto): APIResult<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun getRoutineSetByDate(
        userId: String,
        date: LocalDate
    ): APIResult<List<RoutineSet>> {
        TODO("Not yet implemented")
    }

    override suspend fun getRoutineSetByRoutineSetId(
        userId: String,
        routineSetId: Int
    ): APIResult<RoutineSet> {
        TODO("Not yet implemented")
    }

    override suspend fun getRoutine(userId: String): APIResult<List<Routine>> {
        TODO("Not yet implemented")
    }

    override suspend fun getRoutineByDateResponseDto(
        userId: String,
        date: LocalDate
    ): APIResult<List<Routine>> {
        TODO("Not yet implemented")
    }

    override suspend fun getRoutineByRoutineSetIdResponseDto(
        userId: String,
        routineSetId: Int
    ): APIResult<List<Routine>> {
        TODO("Not yet implemented")
    }

    override suspend fun getRoutineByDateAndRoutineSetIdResponseDto(
        userId: String,
        date: LocalDate,
        routineSetId: Int
    ): APIResult<List<Routine>> {
        TODO("Not yet implemented")
    }
}