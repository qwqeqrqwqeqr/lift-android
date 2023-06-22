package com.gradation.lift.data.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.data.utils.RefreshManager
import com.gradation.lift.domain.repository.WorkRepository
import com.gradation.lift.model.work.WorkCategory
import com.gradation.lift.model.work.WorkPart
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.datasource.WorkDataSource
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class DefaultWorkRepository @Inject constructor(
    private val workDataSource: WorkDataSource,
    private val refreshManager: RefreshManager,
) : WorkRepository {
    override fun getWorkPart(): Flow<DataState<List<WorkPart>>> = flow {
        workDataSource.getWorkPart().collect { result ->
            when (result) {
                is APIResult.Fail -> emit(DataState.Fail(result.message))
                is APIResult.Error -> emit(DataState.Error(result.exception.toString()))
                is APIResult.Loading -> emit(DataState.Loading)
                is APIResult.Success -> emit(DataState.Success(result.data))
                is APIResult.Refresh -> {
                    emit(refreshManager { workDataSource.getWorkPart()})
                }
            }
        }
    }


    override fun getWorkCategory(): Flow<DataState<List<WorkCategory>>> = flow {
        workDataSource.getWorkCategory().collect { result ->
            when (result) {
                is APIResult.Fail -> emit(DataState.Fail(result.message))
                is APIResult.Error -> emit(DataState.Error(result.exception.toString()))
                is APIResult.Loading -> emit(DataState.Loading)
                is APIResult.Success -> emit(DataState.Success(result.data))
                is APIResult.Refresh -> {
                    emit(refreshManager { workDataSource.getWorkCategory()})
                }
            }
        }
    }

    override fun getWorkCategoryByWorkPart(workpart: Int): Flow<DataState<List<WorkCategory>>> =
        flow {
            workDataSource.getWorkCategoryByWorkPart(workpart).collect { result ->
                when (result) {
                    is APIResult.Fail -> emit(DataState.Fail(result.message))
                    is APIResult.Error -> emit(DataState.Error(result.exception.toString()))
                    is APIResult.Loading -> emit(DataState.Loading)
                    is APIResult.Success -> emit(DataState.Success(result.data))
                    is APIResult.Refresh -> {
                        emit(refreshManager { workDataSource.getWorkCategoryByWorkPart(workpart)})
                    }
                }
            }
        }
}