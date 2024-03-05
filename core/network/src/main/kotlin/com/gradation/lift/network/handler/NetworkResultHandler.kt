package com.gradation.lift.network.handler

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.common.error.ErrorMessage.FAILED_CONNECTION_ERROR_MESSAGE
import com.gradation.lift.network.common.error.toMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject


/**
 * [NetworkResultHandler]
 * API 응답 결과를 핸들링하는 클래스
 * 예외가 발생할 경우 [NetworkResult.Fail] 반환
 * @since 2024-03-05 22:06:07
 */
class NetworkResultHandler @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
) {
    suspend operator fun <Resource : Any> invoke(call: suspend () -> Response<APIResultWrapper<Resource>>): Flow<NetworkResult<Resource>> =
        flow {
            try {
                call.invoke().run {
                    emit(
                        if (isSuccessful)
                            NetworkResult.Success(
                                data = body()?.data!!,
                                message = body()?.message
                            )
                        else
                            NetworkResult.Fail(
                                message = JSONObject(
                                    errorBody()?.string() ?: FAILED_CONNECTION_ERROR_MESSAGE
                                ).getString("message")
                            )
                    )
                }
            } catch (error: Throwable) {
                emit(NetworkResult.Fail(message = error.toMessage()))
            }
        }.flowOn(dispatcherProvider.io)
}