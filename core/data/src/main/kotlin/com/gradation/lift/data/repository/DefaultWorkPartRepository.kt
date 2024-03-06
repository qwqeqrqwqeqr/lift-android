package com.gradation.lift.data.repository

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.common.model.DataState
import com.gradation.lift.database.dao.WorkPartDao
import com.gradation.lift.domain.repository.WorkPartRepository
import com.gradation.lift.model.model.work.WorkPart
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.work.WorkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class DefaultWorkPartRepository @Inject constructor(
    private val workDataSource: WorkDataSource,
    private val workPartDao: WorkPartDao,
    private val dispatcherProvider: DispatcherProvider,
) : WorkPartRepository {
    override fun getWorkPart(): Flow<DataState<List<WorkPart>>> = flow {
        workDataSource.getWorkPart().distinctUntilChanged().collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }.flowOn(dispatcherProvider.default)

}

