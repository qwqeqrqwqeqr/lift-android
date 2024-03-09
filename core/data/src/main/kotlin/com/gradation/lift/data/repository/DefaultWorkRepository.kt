package com.gradation.lift.data.repository

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.common.model.DataState
import com.gradation.lift.data.utils.ErrorMessage
import com.gradation.lift.database.datasource.work.WorkLocalDataSource
import com.gradation.lift.domain.repository.WorkRepository
import com.gradation.lift.model.model.work.Work
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class DefaultWorkRepository @Inject constructor(
    private val workLocalDataSource: WorkLocalDataSource,
    private val dispatcherProvider: DispatcherProvider,
) : WorkRepository {


    override fun loadWork(): Flow<DataState<Work>> = flow {
        try {
            workLocalDataSource.load().collect {
                emit(it?.let { DataState.Success(it) }
                    ?: DataState.Fail(ErrorMessage.LOAD_WORK_ERROR_MESSAGE))
            }
        } catch (error: Throwable) {
            emit(DataState.Fail(ErrorMessage.LOAD_WORK_ERROR_MESSAGE))
        }
    }.flowOn(dispatcherProvider.default)

    override fun fetchWork(work: Work): Flow<DataState<Unit>> = flow {
        try {
            workLocalDataSource.fetch(work)
            emit(DataState.Success(Unit))
        } catch (error: Throwable) {
            emit(DataState.Fail(ErrorMessage.CACHE_ERROR_MESSAGE))
        }
    }.flowOn(dispatcherProvider.default)


    override fun clearWork(): Flow<DataState<Unit>> = flow {
        try {
            workLocalDataSource.clear()
            emit(DataState.Success(Unit))
        } catch (error: Throwable) {
            emit(DataState.Fail(ErrorMessage.CACHE_ERROR_MESSAGE))
        }
    }.flowOn(dispatcherProvider.default)

    override fun existWork(): Flow<DataState<Boolean>> = flow {
        try {
            workLocalDataSource.existWork().collect {
                emit(DataState.Success(it))
            }
        } catch (error: Throwable) {
            emit(DataState.Fail(ErrorMessage.CACHE_ERROR_MESSAGE))
        }
    }.flowOn(dispatcherProvider.default)


}

