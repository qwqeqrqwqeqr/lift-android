package com.gradation.lift.network.fake

import com.gradation.lift.domain.model.routine.CreateRoutineSetRoutine
import com.gradation.lift.domain.model.routine.Routine
import com.gradation.lift.domain.model.routine.RoutineSet
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.common.NetworkResultHandler
import com.gradation.lift.network.datasource.RoutineDataSource
import com.gradation.lift.network.service.RoutineService
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class FakeRoutineDataSource @Inject constructor(
    private val routineService: RoutineService,
    private val networkResultHandler: NetworkResultHandler
) : RoutineDataSource{
    override suspend fun getRoutineSet(userId: String): Flow<APIResult<List<RoutineSet>>> {
        TODO("Not yet implemented")
    }

    override suspend fun createRoutineSet(createRoutineSetRoutine: CreateRoutineSetRoutine): Flow<APIResult<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun getRoutineSetByDate(
        userId: String,
        date: LocalDate
    ): Flow<APIResult<List<RoutineSet>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getRoutineSetByRoutineSetId(
        userId: String,
        routineSetId: Int
    ): Flow<APIResult<RoutineSet>> {
        TODO("Not yet implemented")
    }

    override suspend fun getRoutine(userId: String): Flow<APIResult<List<Routine>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getRoutineByDate(
        userId: String,
        date: LocalDate
    ): Flow<APIResult<List<Routine>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getRoutineByRoutineSetId(
        userId: String,
        routineSetId: Int
    ): Flow<APIResult<List<Routine>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getRoutineByDateAndRoutineSetId(
        userId: String,
        date: LocalDate,
        routineSetId: Int
    ): Flow<APIResult<List<Routine>>> {
        TODO("Not yet implemented")
    }

}