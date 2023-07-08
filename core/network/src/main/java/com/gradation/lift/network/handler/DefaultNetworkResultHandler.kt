package com.gradation.lift.network.handler

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.common.AuthAPIResult
import com.gradation.lift.network.common.Constants.FORBIDDEN
import com.gradation.lift.network.common.Constants.INTERNAL_SERVER_ERROR
import com.gradation.lift.network.common.Constants.NETWORK_RETRY_DELAY
import com.gradation.lift.network.common.Constants.UNAUTHORIZATION
import com.gradation.lift.network.common.DefaultAPIResult
import com.gradation.lift.network.common.toMessage
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject


class DefaultNetworkResultHandler @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
) : NetworkResultHandler {
    override suspend fun <T : Any> executeAuth(call: suspend () -> APIResultWrapper<T>): Flow<AuthAPIResult<T>> =
        flow {
            flowOf(call.invoke())
                .flowOn(dispatcherProvider.io)
                .catch { error ->
                    if (error is HttpException && error.code() == FORBIDDEN) {
                        emit(AuthAPIResult.Fail(message = error.toMessage()))
                    }
                }

                .catch { error ->
                    if (error is SocketTimeoutException) {
                        emit(AuthAPIResult.Fail(message = error.toMessage()))
                    }
                }
                .retryWhen { cause, attempt ->
                    if ((cause is IOException || cause is HttpException) && attempt < 3L) {
                        delay(NETWORK_RETRY_DELAY)
                        true
                    } else {
                        false
                    }
                }
                .collect { response ->
                    if (response.status) {
                        emit(AuthAPIResult.Success(data = response.data!!))
                    } else {
                        emit(
                            AuthAPIResult.Fail(message = response.message)
                        )
                    }
                }
        }

    override suspend fun <T : Any> executeDefault(call: suspend () -> APIResultWrapper<T>): Flow<DefaultAPIResult<T>> =
        flow {
            flowOf(call.invoke())
                .flowOn(dispatcherProvider.io)
                .retryWhen { cause, attempt ->
                    if ((cause is IOException || cause is HttpException) && attempt < 3L) {
                        delay(NETWORK_RETRY_DELAY)
                        true
                    } else {
                        false
                    }
                }
                .catch { error ->
                    if (error is SocketTimeoutException) {
                        emit(DefaultAPIResult.Fail(message = error.toMessage()))
                    }
                }
                .collect { response ->
                    if (response.status) {
                        emit(DefaultAPIResult.Success(data = response.data!!))
                    } else {
                        emit(
                            DefaultAPIResult.Fail(message = response.message)
                        )
                    }
                }
        }




}