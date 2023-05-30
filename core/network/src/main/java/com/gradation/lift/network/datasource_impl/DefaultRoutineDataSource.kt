package com.gradation.lift.network.datasource_impl

import com.gradation.lift.domain.model.routine.Routine
import com.gradation.lift.domain.model.routine.RoutineSet
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.common.NetworkResultHandler
import com.gradation.lift.network.datasource.RoutineDataSource
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
    override suspend fun getRoutineSet(userId: String): Flow<APIResult<List<RoutineSet>>> =flow{
        networkResultHandler.execute { routineService.getRoutineSet(userId) }.collect { result ->
            when (result) {
                is APIResult.Fail -> emit(APIResult.Fail(emptyList(),result.message))
                is APIResult.Error -> emit(APIResult.Error(result.exception))
                is APIResult.Loading -> emit(APIResult.Loading)
                is APIResult.Success -> emit(APIResult.Success(result.data.toRoutineSet()))
            }
        }
    }

    override suspend fun createRoutineSet(createRoutineSetRequestDto: CreateRoutineSetRequestDto): Flow<APIResult<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun getRoutineSetByDate(
        userId: String,
        date: LocalDate
    ): Flow<APIResult<List<RoutineSet>>> = flow{
        networkResultHandler.execute { routineService.getRoutineSetByDate(userId= userId,date= date) }.collect { result ->
            when (result) {
                is APIResult.Fail -> emit(APIResult.Fail(emptyList(),result.message))
                is APIResult.Error -> emit(APIResult.Error(result.exception))
                is APIResult.Loading -> emit(APIResult.Loading)
                is APIResult.Success -> emit(APIResult.Success(result.data.toRoutineSet()))
            }
        }
    }

    override suspend fun getRoutineSetByRoutineSetId(
        userId: String,
        routineSetId: Int
    ): Flow<APIResult<RoutineSet>> = flow{
        networkResultHandler.execute { routineService.getRoutineSetByRoutineSetId(userId= userId,routineSetId= routineSetId) }.collect { result ->
            when (result) {
                is APIResult.Fail -> emit(APIResult.Fail(null,result.message))
                is APIResult.Error -> emit(APIResult.Error(result.exception))
                is APIResult.Loading -> emit(APIResult.Loading)
                is APIResult.Success -> emit(APIResult.Success(result.data.toRoutineSet()))
            }
        }
    }

    override suspend fun getRoutine(userId: String): Flow<APIResult<List<Routine>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getRoutineByDateResponseDto(
        userId: String,
        date: LocalDate
    ): Flow<APIResult<List<Routine>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getRoutineByRoutineSetIdResponseDto(
        userId: String,
        routineSetId: Int
    ): Flow<APIResult<List<Routine>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getRoutineByDateAndRoutineSetIdResponseDto(
        userId: String,
        date: LocalDate,
        routineSetId: Int
    ): Flow<APIResult<List<Routine>>> {
        TODO("Not yet implemented")
    }

}