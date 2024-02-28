package com.gradation.lift.data.fake.datasource

import com.gradation.lift.data.data.TestDtoDataGenerator.Work.GetPopularWorkCategory.GET_POPULAR_WORK_CATEGORY_RESPONSE_DTO
import com.gradation.lift.data.data.TestDtoDataGenerator.Work.GetRecommendWorkCategory.GET_RECOMMEND_WORK_CATEGORY_RESPONSE_DTO
import com.gradation.lift.data.data.TestDtoDataGenerator.Work.GetWorkCategory.GET_WORK_CATEGORY_RESPONSE_DTO
import com.gradation.lift.data.data.TestDtoDataGenerator.Work.GetWorkCategoryById.GET_WORK_CATEGORY_BY_ID_RESPONSE_DTO
import com.gradation.lift.data.data.TestDtoDataGenerator.Work.GetWorkCategoryByWorkPart.GET_WORK_CATEGORY_BY_WORK_PART_RESPONSE_DTO
import com.gradation.lift.data.data.TestDtoDataGenerator.Work.GetWorkPart.GET_WORK_PART_RESPONSE_DTO
import com.gradation.lift.data.utils.TestReturnState
import com.gradation.lift.model.model.work.WorkCategory
import com.gradation.lift.model.model.work.WorkPart
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ERROR_MESSAGE
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.work.WorkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeWorkDataSource(private val testReturnState: TestReturnState = TestReturnState.Success)  :
    WorkDataSource {
    override suspend fun getWorkPart(): Flow<NetworkResult<List<WorkPart>>> =flow{
        when(testReturnState){
            TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
            TestReturnState.Success -> emit(NetworkResult.Success(data = GET_WORK_PART_RESPONSE_DTO.toDomain()))
        }
    }

    override suspend fun getWorkCategory(): Flow<NetworkResult<List<WorkCategory>>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
            TestReturnState.Success -> emit(NetworkResult.Success(data = GET_WORK_CATEGORY_RESPONSE_DTO.toDomain()))
        }
    }

    override suspend fun getWorkCategoryById(workCategoryId: Int): Flow<NetworkResult<WorkCategory>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
                TestReturnState.Success -> emit(NetworkResult.Success(data = GET_WORK_CATEGORY_BY_ID_RESPONSE_DTO.toDomain()))
            }
        }

    override suspend fun getPopularWorkCategory(): Flow<NetworkResult<List<WorkCategory>>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
            TestReturnState.Success -> emit(NetworkResult.Success(data = GET_POPULAR_WORK_CATEGORY_RESPONSE_DTO.toDomain()))
        }
    }

    override suspend fun getRecommendWorkCategory(): Flow<NetworkResult<List<WorkCategory>>> =
        flow {
        when(testReturnState){
            TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
            TestReturnState.Success -> emit(NetworkResult.Success(data = GET_RECOMMEND_WORK_CATEGORY_RESPONSE_DTO.toDomain()))
        }
    }


    override suspend fun getWorkCategoryByWorkPart(workPart: String): Flow<NetworkResult<List<WorkCategory>>> =flow{
        when(testReturnState){
            TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
            TestReturnState.Success -> emit(NetworkResult.Success(data = GET_WORK_CATEGORY_BY_WORK_PART_RESPONSE_DTO.toDomain()))
        }
    }

}