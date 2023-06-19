package com.gradation.lift.network.fake

import com.gradation.lift.domain.model.routine.CreateRoutineSetRoutine
import com.gradation.lift.domain.model.routine.Routine
import com.gradation.lift.domain.model.routine.RoutineSet
import com.gradation.lift.domain.model.routine.RoutineSetRoutine
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.datasource.RoutineDataSource
import com.gradation.lift.network.utils.TestDataGenerator
import com.gradation.lift.network.utils.TestReturnState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.LocalDate

class FakeRoutineDataSource(private val testReturnState: TestReturnState = TestReturnState.Success) : RoutineDataSource{
    override suspend fun getRoutineSet(userId: String): Flow<APIResult<List<RoutineSet>>> = flow{
        when(testReturnState){
            TestReturnState.Error -> emit(APIResult.Error(Throwable(message = "통신 에러")))
            TestReturnState.Fail -> emit(APIResult.Fail("존재 하지 않는 값"))
            TestReturnState.Loading -> emit(APIResult.Loading)
            TestReturnState.Success ->  emit(APIResult.Success(data = TestDataGenerator.getRoutineSetDto.toRoutineSet()))
        }
    }

    override suspend fun createRoutineSet(createRoutineSetRoutine: CreateRoutineSetRoutine): Flow<APIResult<Boolean>> = flow {
        when(testReturnState){
            TestReturnState.Error -> emit(APIResult.Error(Throwable(message = "통신 에러")))
            TestReturnState.Fail -> emit(APIResult.Fail("존재 하지 않는 값"))
            TestReturnState.Loading -> emit(APIResult.Loading)
            TestReturnState.Success ->  emit(APIResult.Success(data = true))
        }
    }

    override suspend fun getRoutineSetByDate(
        userId: String,
        date: LocalDate
    ): Flow<APIResult<List<RoutineSet>>> = flow{
        when(testReturnState){
            TestReturnState.Error -> emit(APIResult.Error(Throwable(message = "통신 에러")))
            TestReturnState.Fail -> emit(APIResult.Fail("존재 하지 않는 값"))
            TestReturnState.Loading -> emit(APIResult.Loading)
            TestReturnState.Success ->  emit(APIResult.Success(data = TestDataGenerator.getRoutineSetByDateDto.toRoutineSet()))
        }
    }

    override suspend fun getRoutineSetByRoutineSetId(
        userId: String,
        routineSetId: Int
    ): Flow<APIResult<RoutineSet>> = flow{
        when(testReturnState){
            TestReturnState.Error -> emit(APIResult.Error(Throwable(message = "통신 에러")))
            TestReturnState.Fail -> emit(APIResult.Fail("존재 하지 않는 값"))
            TestReturnState.Loading -> emit(APIResult.Loading)
            TestReturnState.Success ->  emit(APIResult.Success(data = TestDataGenerator.getRoutineSetByRoutineSetIdDto.toRoutineSet()))
        }
    }

    override suspend fun getRoutine(userId: String): Flow<APIResult<List<Routine>>> = flow{
        when(testReturnState){
            TestReturnState.Error -> emit(APIResult.Error(Throwable(message = "통신 에러")))
            TestReturnState.Fail -> emit(APIResult.Fail("존재 하지 않는 값"))
            TestReturnState.Loading -> emit(APIResult.Loading)
            TestReturnState.Success ->  emit(APIResult.Success(data = TestDataGenerator.getRoutineDto.toRoutine()))
        }
    }

    override suspend fun getRoutineByDate(
        userId: String,
        date: LocalDate
    ): Flow<APIResult<List<Routine>>> = flow{
        when(testReturnState){
            TestReturnState.Error -> emit(APIResult.Error(Throwable(message = "통신 에러")))
            TestReturnState.Fail -> emit(APIResult.Fail("존재 하지 않는 값"))
            TestReturnState.Loading -> emit(APIResult.Loading)
            TestReturnState.Success ->  emit(APIResult.Success(data = TestDataGenerator.getRoutineByDateDto.toRoutine()))
        }
    }

    override suspend fun getRoutineByRoutineSetId(
        userId: String,
        routineSetId: Int
    ): Flow<APIResult<List<Routine>>> = flow{
        when(testReturnState){
            TestReturnState.Error -> emit(APIResult.Error(Throwable(message = "통신 에러")))
            TestReturnState.Fail -> emit(APIResult.Fail("존재 하지 않는 값"))
            TestReturnState.Loading -> emit(APIResult.Loading)
            TestReturnState.Success ->  emit(APIResult.Success(data = TestDataGenerator.getRoutineByRoutineSetIdDto.toRoutine()))
        }
    }

    override suspend fun getRoutineByDateAndRoutineSetId(
        userId: String,
        date: LocalDate,
        routineSetId: Int
    ): Flow<APIResult<List<Routine>>> = flow{
        when(testReturnState){
            TestReturnState.Error -> emit(APIResult.Error(Throwable(message = "통신 에러")))
            TestReturnState.Fail -> emit(APIResult.Fail("존재 하지 않는 값"))
            TestReturnState.Loading -> emit(APIResult.Loading)
            TestReturnState.Success ->  emit(APIResult.Success(data = TestDataGenerator.getRoutineByDateAndRoutineSetIdDto.toRoutine()))
        }
    }

    override suspend fun getRoutineSetRoutine(userId: String): Flow<APIResult<List<RoutineSetRoutine>>> = flow {
        when (testReturnState) {
            TestReturnState.Error -> emit(APIResult.Error(Throwable(message = "통신 에러")))
            TestReturnState.Fail -> emit(APIResult.Fail("존재 하지 않는 값"))
            TestReturnState.Loading -> emit(APIResult.Loading)
            TestReturnState.Success -> emit(APIResult.Success(data = TestDataGenerator.getRoutineSetRoutineDto.toRoutineSetRoutine()))
        }
    }
    override suspend fun getRoutineSetRoutineByDate(
        userId: String,
        date: LocalDate
    ): Flow<APIResult<List<RoutineSetRoutine>>> = flow {
        when (testReturnState) {
            TestReturnState.Error -> emit(APIResult.Error(Throwable(message = "통신 에러")))
            TestReturnState.Fail -> emit(APIResult.Fail("존재 하지 않는 값"))
            TestReturnState.Loading -> emit(APIResult.Loading)
            TestReturnState.Success -> emit(APIResult.Success(data = TestDataGenerator.getRoutineSetRoutineByDateDto.toRoutineSetRoutine()))
        }
    }

}