package com.gradation.lift.network.datasource

import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.service.CheckerService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class DefaultCheckerDataSource @Inject constructor(
    private val checkerService: CheckerService,
    private val NetworkResultHandler: NetworkResultHandler,
) : CheckerDataSource {
    override suspend fun checkDuplicateEmail(email: String): Flow<NetworkResult<Boolean>> =
        flow {

            NetworkResultHandler {
                checkerService.checkDuplicateEmail(email)
            }.transform { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.result))
                }
            }
        }

    @ExperimentalCoroutinesApi
    @FlowPreview
    override suspend fun checkDuplicateName(name: String): Flow<NetworkResult<Boolean>> =
        flow {

            NetworkResultHandler {
                checkerService.checkDuplicateName(name)
            }.transform { result ->

                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.result))
                }
            }
        }

}

