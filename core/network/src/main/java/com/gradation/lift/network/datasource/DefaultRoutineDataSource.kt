package com.gradation.lift.network.datasource

import com.gradation.lift.domain.model.common.*
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
                routineService.createRoutineSet(CreateRoutineSetRequestDto(
                    userId = createRoutineSetRoutine.userId,
                    shortDescription = createRoutineSetRoutine.shortDescription,
                    longDescription = createRoutineSetRoutine.longDescription,
                    repeatType = when (val type = createRoutineSetRoutine.repeatIntervalType) {
                        is RepeatIntervalType.DayType -> type.type
                        is RepeatIntervalType.WeekDayType -> type.type
                    },
                    repeatInterval = when (val type =
                        createRoutineSetRoutine.repeatIntervalType) {
                        is RepeatIntervalType.DayType -> type.interval
                        is RepeatIntervalType.WeekDayType -> when (type.weekday) {
                            is WeekDay.Friday -> FRIDAY
                            is WeekDay.Monday -> MONDAY
                            is WeekDay.Saturday -> SATURDAY
                            is WeekDay.Sunday -> SUNDAY
                            is WeekDay.Thursday -> THURSDAY
                            is WeekDay.Tuesday -> TUESDAY
                            is WeekDay.Wednesday -> WEDNESDAY
                        }
                    },
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
          
                    is APIResult.Success -> emit(APIResult.Success(result.data))
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

    override suspend fun getRoutineSetRoutine(): Flow<APIResult<List<RoutineSetRoutine>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getRoutineSetRoutineByWeekday(weekday: String): Flow<APIResult<List<RoutineSetRoutine>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getRoutineSetRoutineByRoutineSetId(routineSetId: String): Flow<APIResult<List<RoutineSetRoutine>>> {
        TODO("Not yet implemented")
    }






}