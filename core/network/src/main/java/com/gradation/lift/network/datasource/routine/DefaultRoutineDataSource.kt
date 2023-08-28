package com.gradation.lift.network.datasource.routine

import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.model.routine.CreateRoutineSetRoutine
import com.gradation.lift.model.model.routine.Routine
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.mapper.toDto
import com.gradation.lift.network.service.RoutineService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultRoutineDataSource @Inject constructor(
    private val routineService: RoutineService,
    private val networkResultHandler: NetworkResultHandler,
) : RoutineDataSource {

    override suspend fun createRoutineSet(createRoutineSetRoutine: CreateRoutineSetRoutine): Flow<NetworkResult<Unit>> =
        flow {
            networkResultHandler {
                routineService.createRoutineSetRoutine(
                    createRoutineSetRoutineRequestDto = createRoutineSetRoutine.toDto()
                )
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))

                    is NetworkResult.Success -> emit(NetworkResult.Success(Unit))
                }
            }
        }


    override suspend fun getRoutine(): Flow<NetworkResult<List<Routine>>> = flow {
        networkResultHandler { routineService.getRoutine() }
            .collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))

                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.toDomain()))
                }
            }
    }

    override suspend fun getRoutineSetRoutine(): Flow<NetworkResult<List<RoutineSetRoutine>>> = flow {
        networkResultHandler { routineService.getRoutineSetRoutine() }
            .collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))

                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.toDomain()))
                }
            }
    }

    override suspend fun getRoutineSetRoutineByWeekday(weekday: Weekday): Flow<NetworkResult<List<RoutineSetRoutine>>> =
        flow {
            networkResultHandler { routineService.getRoutineSetRoutineByWeekday(weekday.getWeekdayValue()) }
                .collect { result ->
                    when (result) {
                        is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))

                        is NetworkResult.Success -> emit(NetworkResult.Success(result.data.toDomain()))
                    }
                }
        }

    override suspend fun getRoutineSetRoutineByRoutineSetId(routineSetIdList: Set<Int>): Flow<NetworkResult<List<RoutineSetRoutine>>> =
        flow {
            networkResultHandler {
                routineService.getRoutineSetRoutineByRoutineSetId(
                    routineSetIdList = routineSetIdList.joinToString(",")
                )
            }
                .collect { result ->
                    when (result) {
                        is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))

                        is NetworkResult.Success -> emit(NetworkResult.Success(result.data.toDomain()))
                    }
                }
        }


}