package com.gradation.lift.network.datasource

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
                        shortDescription = createRoutineSetRoutine.shortDescription,
                        longDescription = createRoutineSetRoutine.longDescription,
                        weekday = createRoutineSetRoutine.weekday.getName(),
                        routine = createRoutineSetRoutine.routine.map { routine ->
                            CreateRoutineDto(
                                workCategory = routine.workCategoryId,
                                workWeightList = routine.workSet.map { it.weight }.toList(),
                                workRepetitionList = routine.workSet.map { it.repetition }.toList()
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

    override suspend fun getRoutineSetRoutineByWeekday(weekday: String): Flow<APIResult<List<RoutineSetRoutine>>> =
        flow {
            networkResultHandler { routineService.getRoutineSetRoutineByWeekday(weekday) }
                .collect { result ->
                    when (result) {
                        is APIResult.Fail -> emit(APIResult.Fail(result.message))

                        is APIResult.Success -> emit(APIResult.Success(result.data.toRoutineSetRoutine()))
                    }
                }
        }

    override suspend fun getRoutineSetRoutineByRoutineSetId(routineSetId: String): Flow<APIResult<List<RoutineSetRoutine>>> =
        flow {
            networkResultHandler { routineService.getRoutineSetRoutineByRoutineSetId(routineSetId) }
                .collect { result ->
                    when (result) {
                        is APIResult.Fail -> emit(APIResult.Fail(result.message))

                        is APIResult.Success -> emit(APIResult.Success(result.data.toRoutineSetRoutine()))
                    }
                }
        }


}