package com.gradation.lift.network.handler

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.common.NetworkResult
import kotlinx.coroutines.flow.*
import org.json.JSONObject
import retrofit2.Response
import java.net.SocketTimeoutException
import javax.inject.Inject


class DefaultNetworkResultHandler @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
) : NetworkResultHandler {
    override suspend operator fun <T : Any> invoke(call: suspend () -> Response<APIResultWrapper<T>>): Flow<NetworkResult<T>> =
        flow {
            flowOf(call.invoke())
                .flowOn(dispatcherProvider.io)
                .catch { error ->
                    if (error is SocketTimeoutException) {
                        emit(NetworkResult.Fail(message = error.toMessage()))
                    }
                }
                .map { response ->
                    if (response.isSuccessful) {
                        emit(NetworkResult.Success(data = response.body()?.data!!))
                    } else {
                        emit(
                            NetworkResult.Fail(
                                message = JSONObject(
                                    response.errorBody()?.string()!!
                                ).getString("message")
                            )
                        )
                    }
                }.collect()
        }
}
