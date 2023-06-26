package com.gradation.lift.network.datasource

import com.gradation.lift.model.work.WorkCategory
import com.gradation.lift.model.work.WorkPart
import com.gradation.lift.network.common.AuthAPIResult
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.service.WorkService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultWorkDataSource @Inject constructor(
    private val workService: WorkService,
    private val networkResultHandler: NetworkResultHandler,
) : WorkDataSource {
    override suspend fun getWorkPart(): Flow<AuthAPIResult<List<WorkPart>>> = flow {
        networkResultHandler.executeAuth { workService.getWorkPart() }.collect { result ->
            when (result) {
                is AuthAPIResult.Fail -> emit(AuthAPIResult.Fail(result.message))
                is AuthAPIResult.Error -> emit(AuthAPIResult.Error(result.exception))
                is AuthAPIResult.Refresh -> emit(AuthAPIResult.Refresh)
                is AuthAPIResult.Success -> emit(AuthAPIResult.Success(result.data.toWorkPart()))
            }
        }

    }

    override suspend fun getWorkCategory(): Flow<AuthAPIResult<List<WorkCategory>>> = flow {
        networkResultHandler.executeAuth { workService.getWorkCategory() }.collect { result ->
            when (result) {
                is AuthAPIResult.Fail -> emit(AuthAPIResult.Fail(result.message))
                is AuthAPIResult.Error -> emit(AuthAPIResult.Error(result.exception))
                is AuthAPIResult.Refresh -> emit(AuthAPIResult.Refresh)
                is AuthAPIResult.Success -> emit(AuthAPIResult.Success(result.data.toWorkCategory()))
            }
        }
    }

    override suspend fun getWorkCategoryByWorkPart(workpart: Int): Flow<AuthAPIResult<List<WorkCategory>>> =
        flow {
            networkResultHandler.executeAuth { workService.getWorkCategoryByWorkPart(workpart) }
                .collect { result ->
                    when (result) {
                        is AuthAPIResult.Fail -> emit(AuthAPIResult.Fail(result.message))
                        is AuthAPIResult.Error -> emit(AuthAPIResult.Error(result.exception))
                        is AuthAPIResult.Refresh -> emit(AuthAPIResult.Refresh)
                        is AuthAPIResult.Success -> emit(AuthAPIResult.Success(result.data.toWorkCategory()))
                    }
                }
        }

}