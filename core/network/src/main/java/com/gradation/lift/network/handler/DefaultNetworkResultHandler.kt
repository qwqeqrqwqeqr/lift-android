package com.gradation.lift.network.handler

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.common.toMessage
import kotlinx.coroutines.flow.*
import org.json.JSONObject
import retrofit2.Response
import java.net.SocketTimeoutException
import javax.inject.Inject


class DefaultNetworkResultHandler @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
) : NetworkResultHandler {
    override suspend operator fun <T : Any> invoke(call: suspend () -> Response<APIResultWrapper<T>>): Flow<APIResult<T>> =
        flow {
            flowOf(call.invoke())
                .flowOn(dispatcherProvider.io)
                .catch { error ->
                    if (error is SocketTimeoutException) {
                        emit(APIResult.Fail(message = error.toMessage()))
                    }
                }
                .map { response ->
                    if (response.isSuccessful) {
                        emit(APIResult.Success(data = response.body()?.data!!))
                    } else {
                        emit(
                            APIResult.Fail(
                                message = JSONObject(
                                    response.errorBody()?.string()!!
                                ).getString("message")
                            )
                        )
                    }
                }.collect()
        }
}
