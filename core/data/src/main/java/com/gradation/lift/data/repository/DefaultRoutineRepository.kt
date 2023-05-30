package com.gradation.lift.data.repository

import com.gradation.lift.domain.model.common.DataState
import com.gradation.lift.domain.model.routine.CreateRoutineSetRoutine
import com.gradation.lift.domain.model.routine.Routine
import com.gradation.lift.domain.model.routine.RoutineSet
import com.gradation.lift.domain.repository.RoutineRepository
import com.gradation.lift.network.datasource.RoutineDataSource
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject


class DefaultRoutineRepository @Inject constructor(
    private val routineDataSource: RoutineDataSource,
) : RoutineRepository {
    override fun getRoutineSet(userId: String): Flow<DataState<List<RoutineSet>>> {
        TODO("Not yet implemented")
    }

    override fun createRoutineSet(createRoutineSetRoutine: CreateRoutineSetRoutine): Flow<DataState<Boolean>> {
        TODO("Not yet implemented")
    }

    override fun getRoutineSetByDate(
        userId: String,
        date: LocalDate
    ): Flow<DataState<List<RoutineSet>>> {
        TODO("Not yet implemented")
    }

    override fun getRoutineSetByRoutineSetId(
        userId: String,
        routineSetId: Int
    ): Flow<DataState<RoutineSet>> {
        TODO("Not yet implemented")
    }

    override fun getRoutine(userId: String): Flow<DataState<List<Routine>>> {
        TODO("Not yet implemented")
    }

    override fun getRoutineByDate(userId: String, date: LocalDate): Flow<DataState<List<Routine>>> {
        TODO("Not yet implemented")
    }

    override fun getRoutineByRoutineSetId(
        userId: String,
        routineSetId: Int
    ): Flow<DataState<List<Routine>>> {
        TODO("Not yet implemented")
    }

    override fun getRoutineByDateAndRoutineSetId(
        userId: String,
        date: LocalDate,
        routineSetId: Int
    ): Flow<DataState<List<Routine>>> {
        TODO("Not yet implemented")
    }


}