package com.gradation.lift.data.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.RoutineRepository
import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.model.routine.CreateRoutineSetRoutine
import com.gradation.lift.model.model.routine.Routine
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.model.model.routine.UpdateRoutineSetRoutine
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.routine.RoutineDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class DefaultRoutineRepository @Inject constructor(
    private val routineDataSource: RoutineDataSource,
) : RoutineRepository {


    override fun createRoutineSetRoutine(createRoutineSetRoutine: CreateRoutineSetRoutine): Flow<DataState<Unit>> =
        flow {
            routineDataSource.createRoutineSetRoutine(createRoutineSetRoutine).collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                    is NetworkResult.Success -> emit(DataState.Success(result.data))
                }
            }
        }

    override fun updateRoutineSetRoutine(updateRoutineSetRoutine: UpdateRoutineSetRoutine): Flow<DataState<Unit>> =
        flow {
            routineDataSource.updateRoutineSetRoutine(updateRoutineSetRoutine).collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                    is NetworkResult.Success -> emit(DataState.Success(result.data))
                }
            }
        }

    override fun deleteRoutineSetRoutine(routineSetId: Int): Flow<DataState<Unit>> = flow {
        routineDataSource.deleteRoutineSetRoutine(routineSetId).collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }

    override fun getRoutine(): Flow<DataState<List<Routine>>> = flow {
        routineDataSource.getRoutine().collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }

    override fun getRoutineSetRoutine(): Flow<DataState<List<RoutineSetRoutine>>> = flow {
        routineDataSource.getRoutineSetRoutine().collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }

    override fun getRoutineSetRoutineByWeekday(weekday: Weekday): Flow<DataState<List<RoutineSetRoutine>>> =
        flow {
            routineDataSource.getRoutineSetRoutineByWeekday(weekday).collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                    is NetworkResult.Success -> emit(DataState.Success(result.data))
                }
            }
        }

    override fun getRoutineSetRoutineByRoutineSetId(routineSetIdList: Set<Int>): Flow<DataState<List<RoutineSetRoutine>>> =
        flow {
            routineDataSource.getRoutineSetRoutineByRoutineSetId(routineSetIdList)
                .collect { result ->
                    when (result) {
                        is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                        is NetworkResult.Success -> emit(DataState.Success(result.data))
                    }
                }
        }


}