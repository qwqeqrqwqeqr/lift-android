package com.gradation.lift.network.datasource.checker

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.service.CheckerService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DefaultCheckerDataSource @Inject constructor(
    private val checkerService: CheckerService,
    private val networkResultHandler: NetworkResultHandler,
    private val dispatcherProvider: DispatcherProvider,
) : CheckerDataSource {
    override suspend fun checkDuplicateEmail(email: String): Flow<NetworkResult<Boolean>> =
        flow {

            networkResultHandler {
                checkerService.checkDuplicateEmail(email)
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                    is NetworkResult.Success -> emit(
                        NetworkResult.Success(
                            data = result.data.result,
                            message = result.message
                        )
                    )
                }
            }
        }.flowOn(dispatcherProvider.default)

    @ExperimentalCoroutinesApi
    @FlowPreview
    override suspend fun checkDuplicateName(name: String): Flow<NetworkResult<Boolean>> =
        flow {
            networkResultHandler {
                checkerService.checkDuplicateName(name)
            }.collect { result ->

                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                    is NetworkResult.Success -> emit(
                        NetworkResult.Success(
                            data = result.data.result,
                            message = result.message
                        )
                    )
                }
            }
        }.flowOn(dispatcherProvider.default)

}

