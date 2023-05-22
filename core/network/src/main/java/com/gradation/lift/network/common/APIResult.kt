package com.gradation.lift.network.common

import com.gradation.lift.common.di.DispatcherProvider
import com.gradation.lift.common.model.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import okio.IOException
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject


data class APIResultWrapper<out T : Any>(
    val status: Boolean,
    val message: String,
    val data: T?
)

sealed class APIResult<out T : Any> {
    data class Success<out T : Any>(val data: T?, val message: String) : APIResult<T>()
    data class Fail<out T : Any>(val data: T?, val message: String) : APIResult<T>()
    data class Error(val exception: Throwable? = null) : APIResult<Nothing>()
    object Loading : APIResult<Nothing>()
}

class NetworkResultHandler @Inject constructor(
    private val dispatcherProvider: DispatcherProvider
) {
    suspend operator fun <T : Any> invoke(call: suspend () -> APIResultWrapper<T>): Flow<APIResult<T>> =
        flow {
            flowOf(call.invoke())
                .map { response ->
                    if (response.status) {
                        emit(APIResult.Success(data = response.data, message = response.message))
                    } else {
                        emit(
                            APIResult.Fail(data = response.data, message = response.message)
                        )
                    }
                }
                .onStart { emit(APIResult.Loading) }
                .flowOn(dispatcherProvider.io)
                .retryWhen { cause, attempt ->
                    if ((cause is java.io.IOException || cause is HttpException) && attempt < 3L) {
                        emit(APIResult.Loading)
                        delay(1000)
                        true
                    } else {
                        false
                    }
                }
                .catch { e -> APIResult.Error(e) }
        }
}
