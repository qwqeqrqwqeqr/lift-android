package com.gradation.lift.data.fake

import com.gradation.lift.data.data.TestDtoDataGenerator.WorkCategory.getWorkCategoryByWorkPartResponseDto
import com.gradation.lift.data.data.TestDtoDataGenerator.WorkCategory.getWorkCategoryResponseDto
import com.gradation.lift.data.data.TestDtoDataGenerator.WorkPart.getWorkPartResponseDto
import com.gradation.lift.model.model.work.WorkCategory
import com.gradation.lift.model.model.work.WorkPart
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ERROR_MESSAGE
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.work.WorkDataSource
import com.gradation.lift.data.utils.TestReturnState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeWorkDataSource(private val testReturnState: TestReturnState = TestReturnState.Success)  :
    WorkDataSource {
    override suspend fun getWorkPart(): Flow<NetworkResult<List<WorkPart>>> =flow{
        when(testReturnState){
            TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
            TestReturnState.Success ->  emit(NetworkResult.Success(data = getWorkPartResponseDto.toDomain()))
        }
    }

    override suspend fun getWorkCategory(): Flow<NetworkResult<List<WorkCategory>>> = flow{
        when(testReturnState){
            TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
            TestReturnState.Success ->  emit(NetworkResult.Success(data = getWorkCategoryResponseDto.toDomain()))
        }
    }


    override suspend fun getWorkCategoryByWorkPart(workPart: String): Flow<NetworkResult<List<WorkCategory>>> =flow{
        when(testReturnState){
            TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
            TestReturnState.Success ->  emit(NetworkResult.Success(data = getWorkCategoryByWorkPartResponseDto.toDomain()))
        }
    }

}