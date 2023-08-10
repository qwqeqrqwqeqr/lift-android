package com.gradation.lift.network.datasource

import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.service.CheckerService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultCheckerDataSource @Inject constructor(
    private val checkerService: CheckerService,
    private val networkResultHandler: NetworkResultHandler,
) : CheckerDataSource {
    override suspend fun checkDuplicateEmail(email: String): Flow<APIResult<Boolean>> =
        flow {

            networkResultHandler {
                checkerService.checkDuplicateEmail(email)
            }.collect { result ->
                when (result) {
                    is APIResult.Fail -> emit(APIResult.Fail(result.message))
                    is APIResult.Success -> emit(APIResult.Success(result.data.result))
                }
            }
        }

    @ExperimentalCoroutinesApi
    @FlowPreview
    override suspend fun checkDuplicateName(name: String): Flow<APIResult<Boolean>> =
        flow {

            networkResultHandler {
                checkerService.checkDuplicateName(name)
            }.collect { result ->

                when (result) {
                    is APIResult.Fail -> emit(APIResult.Fail(result.message))
                    is APIResult.Success -> emit(APIResult.Success(result.data.result))
                }
            }
        }

}

