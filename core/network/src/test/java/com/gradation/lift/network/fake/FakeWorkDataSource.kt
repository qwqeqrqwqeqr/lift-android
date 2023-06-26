package com.gradation.lift.network.fake

import com.gradation.lift.network.common.AuthAPIResult
import com.gradation.lift.network.data.TestDtoDataGenerator.getWorkCategoryByWorkPartDto
import com.gradation.lift.network.data.TestDtoDataGenerator.getWorkCategoryDto
import com.gradation.lift.network.data.TestDtoDataGenerator.getWorkPartDto
import com.gradation.lift.network.datasource.WorkDataSource
import com.gradation.lift.network.utils.TestReturnState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeWorkDataSource(private val testReturnState: TestReturnState= TestReturnState.Success)  : WorkDataSource {
    override suspend fun getWorkPart(): Flow<AuthAPIResult<List<com.gradation.lift.model.work.WorkPart>>> =flow{
        when(testReturnState){
            TestReturnState.Error -> emit(AuthAPIResult.Error(Throwable(message = "통신 에러")))
            TestReturnState.Fail -> emit(AuthAPIResult.Fail("존재 하지 않는 값"))
            TestReturnState.Success ->  emit(AuthAPIResult.Success(data = getWorkPartDto.toWorkPart()))
        }
    }

    override suspend fun getWorkCategory(): Flow<AuthAPIResult<List<com.gradation.lift.model.work.WorkCategory>>> = flow{
        when(testReturnState){
            TestReturnState.Error -> emit(AuthAPIResult.Error(Throwable(message = "통신 에러")))
            TestReturnState.Fail -> emit(AuthAPIResult.Fail("존재 하지 않는 값"))
            TestReturnState.Success ->  emit(AuthAPIResult.Success(data = getWorkCategoryDto.toWorkCategory()))
        }
    }


    override suspend fun getWorkCategoryByWorkPart(workpart: Int): Flow<AuthAPIResult<List<com.gradation.lift.model.work.WorkCategory>>> =flow{
        when(testReturnState){
            TestReturnState.Error -> emit(AuthAPIResult.Error(Throwable(message = "통신 에러",)))
            TestReturnState.Fail -> emit(AuthAPIResult.Fail("존재 하지 않는 값"))
            TestReturnState.Success ->  emit(AuthAPIResult.Success(data = getWorkCategoryByWorkPartDto.toWorkCategory()))
        }
    }

}