package com.gradation.lift.data.repository

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.common.model.DataState
import com.gradation.lift.data.utils.ErrorMessage
import com.gradation.lift.database.datasource.workPart.WorkPartLocalDataSource
import com.gradation.lift.domain.repository.WorkPartRepository
import com.gradation.lift.model.model.work.WorkPart
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.work.WorkRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class DefaultWorkPartRepository @Inject constructor(
    private val workRemoteDataSource: WorkRemoteDataSource,
    private val workPartLocalDataSource: WorkPartLocalDataSource,
    private val dispatcherProvider: DispatcherProvider,
) : WorkPartRepository {
    override fun getWorkPart(): Flow<DataState<List<WorkPart>>> = flow {
        workRemoteDataSource.getWorkPart().collect { result ->
            when (result) {
                is NetworkResult.Fail -> {
                    try {
                        workPartLocalDataSource.getAllWorkPart().collect {
                            if (it.isEmpty()) emit(DataState.Fail(result.message))
                            else emit(DataState.Success(it))
                        }
                    } catch (error: Throwable) {
                        emit(DataState.Fail(ErrorMessage.CACHE_ERROR_MESSAGE))
                    }
                }

                is NetworkResult.Success -> {
                    try {
                        workPartLocalDataSource.fetch(result.data)
                        emit(DataState.Success(result.data))
                    } catch (error: Throwable) {
                        emit(DataState.Fail(ErrorMessage.CACHE_ERROR_MESSAGE))
                    }
                }
            }
        }
    }.flowOn(dispatcherProvider.default)

}

