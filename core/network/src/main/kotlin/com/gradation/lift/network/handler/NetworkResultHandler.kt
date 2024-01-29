package com.gradation.lift.network.handler

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.common.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import javax.inject.Inject


/**
 * [NetworkResultHandler]
 * API 응답 결과를 핸들링하는 클래스
 * 예외가 발생할 경우 [NetworkResult.Fail] 반환
 * @since 2024-01-19 22:49:02
 */
class NetworkResultHandler @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
) {
    suspend operator fun <T : Any> invoke(call: suspend () -> Response<APIResultWrapper<T>>): Flow<NetworkResult<T>> =
        flow {
            try {
                call.invoke().run {
                    if (isSuccessful)
                        emit(
                            NetworkResult.Success(
                                data = body()?.data!!,
                                message = body()?.message
                            )
                        )
                    else
                        emit(
                            NetworkResult.Fail(
                                message = JSONObject(
                                    errorBody()?.string() ?: "네트워크 연결에 실패하였습니다."
                                ).getString("message")
                            )
                        )
                }
            } catch (error: Throwable) {
                when (error) {
                    is ConnectException -> emit(NetworkResult.Fail(message = error.message.toString()))
                    is SocketTimeoutException -> emit(NetworkResult.Fail(message = "네트워크 시간이 만료되었습니다.\n잠시후에 다시 시도해주세요"))
                    is IOException -> emit(NetworkResult.Fail(message = "요청을 실패하였습니다."))
                    else -> emit(NetworkResult.Fail(message = "네트워크 연결에 실패하였습니다."))
                }
            }
        }.flowOn(dispatcherProvider.io)

}