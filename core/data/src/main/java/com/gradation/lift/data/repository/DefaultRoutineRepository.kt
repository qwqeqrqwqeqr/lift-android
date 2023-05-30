package com.gradation.lift.data.repository

import com.gradation.lift.domain.model.common.DataState
import com.gradation.lift.domain.model.routine.Routine
import com.gradation.lift.domain.model.routine.RoutineSet
import com.gradation.lift.domain.model.work.WorkCategory
import com.gradation.lift.domain.model.work.WorkPart
import com.gradation.lift.domain.repository.RoutineRepository
import com.gradation.lift.domain.repository.WorkRepository
import com.gradation.lift.network.datasource.RoutineDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class DefaultRoutineRepository @Inject constructor(
    private val routineDataSource: RoutineDataSource,
) : RoutineRepository {
    override fun getRoutineSet(userId: String): Flow<DataState<List<RoutineSet>>> {
        TODO("Not yet implemented")
    }

    override fun getRoutineSetByDate(
        userId: String,
        date: String
    ): Flow<DataState<List<RoutineSet>>> {
        TODO("Not yet implemented")
    }

    override fun getRoutine(userId: String): Flow<DataState<List<Routine>>> {
        TODO("Not yet implemented")
    }

    override fun getRoutineByDate(userId: String): Flow<DataState<List<Routine>>> {
        TODO("Not yet implemented")
    }

    override fun getRoutineByRoutineSet(
        userId: String,
        date: String
    ): Flow<DataState<List<Routine>>> {
        TODO("Not yet implemented")
    }

    override fun createRoutineSet(routineSet: RoutineSet): Flow<DataState<Boolean>> {
        TODO("Not yet implemented")
    }


}