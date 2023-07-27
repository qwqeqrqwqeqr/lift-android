package com.gradation.lift.network.datasource

import com.gradation.lift.model.common.Weekday
import com.gradation.lift.model.routine.*
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.dto.routine.CreateRoutineDto
import com.gradation.lift.network.dto.routine.CreateRoutineSetRequestDto
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
                    CreateRoutineSetRequestDto(
                        name = createRoutineSetRoutine.name,
                        description = createRoutineSetRoutine.description,
                        weekday = createRoutineSetRoutine.weekday.map { it.getWeekdayValue() },
                        picture = createRoutineSetRoutine.picture,
                        routine = createRoutineSetRoutine.routine.map { routine ->
                            CreateRoutineDto(
                                workCategory = routine.workCategory,
                                workWeightList = routine.workSetList.map { it.weight },
                                workRepetitionList = routine.workSetList.map { it.repetition }
                            )
                        }
                    ))
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

                    is APIResult.Success -> emit(APIResult.Success(result.data.toRoutine()))
                }
            }
    }

    override suspend fun getRoutineSetRoutine(): Flow<APIResult<List<RoutineSetRoutine>>> = flow {
        networkResultHandler { routineService.getRoutineSetRoutine() }
            .collect { result ->
                when (result) {
                    is APIResult.Fail -> emit(APIResult.Fail(result.message))

                    is APIResult.Success -> emit(APIResult.Success(result.data.toRoutineSetRoutine()))
                }
            }
    }

    override suspend fun getRoutineSetRoutineByWeekday(weekday: Weekday): Flow<APIResult<List<RoutineSetRoutine>>> =
        flow {
            networkResultHandler { routineService.getRoutineSetRoutineByWeekday(weekday.getWeekdayValue()) }
                .collect { result ->
                    when (result) {
                        is APIResult.Fail -> emit(APIResult.Fail(result.message))

                        is APIResult.Success -> emit(APIResult.Success(result.data.toRoutineSetRoutine()))
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

                        is APIResult.Success -> emit(APIResult.Success(result.data.toRoutineSetRoutine()))
                    }
                }
        }


}