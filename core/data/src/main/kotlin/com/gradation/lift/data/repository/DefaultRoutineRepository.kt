package com.gradation.lift.data.repository

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.common.model.DataState
import com.gradation.lift.data.utils.ErrorMessage
import com.gradation.lift.database.datasource.routine.RoutineLocalDataSource
import com.gradation.lift.domain.repository.RoutineRepository
import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.model.routine.CreateRoutineSetRoutine
import com.gradation.lift.model.model.routine.Label
import com.gradation.lift.model.model.routine.Routine
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.model.model.routine.UpdateRoutineSetRoutine
import com.gradation.lift.model.model.routine.UpdateUsedRoutineSet
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.routine.RoutineRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class DefaultRoutineRepository @Inject constructor(
    private val routineRemoteDataSource: RoutineRemoteDataSource,
    private val routineLocalDataSource: RoutineLocalDataSource,
    private val dispatcherProvider: DispatcherProvider,
) : RoutineRepository {


    override fun createRoutineSetRoutine(createRoutineSetRoutine: CreateRoutineSetRoutine): Flow<DataState<Unit>> =
        flow {
            routineRemoteDataSource.createRoutineSetRoutine(createRoutineSetRoutine)
                .collect { result ->
                    when (result) {
                        is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                        is NetworkResult.Success -> emit(DataState.Success(result.data))
                    }
                }
        }.flowOn(dispatcherProvider.default)

    override fun updateRoutineSetRoutine(updateRoutineSetRoutine: UpdateRoutineSetRoutine): Flow<DataState<Unit>> =
        flow {
            routineRemoteDataSource.updateRoutineSetRoutine(updateRoutineSetRoutine)
                .collect { result ->
                    when (result) {
                        is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                        is NetworkResult.Success -> emit(DataState.Success(result.data))
                    }
                }
        }.flowOn(dispatcherProvider.default)

    override fun updateUsedRoutineSet(updateUsedRoutineSet: UpdateUsedRoutineSet): Flow<DataState<Unit>> =
        flow {
            routineRemoteDataSource.updateUsedRoutineSet(updateUsedRoutineSet).collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                    is NetworkResult.Success -> emit(DataState.Success(result.data))
                }
            }
        }.flowOn(dispatcherProvider.default)

    override fun deleteRoutineSetRoutine(routineSetId: Int): Flow<DataState<Unit>> = flow {
        routineRemoteDataSource.deleteRoutineSetRoutine(routineSetId).collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }.flowOn(dispatcherProvider.default)

    override fun getRoutine(): Flow<DataState<List<Routine>>> = flow {
        routineRemoteDataSource.getRoutine().distinctUntilChanged().collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }.flowOn(dispatcherProvider.default)

    override fun getRoutineSetRoutine(): Flow<DataState<List<RoutineSetRoutine>>> = flow {
        routineRemoteDataSource.getRoutineSetRoutine().collect { result ->
            when (result) {
                is NetworkResult.Fail -> {
                    try {
                        routineLocalDataSource.getAllRoutineSetRoutine().collect {
                            emit(DataState.Success(it))
                        }
                    } catch (error: Throwable) {
                        emit(DataState.Fail(ErrorMessage.CACHE_ERROR_MESSAGE))
                    }
                }

                is NetworkResult.Success -> {
                    try {
                        routineLocalDataSource.fetch(result.data)
                        emit(DataState.Success(result.data))
                    } catch (error: Throwable) {
                        emit(DataState.Fail(ErrorMessage.CACHE_ERROR_MESSAGE))
                    }
                }
            }
        }
    }.flowOn(dispatcherProvider.default)

    override fun getRoutineSetRoutineByRecent(): Flow<DataState<List<RoutineSetRoutine>>> = flow {
        routineRemoteDataSource.getRoutineSetRoutineByRecent().distinctUntilChanged()
            .collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                    is NetworkResult.Success -> emit(DataState.Success(result.data))
                }
            }
    }.flowOn(dispatcherProvider.default)

    override fun getRoutineSetRoutineByWeekday(weekday: Set<Weekday>): Flow<DataState<List<RoutineSetRoutine>>> =
        flow {
            routineRemoteDataSource.getRoutineSetRoutineByWeekday(weekday).collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                    is NetworkResult.Success -> emit(DataState.Success(result.data))
                }
            }
        }.flowOn(dispatcherProvider.default)

    override fun getRoutineSetRoutineByLabel(label: Set<Label>): Flow<DataState<List<RoutineSetRoutine>>> =
        flow {
            routineRemoteDataSource.getRoutineSetRoutineByLabel(label).collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                    is NetworkResult.Success -> emit(DataState.Success(result.data))
                }
            }
        }.flowOn(dispatcherProvider.default)


    override fun getRoutineSetRoutineByRoutineSetId(routineSetIdList: Set<Int>): Flow<DataState<List<RoutineSetRoutine>>> =
        flow {
            if (routineSetIdList.isEmpty())
                emit(DataState.Fail("불러올 수 있는 루틴이 존재하지 않습니다."))
            else
                routineRemoteDataSource.getRoutineSetRoutineByRoutineSetId(routineSetIdList)
                    .collect { result ->
                        when (result) {
                            is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                            is NetworkResult.Success -> emit(DataState.Success(result.data))
                        }
                    }
        }.flowOn(dispatcherProvider.default)


}