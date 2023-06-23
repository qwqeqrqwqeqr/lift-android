package com.gradation.lift.network.handler

import com.gradation.lift.common.dispatcher.DispatcherProvider
import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.common.AuthAPIResult
import com.gradation.lift.network.common.Constants.INTERNAL_SERVER_ERROR
import com.gradation.lift.network.common.Constants.NETWORK_RETRY_DELAY
import com.gradation.lift.network.common.Constants.UNAUTHORIZATION
import com.gradation.lift.network.common.DefaultAPIResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class DefaultNetworkResultHandler @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
) : NetworkResultHandler {
    override suspend fun <T : Any> executeAuth(call: suspend () -> APIResultWrapper<T>): Flow<AuthAPIResult<T>> =
        flow {
            flowOf(call.invoke())
                .flowOn(dispatcherProvider.io)
                .retryWhen { cause, attempt ->
                    if ((cause is IOException || cause is HttpException) && attempt < 3L) {
                        emit(AuthAPIResult.Loading)
                        delay(NETWORK_RETRY_DELAY)
                        true
                    } else {
                        false
                    }
                }
                .onStart { emit(AuthAPIResult.Loading) }
                .catch { error ->
                    if (error is HttpException && error.code() == INTERNAL_SERVER_ERROR) {
                        emit(AuthAPIResult.Error(error))
                    }
                }
                .catch { error ->
                    if(error is HttpException && error.code() == UNAUTHORIZATION){
                        emit(AuthAPIResult.Refresh)
                    }
                }
                .catch { error -> emit(AuthAPIResult.Error(error)) }
                .collect { response ->
                    if (response.status) {
                        //TODO modify not null assertion
                        emit(AuthAPIResult.Success(data = response.data!!))
                    } else {
                        emit(
                            AuthAPIResult.Fail(message = response.message)
                        )
                    }
                }
        }

    override suspend fun <T : Any> executeDefault(call: suspend () -> APIResultWrapper<T>): Flow<DefaultAPIResult<T>>  =
        flow {
            flowOf(call.invoke())
                .flowOn(dispatcherProvider.io)
                .retryWhen { cause, attempt ->
                    if ((cause is IOException || cause is HttpException) && attempt < 3L) {
                        emit(DefaultAPIResult.Loading)
                        delay(NETWORK_RETRY_DELAY)
                        true
                    } else {
                        false
                    }
                }
                .onStart { emit(DefaultAPIResult.Loading) }
                .catch { error ->
                    if (error is HttpException && error.code() == INTERNAL_SERVER_ERROR) {
                        emit(DefaultAPIResult.Error(error))
                    }
                }
                .catch { error -> emit(DefaultAPIResult.Error(error)) }
                .collect { response ->
                    if (response.status) {
                        //TODO modify not null assertion
                        emit(DefaultAPIResult.Success(data = response.data!!))
                    } else {
                        emit(
                            DefaultAPIResult.Fail(message = response.message)
                        )
                    }
                }
        }

}