package com.gradation.lift.network.datasource

import com.gradation.lift.domain.model.common.*
import com.gradation.lift.domain.model.routine.CreateRoutineSetRoutine
import com.gradation.lift.domain.model.routine.Routine
import com.gradation.lift.domain.model.routine.RoutineSet
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.common.NetworkResultHandler
import com.gradation.lift.network.dto.routine.CreateRoutineDto
import com.gradation.lift.network.dto.routine.CreateRoutineSetRequestDto
import com.gradation.lift.network.service.RoutineService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDate
import javax.inject.Inject

class DefaultRoutineDataSource @Inject constructor(
    private val routineService: RoutineService,
    private val networkResultHandler: NetworkResultHandler
) : RoutineDataSource {
    override suspend fun getRoutineSet(userId: String): Flow<APIResult<List<RoutineSet>>> = flow {
        networkResultHandler.execute { routineService.getRoutineSet(userId) }.collect { result ->
            when (result) {
                is APIResult.Fail -> emit(APIResult.Fail(result.message))
                is APIResult.Error -> emit(APIResult.Error(result.exception))
                is APIResult.Loading -> emit(APIResult.Loading)
                is APIResult.Success -> emit(APIResult.Success(result.data.toRoutineSet()))
            }
        }
    }

    override suspend fun createRoutineSet(createRoutineSetRoutine: CreateRoutineSetRoutine): Flow<APIResult<Boolean>> =
        flow {
            networkResultHandler.execute {
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
                    is APIResult.Fail -> emit(APIResult.Fail( result.message))
                    is APIResult.Error -> emit(APIResult.Error(result.exception))
                    is APIResult.Loading -> emit(APIResult.Loading)
                    is APIResult.Success -> emit(APIResult.Success(result.data))
                }
            }
        }

    override suspend fun getRoutineSetByDate(
        userId: String,
        date: LocalDate
    ): Flow<APIResult<List<RoutineSet>>> = flow {
        networkResultHandler.execute {
            routineService.getRoutineSetByDate(
                userId = userId,
                date = date
            )
        }.collect { result ->
            when (result) {
                is APIResult.Fail -> emit(APIResult.Fail(result.message))
                is APIResult.Error -> emit(APIResult.Error(result.exception))
                is APIResult.Loading -> emit(APIResult.Loading)
                is APIResult.Success -> emit(APIResult.Success(result.data.toRoutineSet()))
            }
        }
    }

    override suspend fun getRoutineSetByRoutineSetId(
        userId: String,
        routineSetId: Int
    ): Flow<APIResult<RoutineSet>> = flow {
        networkResultHandler.execute {
            routineService.getRoutineSetByRoutineSetId(
                userId = userId,
                routineSetId = routineSetId
            )
        }.collect { result ->
            when (result) {
                is APIResult.Fail -> emit(APIResult.Fail( result.message))
                is APIResult.Error -> emit(APIResult.Error(result.exception))
                is APIResult.Loading -> emit(APIResult.Loading)
                is APIResult.Success -> emit(APIResult.Success(result.data.toRoutineSet()))
            }
        }
    }

    override suspend fun getRoutine(userId: String): Flow<APIResult<List<Routine>>> = flow {
        networkResultHandler.execute { routineService.getRoutine(userId = userId) }
            .collect { result ->
                when (result) {
                    is APIResult.Fail -> emit(APIResult.Fail( result.message))
                    is APIResult.Error -> emit(APIResult.Error(result.exception))
                    is APIResult.Loading -> emit(APIResult.Loading)
                    is APIResult.Success -> emit(APIResult.Success(result.data.toRoutine()))
                }
            }
    }

    override suspend fun getRoutineByDate(
        userId: String,
        date: LocalDate
    ): Flow<APIResult<List<Routine>>> = flow {
        networkResultHandler.execute {
            routineService.getRoutineByDate(
                userId = userId,
                date = date
            )
        }.collect { result ->
            when (result) {
                is APIResult.Fail -> emit(APIResult.Fail( result.message))
                is APIResult.Error -> emit(APIResult.Error(result.exception))
                is APIResult.Loading -> emit(APIResult.Loading)
                is APIResult.Success -> emit(APIResult.Success(result.data.toRoutine()))
            }
        }
    }

    override suspend fun getRoutineByRoutineSetId(
        userId: String,
        routineSetId: Int
    ): Flow<APIResult<List<Routine>>> = flow {
        networkResultHandler.execute {
            routineService.getRoutineByRoutineSetId(
                userId = userId,
                routineSetId = routineSetId
            )
        }.collect { result ->
            when (result) {
                is APIResult.Fail -> emit(APIResult.Fail( result.message))
                is APIResult.Error -> emit(APIResult.Error(result.exception))
                is APIResult.Loading -> emit(APIResult.Loading)
                is APIResult.Success -> emit(APIResult.Success(result.data.toRoutine()))
            }
        }
    }

    override suspend fun getRoutineByDateAndRoutineSetId(
        userId: String,
        date: LocalDate,
        routineSetId: Int
    ): Flow<APIResult<List<Routine>>> = flow {
        networkResultHandler.execute {
            routineService.getRoutineByDateAndRoutineSetId(
                userId = userId,
                date = date,
                routineSetId = routineSetId
            )
        }.collect { result ->
            when (result) {
                is APIResult.Fail -> emit(APIResult.Fail( result.message))
                is APIResult.Error -> emit(APIResult.Error(result.exception))
                is APIResult.Loading -> emit(APIResult.Loading)
                is APIResult.Success -> emit(APIResult.Success(result.data.toRoutine()))
            }
        }
    }

}