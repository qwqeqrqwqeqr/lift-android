package com.gradation.lift.network.handler

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.common.Constants.NETWORK_RETRY_DELAY
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.common.toMessage
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject


class DefaultNetworkResultHandler @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
) : NetworkResultHandler {
    override suspend operator fun <T : Any> invoke(call: suspend () -> APIResultWrapper<T>): Flow<APIResult<T>> =
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
                        emit(APIResult.Fail(message = error.toMessage()))
                    }
                }
                .collect { response ->
                    if (response.status) {
                        emit(APIResult.Success(data = response.data!!))
                    } else {
                        emit(
                            APIResult.Fail(message = response.message)
                        )
                    }
                }
        }
}