package com.gradation.lift.network.common

import android.util.Log
import com.gradation.lift.common.di.DispatcherProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

interface NetworkResultHandler{
    suspend fun <T : Any> execute(call: suspend () -> APIResultWrapper<T>): Flow<APIResult<T>>
}

class DefaultNetworkResultHandler @Inject constructor(
    private val dispatcherProvider: DispatcherProvider
) :NetworkResultHandler{
    override suspend fun <T : Any> execute(call: suspend () -> APIResultWrapper<T>): Flow<APIResult<T>> =
        flow {
            flowOf(call.invoke())
                .flowOn(dispatcherProvider.io)
                .retryWhen { cause, attempt ->
                    if ((cause is IOException || cause is HttpException) && attempt < 3L) {
                        emit(APIResult.Loading)
                        delay(1000)
                        true
                    } else {
                        false
                    }
                }
                .onStart { emit(APIResult.Loading) }
                .catch { e -> emit(APIResult.Error(e)) }
                .collect{
                    response ->
                    if (response.status) {
                        emit(APIResult.Success(data = response.data, message = response.message))
                    } else {
                        emit(APIResult.Fail(data = response.data, message = response.message)
                        )
                    }
                }
        }
}