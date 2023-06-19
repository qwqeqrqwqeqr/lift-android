package com.gradation.lift.network.datasource

import com.gradation.lift.model.work.WorkCategory
import com.gradation.lift.model.work.WorkPart
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.common.NetworkResultHandler
import com.gradation.lift.network.service.WorkService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultWorkDataSource @Inject constructor(
    private val workService: WorkService,
    private val networkResultHandler: NetworkResultHandler
) : WorkDataSource {
    override suspend fun getWorkPart(): Flow<APIResult<List<WorkPart>>> = flow {
        networkResultHandler.execute { workService.getWorkPart() }.collect { result ->
            when (result) {
                is APIResult.Fail -> emit(APIResult.Fail(result.message))
                is APIResult.Error -> emit(APIResult.Error(result.exception))
                is APIResult.Loading -> emit(APIResult.Loading)
                is APIResult.Success -> emit(APIResult.Success(result.data.toWorkPart()))
            }
        }
    }

    override suspend fun getWorkCategory(): Flow<APIResult<List<WorkCategory>>> = flow {
        networkResultHandler.execute { workService.getWorkCategory() }.collect { result ->
            when (result) {
                is APIResult.Fail -> emit(APIResult.Fail(result.message))
                is APIResult.Error -> emit(APIResult.Error(result.exception))
                is APIResult.Loading -> emit(APIResult.Loading)
                is APIResult.Success -> emit(APIResult.Success(result.data.toWorkCategory()))
            }
        }
    }

    override suspend fun getWorkCategoryByWorkPart(workpart: Int): Flow<APIResult<List<WorkCategory>>> = flow {
        networkResultHandler.execute { workService.getWorkCategoryByWorkPart(workpart) }.collect { result ->
            when (result) {
                is APIResult.Fail -> emit(APIResult.Fail(result.message))
                is APIResult.Error -> emit(APIResult.Error(result.exception))
                is APIResult.Loading -> emit(APIResult.Loading)
                is APIResult.Success -> emit(APIResult.Success(result.data.toWorkCategory()))
            }
        }
    }

}