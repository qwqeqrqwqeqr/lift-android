package com.gradation.lift.data.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.RoutineRepository
import com.gradation.lift.model.model.common.Weekday
import com.gradation.lift.model.model.routine.CreateRoutineSetRoutine
import com.gradation.lift.model.model.routine.Routine
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.RoutineDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject


class DefaultRoutineRepository @Inject constructor(
    private val routineDataSource: RoutineDataSource,
) : RoutineRepository {


    override  fun createRoutineSet(createRoutineSetRoutine: CreateRoutineSetRoutine) : Flow<DataState<Boolean>> = flow{
        routineDataSource.createRoutineSet(createRoutineSetRoutine).transform { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }

    override  fun getRoutine(): Flow<DataState<List<Routine>>> = flow{
        routineDataSource.getRoutine().transform { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }

    override  fun getRoutineSetRoutine(): Flow<DataState<List<RoutineSetRoutine>>> = flow{
        routineDataSource.getRoutineSetRoutine().transform { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }

    override  fun getRoutineSetRoutineByWeekday(weekday: Weekday): Flow<DataState<List<RoutineSetRoutine>>> = flow{
        routineDataSource.getRoutineSetRoutineByWeekday(weekday).transform { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }

    override  fun getRoutineSetRoutineByRoutineSetId(routineSetIdList: Set<Int>): Flow<DataState<List<RoutineSetRoutine>>> = flow{
        routineDataSource.getRoutineSetRoutineByRoutineSetId(routineSetIdList).transform { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }


}