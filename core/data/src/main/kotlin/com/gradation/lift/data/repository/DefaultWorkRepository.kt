package com.gradation.lift.data.repository

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.common.model.DataState
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


    override fun loadWork(): Flow<DataState<Work>> {
        TODO("Not yet implemented")
    }

    override fun fetchWork(work: Work): Flow<DataState<Unit>> {
        TODO("Not yet implemented")
    }


    override fun updateWork(work: Work): Flow<DataState<Unit>> = flow {

        emit(DataState.Success(Unit))
    }.flowOn(dispatcherProvider.default)

    override fun clearWork(): Flow<DataState<Unit>> {
        TODO("Not yet implemented")
    }

    override fun existWork(): Flow<DataState<Boolean>> {
        TODO("Not yet implemented")
    }


}

