package com.gradation.lift.data.repository

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.common.model.DataState
import com.gradation.lift.database.dao.WorkCategoryDao
import com.gradation.lift.domain.repository.WorkCategoryRepository
import com.gradation.lift.model.model.work.WorkCategory
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.work.WorkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class DefaultWorkCategoryRepository @Inject constructor(
    private val workDataSource: WorkDataSource,
    private val workCategoryDao: WorkCategoryDao,
    private val dispatcherProvider: DispatcherProvider,
) : WorkCategoryRepository {


    override fun getWorkCategory(): Flow<DataState<List<WorkCategory>>> = flow {
        workDataSource.getWorkCategory().distinctUntilChanged().collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }.flowOn(dispatcherProvider.default)

    override fun getWorkCategoryById(workCategoryId: Int): Flow<DataState<WorkCategory>> = flow {
        workDataSource.getWorkCategoryById(workCategoryId).collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }.flowOn(dispatcherProvider.default)

    override fun getWorkCategoryByWorkPart(workPart: String): Flow<DataState<List<WorkCategory>>> =
        flow {
            workDataSource.getWorkCategoryByWorkPart(workPart).collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                    is NetworkResult.Success -> emit(DataState.Success(result.data))
                }
            }
        }.flowOn(dispatcherProvider.default)

    override fun getPopularWorkCategory(): Flow<DataState<List<WorkCategory>>> = flow {
        workDataSource.getPopularWorkCategory().distinctUntilChanged().collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }.flowOn(dispatcherProvider.default)

    override fun getRecommendWorkCategory(): Flow<DataState<List<WorkCategory>>> = flow {
        workDataSource.getRecommendWorkCategory().distinctUntilChanged().collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }.flowOn(dispatcherProvider.default)


}

