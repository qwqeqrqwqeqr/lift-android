package com.gradation.lift.network.fake

import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.datasource.RoutineDataSource
import com.gradation.lift.network.data.TestDtoDataGenerator
import com.gradation.lift.network.utils.TestReturnState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.LocalDate

class FakeRoutineDataSource(private val testReturnState: TestReturnState = TestReturnState.Success) : RoutineDataSource{
    override suspend fun getRoutineSet(userId: String): Flow<APIResult<List<com.gradation.lift.model.routine.RoutineSet>>> = flow{
        when(testReturnState){
            TestReturnState.Error -> emit(APIResult.Error(Throwable(message = "통신 에러")))
            TestReturnState.Fail -> emit(APIResult.Fail("존재 하지 않는 값"))
            TestReturnState.Success ->  emit(APIResult.Success(data = TestDtoDataGenerator.getRoutineSetDto.toRoutineSet()))
        }
    }

    override suspend fun createRoutineSet(createRoutineSetRoutine: com.gradation.lift.model.routine.CreateRoutineSetRoutine): Flow<APIResult<Boolean>> = flow {
        when(testReturnState){
            TestReturnState.Error -> emit(APIResult.Error(Throwable(message = "통신 에러")))
            TestReturnState.Fail -> emit(APIResult.Fail("존재 하지 않는 값"))
            TestReturnState.Success ->  emit(APIResult.Success(data = true))
        }
    }

    override suspend fun getRoutineSetByDate(
        userId: String,
        date: LocalDate
    ): Flow<APIResult<List<com.gradation.lift.model.routine.RoutineSet>>> = flow{
        when(testReturnState){
            TestReturnState.Error -> emit(APIResult.Error(Throwable(message = "통신 에러")))
            TestReturnState.Fail -> emit(APIResult.Fail("존재 하지 않는 값"))
            TestReturnState.Success ->  emit(APIResult.Success(data = TestDtoDataGenerator.getRoutineSetByDateDto.toRoutineSet()))
        }
    }

    override suspend fun getRoutineSetByRoutineSetId(
        userId: String,
        routineSetId: Int
    ): Flow<APIResult<com.gradation.lift.model.routine.RoutineSet>> = flow{
        when(testReturnState){
            TestReturnState.Error -> emit(APIResult.Error(Throwable(message = "통신 에러")))
            TestReturnState.Fail -> emit(APIResult.Fail("존재 하지 않는 값"))
            TestReturnState.Success ->  emit(APIResult.Success(data = TestDtoDataGenerator.getRoutineSetByRoutineSetIdDto.toRoutineSet()))
        }
    }

    override suspend fun getRoutine(userId: String): Flow<APIResult<List<com.gradation.lift.model.routine.Routine>>> = flow{
        when(testReturnState){
            TestReturnState.Error -> emit(APIResult.Error(Throwable(message = "통신 에러")))
            TestReturnState.Fail -> emit(APIResult.Fail("존재 하지 않는 값"))
            TestReturnState.Success ->  emit(APIResult.Success(data = TestDtoDataGenerator.getRoutineDto.toRoutine()))
        }
    }

    override suspend fun getRoutineByDate(
        userId: String,
        date: LocalDate
    ): Flow<APIResult<List<com.gradation.lift.model.routine.Routine>>> = flow{
        when(testReturnState){
            TestReturnState.Error -> emit(APIResult.Error(Throwable(message = "통신 에러")))
            TestReturnState.Fail -> emit(APIResult.Fail("존재 하지 않는 값"))
            TestReturnState.Success ->  emit(APIResult.Success(data = TestDtoDataGenerator.getRoutineByDateDto.toRoutine()))
        }
    }

    override suspend fun getRoutineByRoutineSetId(
        userId: String,
        routineSetId: Int
    ): Flow<APIResult<List<com.gradation.lift.model.routine.Routine>>> = flow{
        when(testReturnState){
            TestReturnState.Error -> emit(APIResult.Error(Throwable(message = "통신 에러")))
            TestReturnState.Fail -> emit(APIResult.Fail("존재 하지 않는 값"))
            TestReturnState.Success ->  emit(APIResult.Success(data = TestDtoDataGenerator.getRoutineByRoutineSetIdDto.toRoutine()))
        }
    }

    override suspend fun getRoutineByDateAndRoutineSetId(
        userId: String,
        date: LocalDate,
        routineSetId: Int
    ): Flow<APIResult<List<com.gradation.lift.model.routine.Routine>>> = flow{
        when(testReturnState){
            TestReturnState.Error -> emit(APIResult.Error(Throwable(message = "통신 에러")))
            TestReturnState.Fail -> emit(APIResult.Fail("존재 하지 않는 값"))
            TestReturnState.Success ->  emit(APIResult.Success(data = TestDtoDataGenerator.getRoutineByDateAndRoutineSetIdDto.toRoutine()))
        }
    }

    override suspend fun getRoutineSetRoutine(userId: String): Flow<APIResult<List<com.gradation.lift.model.routine.RoutineSetRoutine>>> = flow {
        when (testReturnState) {
            TestReturnState.Error -> emit(APIResult.Error(Throwable(message = "통신 에러")))
            TestReturnState.Fail -> emit(APIResult.Fail("존재 하지 않는 값"))
            TestReturnState.Success -> emit(APIResult.Success(data = TestDtoDataGenerator.getRoutineSetRoutineDto.toRoutineSetRoutine()))
        }
    }
    override suspend fun getRoutineSetRoutineByDate(
        userId: String,
        date: LocalDate
    ): Flow<APIResult<List<com.gradation.lift.model.routine.RoutineSetRoutine>>> = flow {
        when (testReturnState) {
            TestReturnState.Error -> emit(APIResult.Error(Throwable(message = "통신 에러")))
            TestReturnState.Fail -> emit(APIResult.Fail("존재 하지 않는 값"))
            TestReturnState.Success -> emit(APIResult.Success(data = TestDtoDataGenerator.getRoutineSetRoutineByDateDto.toRoutineSetRoutine()))
        }
    }

}