package com.gradation.lift.network.datasource

import com.gradation.lift.model.model.common.Weekday
import com.gradation.lift.model.model.routine.CreateRoutineSetRoutine
import com.gradation.lift.model.model.routine.Routine
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.network.common.APIResult
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

    override suspend fun createRoutineSet(createRoutineSetRoutine: CreateRoutineSetRoutine): Flow<APIResult<Boolean>> =
        flow {
            networkResultHandler {
                routineService.createRoutineSet(
                    createRoutineSetRequestDto = createRoutineSetRoutine.toDto()
                )
            }.collect { result ->
                when (result) {
                    is APIResult.Fail -> emit(APIResult.Fail(result.message))

                    is APIResult.Success -> emit(APIResult.Success(result.data.result))
                }
            }
        }


    override suspend fun getRoutine(): Flow<APIResult<List<Routine>>> = flow {
        networkResultHandler { routineService.getRoutine() }
            .collect { result ->
                when (result) {
                    is APIResult.Fail -> emit(APIResult.Fail(result.message))

                    is APIResult.Success -> emit(APIResult.Success(result.data.toDomain()))
                }
            }
    }

    override suspend fun getRoutineSetRoutine(): Flow<APIResult<List<RoutineSetRoutine>>> = flow {
        networkResultHandler { routineService.getRoutineSetRoutine() }
            .collect { result ->
                when (result) {
                    is APIResult.Fail -> emit(APIResult.Fail(result.message))

                    is APIResult.Success -> emit(APIResult.Success(result.data.toDomain()))
                }
            }
    }

    override suspend fun getRoutineSetRoutineByWeekday(weekday: Weekday): Flow<APIResult<List<RoutineSetRoutine>>> =
        flow {
            networkResultHandler { routineService.getRoutineSetRoutineByWeekday(weekday.getWeekdayValue()) }
                .collect { result ->
                    when (result) {
                        is APIResult.Fail -> emit(APIResult.Fail(result.message))

                        is APIResult.Success -> emit(APIResult.Success(result.data.toDomain()))
                    }
                }
        }

    override suspend fun getRoutineSetRoutineByRoutineSetId(routineSetIdList: Set<Int>): Flow<APIResult<List<RoutineSetRoutine>>> =
        flow {
            networkResultHandler {
                routineService.getRoutineSetRoutineByRoutineSetId(
                    routineSetIdList = routineSetIdList.joinToString(",")
                )
            }
                .collect { result ->
                    when (result) {
                        is APIResult.Fail -> emit(APIResult.Fail(result.message))

                        is APIResult.Success -> emit(APIResult.Success(result.data.toDomain()))
                    }
                }
        }


}