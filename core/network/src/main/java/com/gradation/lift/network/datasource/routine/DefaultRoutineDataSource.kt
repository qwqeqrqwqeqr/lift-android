package com.gradation.lift.network.datasource.routine

import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.model.routine.CreateRoutineSetRoutine
import com.gradation.lift.model.model.routine.Label
import com.gradation.lift.model.model.routine.Routine
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.model.model.routine.UpdateRoutineSetCount
import com.gradation.lift.model.model.routine.UpdateRoutineSetRoutine
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

    override suspend fun createRoutineSetRoutine(createRoutineSetRoutine: CreateRoutineSetRoutine): Flow<NetworkResult<Unit>> =
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

    override suspend fun deleteRoutineSetRoutine(routineSetId: Int): Flow<NetworkResult<Unit>> =
        flow {
            networkResultHandler {
                routineService.deleteRoutineSetRoutine(routineSetId)
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))

                    is NetworkResult.Success -> emit(NetworkResult.Success(Unit))
                }
            }
        }


    override suspend fun updateRoutineSetRoutine(updateRoutineSetRoutine: UpdateRoutineSetRoutine): Flow<NetworkResult<Unit>> =
        flow {
            networkResultHandler {
                routineService.updateRoutineSetRoutine(
                    updateRoutineSetRoutine.toDto()
                )
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))

                    is NetworkResult.Success -> emit(NetworkResult.Success(Unit))
                }
            }
        }

    override suspend fun updateRoutineSetCount(updateRoutineSetCount: UpdateRoutineSetCount): Flow<NetworkResult<Unit>> =
        flow {
            networkResultHandler { routineService.updateRoutineSetCount(updateRoutineSetCount.toDto()) }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))

                    is NetworkResult.Success -> emit(NetworkResult.Success(Unit))
                }
            }
        }


    override suspend fun getRoutine(): Flow<NetworkResult<List<Routine>>> = flow {
        networkResultHandler { routineService.getRoutine() }.collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))

                is NetworkResult.Success -> emit(NetworkResult.Success(result.data.toDomain()))
            }
        }
    }

    override suspend fun getRoutineSetRoutine(): Flow<NetworkResult<List<RoutineSetRoutine>>> =
        flow {
            networkResultHandler { routineService.getRoutineSetRoutine() }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))

                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.toDomain()))
                }
            }
        }


    override suspend fun getRoutineSetRoutineByWeekday(weekday: Set<Weekday>): Flow<NetworkResult<List<RoutineSetRoutine>>> =
        flow {
            networkResultHandler {
                routineService.getRoutineSetRoutineByWeekday(
                    weekday.joinToString(
                        ","
                    )
                )
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))

                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.toDomain()))
                }
            }
        }

    override suspend fun getRoutineSetRoutineByLabel(label: Set<Label>): Flow<NetworkResult<List<RoutineSetRoutine>>> =
        flow {
            networkResultHandler { routineService.getRoutineSetRoutineByLabel(label.joinToString(",")) }.collect { result ->
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
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))

                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.toDomain()))
                }
            }
        }


}