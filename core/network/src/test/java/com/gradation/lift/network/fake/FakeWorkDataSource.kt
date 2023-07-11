package com.gradation.lift.network.fake

import com.gradation.lift.model.work.WorkCategory
import com.gradation.lift.model.work.WorkPart
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.data.TestDtoDataGenerator.getWorkCategoryByWorkPartDto
import com.gradation.lift.network.data.TestDtoDataGenerator.getWorkCategoryDto
import com.gradation.lift.network.data.TestDtoDataGenerator.getWorkPartDto
import com.gradation.lift.network.datasource.WorkDataSource
import com.gradation.lift.network.utils.TestReturnState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeWorkDataSource(private val testReturnState: TestReturnState= TestReturnState.Success)  : WorkDataSource {
    override suspend fun getWorkPart(): Flow<APIResult<List<WorkPart>>> =flow{
        when(testReturnState){
            TestReturnState.Fail -> emit(APIResult.Fail("오류"))
            TestReturnState.Success ->  emit(APIResult.Success(data = getWorkPartDto.toWorkPart()))
        }
    }

    override suspend fun getWorkCategory(): Flow<APIResult<List<WorkCategory>>> = flow{
        when(testReturnState){
            TestReturnState.Fail -> emit(APIResult.Fail("오류"))
            TestReturnState.Success ->  emit(APIResult.Success(data = getWorkCategoryDto.toWorkCategory()))
        }
    }


    override suspend fun getWorkCategoryByWorkPart(workpart: Int): Flow<APIResult<List<WorkCategory>>> =flow{
        when(testReturnState){
            TestReturnState.Fail -> emit(APIResult.Fail("오류"))
            TestReturnState.Success ->  emit(APIResult.Success(data = getWorkCategoryByWorkPartDto.toWorkCategory()))
        }
    }

}