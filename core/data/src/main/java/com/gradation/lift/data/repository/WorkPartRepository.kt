package com.gradation.lift.data.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.data.WorkCategory
import com.gradation.lift.model.data.WorkPart
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.common.NetworkResultHandler
import com.gradation.lift.network.service.WorkService
import kotlinx.coroutines.flow.*
import javax.inject.Inject


interface WorkRepository {
    suspend fun getWorkPart(): Flow<DataState<List<WorkPart>>>
    suspend fun getWorkCategory(): Flow<DataState<List<WorkCategory>>>
    suspend fun getWorkCategoryByWorkPart(workpart: Int): Flow<DataState<List<WorkCategory>>>
}


class DefaultWorkPartRepository @Inject constructor(
    private val workService: WorkService,
    private val networkResultHandler: NetworkResultHandler
) : WorkRepository {
    override suspend fun getWorkPart(): Flow<DataState<List<WorkPart>>> = flow {
        networkResultHandler.execute { workService.getWorkPart() }.collectLatest { result ->
            when (result) {
                is APIResult.Success -> emit(DataState.Success(result.data?.mapNotNull { it.toWorkPart() }))
                is APIResult.Fail -> emit(DataState.Error(result.message))
                is APIResult.Error -> emit(DataState.Error(result.exception.toString()))
                APIResult.Loading -> emit(DataState.Loading)
            }
        }
    }

    override suspend fun getWorkCategory(): Flow<DataState<List<WorkCategory>>> = flow {
        networkResultHandler.execute { workService.getWorkCategory() }.collectLatest { result ->
            when (result) {
                is APIResult.Success -> emit(DataState.Success(result.data?.map { it.toWorkCategory() }))
                is APIResult.Fail -> emit(DataState.Error(result.message))
                is APIResult.Error -> emit(DataState.Error(result.exception.toString()))
                APIResult.Loading -> emit(DataState.Loading)
            }
        }
    }

    override suspend fun getWorkCategoryByWorkPart(workpart: Int): Flow<DataState<List<WorkCategory>>> =
        flow {
            networkResultHandler.execute { workService.getWorkCategory() }.collectLatest { result ->
                when (result) {
                    is APIResult.Success -> emit(DataState.Success(result.data?.map { it.toWorkCategory() }))
                    is APIResult.Fail -> emit(DataState.Error(result.message))
                    is APIResult.Error -> emit(DataState.Error(result.exception.toString()))
                    APIResult.Loading -> emit(DataState.Loading)
                }
            }
        }
}