package com.gradation.lift.data.repository

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.common.model.DataState
import com.gradation.lift.data.utils.ErrorMessage
import com.gradation.lift.database.datasource.workCategory.WorkCategoryLocalDataSource
import com.gradation.lift.domain.repository.WorkCategoryRepository
import com.gradation.lift.model.model.work.WorkCategory
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.work.WorkRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class DefaultWorkCategoryRepository @Inject constructor(
    private val workRemoteDataSource: WorkRemoteDataSource,
    private val workCategoryLocalDataSource: WorkCategoryLocalDataSource,
    private val dispatcherProvider: DispatcherProvider,
) : WorkCategoryRepository {


    override fun getWorkCategory(): Flow<DataState<List<WorkCategory>>> = flow {
        workRemoteDataSource.getWorkCategory().collect { result ->
            when (result) {
                is NetworkResult.Fail -> {
                    try {
                        workCategoryLocalDataSource.getAllWorkCategory().collect {
                            if (it.isEmpty()) emit(DataState.Fail(result.message))
                            else emit(DataState.Success(it))
                        }
                    } catch (error: Throwable) {
                        emit(DataState.Fail(ErrorMessage.CACHE_ERROR_MESSAGE))
                    }

                }

                is NetworkResult.Success -> {
                    try {
                        workCategoryLocalDataSource.fetch(result.data)
                        emit(DataState.Success(result.data))
                    } catch (error: Throwable) {
                        emit(DataState.Fail(ErrorMessage.CACHE_ERROR_MESSAGE))
                    }
                }
            }
        }
    }.flowOn(dispatcherProvider.default)

    override fun getWorkCategoryById(workCategoryId: Int): Flow<DataState<WorkCategory>> = flow {
        workRemoteDataSource.getWorkCategoryById(workCategoryId).collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }.flowOn(dispatcherProvider.default)

    override fun getWorkCategoryByWorkPart(workPart: String): Flow<DataState<List<WorkCategory>>> =
        flow {
            workRemoteDataSource.getWorkCategoryByWorkPart(workPart).collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                    is NetworkResult.Success -> emit(DataState.Success(result.data))
                }
            }
        }.flowOn(dispatcherProvider.default)

    override fun getPopularWorkCategory(): Flow<DataState<List<WorkCategory>>> = flow {
        workRemoteDataSource.getPopularWorkCategory().collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }.flowOn(dispatcherProvider.default)

    override fun getRecommendWorkCategory(): Flow<DataState<List<WorkCategory>>> = flow {
        workRemoteDataSource.getRecommendWorkCategory().collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }.flowOn(dispatcherProvider.default)


}

