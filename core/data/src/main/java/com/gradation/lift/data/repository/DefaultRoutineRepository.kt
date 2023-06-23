package com.gradation.lift.data.repository

import com.gradation.lift.datastore.datasource.DataStoreDataSource
import com.gradation.lift.common.model.DataState
import com.gradation.lift.data.utils.RefreshManager
import com.gradation.lift.domain.repository.RoutineRepository
import com.gradation.lift.model.routine.CreateRoutineSetRoutine
import com.gradation.lift.model.routine.Routine
import com.gradation.lift.model.routine.RoutineSet
import com.gradation.lift.model.routine.RoutineSetRoutine
import com.gradation.lift.network.common.AuthAPIResult
import com.gradation.lift.network.datasource.AuthDataSource
import com.gradation.lift.network.datasource.RoutineDataSource
import com.gradation.lift.network.service.RefreshService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.LocalDate
import javax.inject.Inject


class DefaultRoutineRepository @Inject constructor(
    private val routineDataSource: RoutineDataSource,
    private val refreshManager: RefreshManager,
    private val dataStoreDataSource: DataStoreDataSource,
) : RoutineRepository {
    override fun getRoutineSet(): Flow<DataState<List<RoutineSet>>> = flow {
        routineDataSource.getRoutineSet(userId = "201713721").collect { result ->
            when (result) {
                is AuthAPIResult.Fail -> emit(DataState.Fail(result.message))
                is AuthAPIResult.Error -> emit(DataState.Error(result.exception.toString()))
                is AuthAPIResult.Loading -> emit(DataState.Loading)
                is AuthAPIResult.Success -> emit(DataState.Success(result.data))
                is AuthAPIResult.Refresh -> {
                    emit(refreshManager { routineDataSource.getRoutineSet(userId = "201713721") })
                }
            }
        }
    }

    override fun createRoutineSet(createRoutineSetRoutine: CreateRoutineSetRoutine): Flow<DataState<Boolean>> =
        flow {
            routineDataSource.createRoutineSet(createRoutineSetRoutine).collect { result ->
                when (result) {
                    is AuthAPIResult.Fail -> emit(DataState.Fail(result.message))
                    is AuthAPIResult.Error -> emit(DataState.Error(result.exception.toString()))
                    is AuthAPIResult.Loading -> emit(DataState.Loading)
                    is AuthAPIResult.Success -> emit(DataState.Success(result.data))
                    is AuthAPIResult.Refresh -> {
                        emit(refreshManager {
                            routineDataSource.createRoutineSet(
                                createRoutineSetRoutine
                            )
                        })
                    }
                }
            }
        }

    override fun getRoutineSetByDate(
        date: LocalDate,
    ): Flow<DataState<List<RoutineSet>>> = flow {
        routineDataSource.getRoutineSetByDate(userId = "201713721", date = date).collect { result ->
            when (result) {
                is AuthAPIResult.Fail -> emit(DataState.Fail(result.message))
                is AuthAPIResult.Error -> emit(DataState.Error(result.exception.toString()))
                is AuthAPIResult.Loading -> emit(DataState.Loading)
                is AuthAPIResult.Success -> emit(DataState.Success(result.data))
                is AuthAPIResult.Refresh -> {
                    emit(refreshManager {
                        routineDataSource.getRoutineSetByDate(
                            userId = "201713721", date = date
                        )
                    })
                }
            }
        }
    }

    override fun getRoutineSetByRoutineSetId(
        routineSetId: Int,
    ): Flow<DataState<RoutineSet>> = flow {
        routineDataSource.getRoutineSetByRoutineSetId(
            userId = "201713721", routineSetId = routineSetId
        ).collect { result ->
            when (result) {
                is AuthAPIResult.Fail -> emit(DataState.Fail(result.message))
                is AuthAPIResult.Error -> emit(DataState.Error(result.exception.toString()))
                is AuthAPIResult.Loading -> emit(DataState.Loading)
                is AuthAPIResult.Success -> emit(DataState.Success(result.data))
                is AuthAPIResult.Refresh -> {
                    emit(refreshManager {
                        routineDataSource.getRoutineSetByRoutineSetId(
                            userId = "201713721", routineSetId = routineSetId
                        )
                    })
                }
            }
        }
    }

    override fun getRoutine(): Flow<DataState<List<Routine>>> = flow {
        routineDataSource.getRoutine(userId = "201713721").collect { result ->
            when (result) {
                is AuthAPIResult.Fail -> emit(DataState.Fail(result.message))
                is AuthAPIResult.Error -> emit(DataState.Error(result.exception.toString()))
                is AuthAPIResult.Loading -> emit(DataState.Loading)
                is AuthAPIResult.Success -> emit(DataState.Success(result.data))
                is AuthAPIResult.Refresh -> {
                    emit(refreshManager { routineDataSource.getRoutine(userId = "201713721") })
                }
            }
        }
    }

    override fun getRoutineByDate(date: LocalDate): Flow<DataState<List<Routine>>> = flow {
        routineDataSource.getRoutineByDate(userId = "201713721", date = date).collect { result ->
                when (result) {
                    is AuthAPIResult.Fail -> emit(DataState.Fail(result.message))
                    is AuthAPIResult.Error -> emit(DataState.Error(result.exception.toString()))
                    is AuthAPIResult.Loading -> emit(DataState.Loading)
                    is AuthAPIResult.Success -> emit(DataState.Success(result.data))
                    is AuthAPIResult.Refresh -> {
                        emit(refreshManager {
                            routineDataSource.getRoutineByDate(
                                userId = "201713721", date = date
                            )
                        })
                    }
                }
            }
    }

    override fun getRoutineByRoutineSetId(
        routineSetId: Int,
    ): Flow<DataState<List<Routine>>> = flow {
        routineDataSource.getRoutineByRoutineSetId(
            userId = "201713721", routineSetId = routineSetId
        ).collect { result ->
            when (result) {
                is AuthAPIResult.Fail -> emit(DataState.Fail(result.message))
                is AuthAPIResult.Error -> emit(DataState.Error(result.exception.toString()))
                is AuthAPIResult.Loading -> emit(DataState.Loading)
                is AuthAPIResult.Success -> emit(DataState.Success(result.data))
                is AuthAPIResult.Refresh -> {
                    emit(refreshManager {
                        routineDataSource.getRoutineByRoutineSetId(
                            userId = "201713721", routineSetId = routineSetId
                        )
                    })
                }
            }
        }
    }

    override fun getRoutineByDateAndRoutineSetId(
        date: LocalDate,
        routineSetId: Int,
    ): Flow<DataState<List<Routine>>> = flow {
        routineDataSource.getRoutineByDateAndRoutineSetId(
            userId = "201713721", date = date, routineSetId = routineSetId
        ).collect { result ->
            when (result) {
                is AuthAPIResult.Fail -> emit(DataState.Fail(result.message))
                is AuthAPIResult.Error -> emit(DataState.Error(result.exception.toString()))
                is AuthAPIResult.Loading -> emit(DataState.Loading)
                is AuthAPIResult.Success -> emit(DataState.Success(result.data))
                is AuthAPIResult.Refresh -> {
                    emit(refreshManager {
                        routineDataSource.getRoutineByDateAndRoutineSetId(
                            userId = "201713721", date = date, routineSetId = routineSetId
                        )
                    })
                }
            }
        }
    }

    override fun getRoutineSetRoutine(): Flow<DataState<List<RoutineSetRoutine>>> = flow {
        routineDataSource.getRoutineSetRoutine(userId = "201713721").collect { result ->
            when (result) {
                is AuthAPIResult.Fail -> emit(DataState.Fail(result.message))
                is AuthAPIResult.Error -> emit(DataState.Error(result.exception.toString()))
                is AuthAPIResult.Loading -> emit(DataState.Loading)
                is AuthAPIResult.Success -> emit(DataState.Success(result.data))
                is AuthAPIResult.Refresh -> {
                    emit(refreshManager { routineDataSource.getRoutineSetRoutine(userId = "201713721") })
                }
            }
        }
    }

    override fun getRoutineSetRoutineByDate(date: LocalDate): Flow<DataState<List<RoutineSetRoutine>>> =
        flow {
            routineDataSource.getRoutineSetRoutineByDate(userId = "201713721", date = date)
                .collect { result ->
                    when (result) {
                        is AuthAPIResult.Fail -> emit(DataState.Fail(result.message))
                        is AuthAPIResult.Error -> emit(DataState.Error(result.exception.toString()))
                        is AuthAPIResult.Loading -> emit(DataState.Loading)
                        is AuthAPIResult.Success -> {
                            emit(DataState.Success(result.data))
                        }
                        is AuthAPIResult.Refresh -> {
                            emit(refreshManager {
                                routineDataSource.getRoutineSetRoutineByDate(
                                    userId = "201713721", date = date
                                )
                            })
                        }

                    }
                }
        }


}