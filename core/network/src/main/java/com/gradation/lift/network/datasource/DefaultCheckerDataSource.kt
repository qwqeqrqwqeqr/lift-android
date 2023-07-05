package com.gradation.lift.network.datasource

import android.util.Log
import com.gradation.lift.model.user.Email
import com.gradation.lift.model.user.Name
import com.gradation.lift.network.common.DefaultAPIResult
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.service.CheckerService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultCheckerDataSource @Inject constructor(
    private val checkerService: CheckerService,
    private val networkResultHandler: NetworkResultHandler,
) : CheckerDataSource {
    override suspend fun checkDuplicateEmail(email: Email): Flow<DefaultAPIResult<Boolean>> =
        flow {

            networkResultHandler.executeDefault {
                checkerService.checkDuplicateEmail(email.email)
            }.collect { result ->
                when (result) {
                    is DefaultAPIResult.Fail -> emit(DefaultAPIResult.Fail(result.message))
                    is DefaultAPIResult.Error -> emit(DefaultAPIResult.Error(result.throwable))
                    is DefaultAPIResult.Success -> emit(DefaultAPIResult.Success(result.data.result))
                }
            }
        }

    @ExperimentalCoroutinesApi
    @FlowPreview
    override suspend fun checkDuplicateName(name: Name): Flow<DefaultAPIResult<Boolean>> =
        flow {

            networkResultHandler.executeDefault {
                checkerService.checkDuplicateName(name.name)
            }.collect { result ->

                when (result) {
                    is DefaultAPIResult.Fail -> emit(DefaultAPIResult.Fail(result.message))
                    is DefaultAPIResult.Error -> emit(DefaultAPIResult.Error(result.throwable))
                    is DefaultAPIResult.Success -> emit(DefaultAPIResult.Success(result.data.result))
                }
            }
        }

}

