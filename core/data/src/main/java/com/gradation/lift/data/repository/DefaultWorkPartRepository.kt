package com.gradation.lift.data.repository

import com.gradation.lift.domain.model.common.DataState
import com.gradation.lift.domain.model.work.WorkCategory
import com.gradation.lift.domain.model.work.WorkPart
import com.gradation.lift.domain.repository.WorkRepository
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.common.NetworkResultHandler
import com.gradation.lift.network.service.WorkService
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class DefaultWorkRepository @Inject constructor(
    private val workService: WorkService,
    private val networkResultHandler: NetworkResultHandler
) : WorkRepository {
    override fun getWorkPart(): Flow<DataState<List<WorkPart>>> = flow {
        networkResultHandler.execute { workService.getWorkPart() }.collect { result ->
            when (result) {
                is APIResult.Success -> emit(DataState.Success(result.data?.mapNotNull { it.toWorkPart() }))
                is APIResult.Fail -> emit(DataState.Error(result.message))
                is APIResult.Error -> emit(DataState.Error(result.exception.toString()))
                APIResult.Loading -> emit(DataState.Loading)
            }
        }

    }

    override  fun getWorkCategory(): Flow<DataState<List<WorkCategory>>> = flow {
        networkResultHandler.execute { workService.getWorkCategory() }.collectLatest { result ->
            when (result) {
                is APIResult.Success -> emit(DataState.Success(result.data?.map { it.toWorkCategory() }))
                is APIResult.Fail -> emit(DataState.Error(result.message))
                is APIResult.Error -> emit(DataState.Error(result.exception.toString()))
                APIResult.Loading -> emit(DataState.Loading)
            }
        }
    }

    override  fun getWorkCategoryByWorkPart(workpart: Int): Flow<DataState<List<WorkCategory>>> =
        flow {
            networkResultHandler.execute { workService.getWorkCategoryByWorkPart(workpart) }.collectLatest { result ->
                when (result) {
                    is APIResult.Success -> emit(DataState.Success(result.data?.map { it.toWorkCategory() }))
                    is APIResult.Fail -> emit(DataState.Error(result.message))
                    is APIResult.Error -> emit(DataState.Error(result.exception.toString()))
                    APIResult.Loading -> emit(DataState.Loading)
                }
            }
        }
}