package com.gradation.lift.network.fake

import com.gradation.lift.domain.model.work.WorkCategory
import com.gradation.lift.domain.model.work.WorkPart
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.datasource.WorkDataSource
import com.gradation.lift.network.utils.TestDataGenerator.workCategory1
import com.gradation.lift.network.utils.TestDataGenerator.workCategory2
import com.gradation.lift.network.utils.TestDataGenerator.workPart1
import com.gradation.lift.network.utils.TestDataGenerator.workPart2
import com.gradation.lift.network.utils.TestReturnState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeWorkDataSource(private val returnState: TestReturnState)  : WorkDataSource {
    override suspend fun getWorkPart(): Flow<APIResult<List<WorkPart>>> =flow{
        when(returnState){
            TestReturnState.Error -> emit(APIResult.Error(Throwable(message = "통신 에러",)))
            TestReturnState.Fail -> emit(APIResult.Fail("존재 하지 않는 값"))
            TestReturnState.Loading -> emit(APIResult.Loading)
            TestReturnState.Success ->  emit(APIResult.Success(data = listOf(workPart1, workPart2)))
        }
    }

    override suspend fun getWorkCategory(): Flow<APIResult<List<WorkCategory>>> = flow{
        when(returnState){
            TestReturnState.Error -> emit(APIResult.Error(Throwable(message = "통신 에러",)))
            TestReturnState.Fail -> emit(APIResult.Fail("존재 하지 않는 값"))
            TestReturnState.Loading -> emit(APIResult.Loading)
            TestReturnState.Success ->  emit(APIResult.Success(data = listOf(workCategory1, workCategory2)))
        }
    }

    override suspend fun getWorkCategoryByWorkPart(workpart: Int): Flow<APIResult<List<WorkCategory>>> =flow{
        when(returnState){
            TestReturnState.Error -> emit(APIResult.Error(Throwable(message = "통신 에러",)))
            TestReturnState.Fail -> emit(APIResult.Fail("존재 하지 않는 값"))
            TestReturnState.Loading -> emit(APIResult.Loading)
            TestReturnState.Success ->  emit(APIResult.Success(data = listOf(workCategory1, workCategory2)))
        }
    }

}