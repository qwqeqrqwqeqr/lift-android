package com.gradation.lift.network.common

import com.gradation.lift.common.di.DispatcherProvider
import com.gradation.lift.network.common.Constants.INTERNAL_ERROR
import com.gradation.lift.network.common.Constants.NETWORK_RETRY_DELAY
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

interface NetworkResultHandler {
    suspend fun <T : Any> execute(call: suspend () -> APIResultWrapper<T>): Flow<APIResult<T>>
}

class DefaultNetworkResultHandler @Inject constructor(
    private val dispatcherProvider: DispatcherProvider
) : NetworkResultHandler {
    override suspend fun <T : Any> execute(call: suspend () -> APIResultWrapper<T>): Flow<APIResult<T>> =
        flow {
            flowOf(call.invoke())
                .flowOn(dispatcherProvider.io)
                .retryWhen { cause, attempt ->
                    if ((cause is IOException || cause is HttpException) && attempt < 3L) {
                        emit(APIResult.Loading)
                        delay(NETWORK_RETRY_DELAY)
                        true
                    } else {
                        false
                    }
                }
                .onStart { emit(APIResult.Loading) }
                .catch { error ->
                    if (error is HttpException && error.code() == INTERNAL_ERROR) {
                        emit(APIResult.Error(error))
                    }
                }
                .catch { e -> emit(APIResult.Error(e)) }
                .collect { response ->
                    if (response.status) {
                        //TODO modify not null assertion
                        emit(APIResult.Success(data = response.data!!))
                    } else {
                        emit(
                            APIResult.Fail(message = response.message)
                        )
                    }
                }
        }
}