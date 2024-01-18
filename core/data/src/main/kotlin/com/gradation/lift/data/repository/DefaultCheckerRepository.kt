package com.gradation.lift.data.repository

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.CheckerRepository
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.checker.CheckerDataSource
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class DefaultCheckerRepository @Inject constructor(
    private val checkerDataSource: CheckerDataSource,
    private val dispatcherProvider: DispatcherProvider
) : CheckerRepository {
    override fun checkDuplicateEmail(email: String): Flow<DataState<Boolean>> = flow {
        checkerDataSource.checkDuplicateEmail(email).collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(
                    DataState.Success(
                        data = result.data,
                        message = result.message
                    )
                )
            }
        }


    }.flowOn(dispatcherProvider.default)

    override fun checkDuplicateName(name: String): Flow<DataState<Boolean>> = flow {
        checkerDataSource.checkDuplicateName(name).collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(
                    DataState.Success(
                        data = result.data,
                        message = result.message
                    )
                )
            }
        }
    }.flowOn(dispatcherProvider.default)

}