package com.gradation.lift.network.datasource

import com.gradation.lift.model.model.common.Weekday
import com.gradation.lift.model.model.routine.CreateRoutineSetRoutine
import com.gradation.lift.model.model.routine.Routine
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.mapper.toDto
import com.gradation.lift.network.service.RoutineService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class DefaultRoutineDataSource @Inject constructor(
    private val routineService: RoutineService,
    private val NetworkResultHandler: NetworkResultHandler,
) : RoutineDataSource {

    override suspend fun createRoutineSet(createRoutineSetRoutine: CreateRoutineSetRoutine): Flow<NetworkResult<Boolean>> =
        flow {
            NetworkResultHandler {
                routineService.createRoutineSet(
                    createRoutineSetRequestDto = createRoutineSetRoutine.toDto()
                )
            }.transform { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))

                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.result))
                }
            }
        }


    override suspend fun getRoutine(): Flow<NetworkResult<List<Routine>>> = flow {
        NetworkResultHandler { routineService.getRoutine() }
            .transform { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))

                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.toDomain()))
                }
            }
    }

    override suspend fun getRoutineSetRoutine(): Flow<NetworkResult<List<RoutineSetRoutine>>> = flow {
        NetworkResultHandler { routineService.getRoutineSetRoutine() }
            .transform { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))

                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.toDomain()))
                }
            }
    }

    override suspend fun getRoutineSetRoutineByWeekday(weekday: Weekday): Flow<NetworkResult<List<RoutineSetRoutine>>> =
        flow {
            NetworkResultHandler { routineService.getRoutineSetRoutineByWeekday(weekday.getWeekdayValue()) }
                .transform { result ->
                    when (result) {
                        is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))

                        is NetworkResult.Success -> emit(NetworkResult.Success(result.data.toDomain()))
                    }
                }
        }

    override suspend fun getRoutineSetRoutineByRoutineSetId(routineSetIdList: Set<Int>): Flow<NetworkResult<List<RoutineSetRoutine>>> =
        flow {
            NetworkResultHandler {
                routineService.getRoutineSetRoutineByRoutineSetId(
                    routineSetIdList = routineSetIdList.joinToString(",")
                )
            }
                .transform { result ->
                    when (result) {
                        is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))

                        is NetworkResult.Success -> emit(NetworkResult.Success(result.data.toDomain()))
                    }
                }
        }


}