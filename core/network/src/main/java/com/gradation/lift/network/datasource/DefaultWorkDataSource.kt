package com.gradation.lift.network.datasource

import com.gradation.lift.model.model.work.WorkCategory
import com.gradation.lift.model.model.work.WorkPart
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.service.WorkService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class DefaultWorkDataSource @Inject constructor(
    private val workService: WorkService,
    private val NetworkResultHandler: NetworkResultHandler,
) : WorkDataSource {
    override suspend fun getWorkPart(): Flow<NetworkResult<List<WorkPart>>> = flow {
        NetworkResultHandler { workService.getWorkPart() }.transform { result ->
            when (result) {
                is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                is NetworkResult.Success -> emit(NetworkResult.Success(result.data.toDomain()))
            }
        }

    }

    override suspend fun getWorkCategory(): Flow<NetworkResult<List<WorkCategory>>> = flow {
        NetworkResultHandler { workService.getWorkCategory() }.transform { result ->
            when (result) {
                is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                is NetworkResult.Success -> emit(NetworkResult.Success(result.data.toDomain()))
            }
        }
    }

    override suspend fun getWorkCategoryByWorkPart(workPart: String): Flow<NetworkResult<List<WorkCategory>>> =
        flow {
            NetworkResultHandler { workService.getWorkCategoryByWorkPart(workPart) }
                .transform { result ->
                    when (result) {
                        is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                        is NetworkResult.Success -> emit(NetworkResult.Success(result.data.toDomain()))
                    }
                }
        }

}