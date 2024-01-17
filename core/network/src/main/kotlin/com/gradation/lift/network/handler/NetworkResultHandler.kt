package com.gradation.lift.network.handler

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.common.NetworkResult
import kotlinx.coroutines.flow.*
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketTimeoutException
import javax.inject.Inject


/**
 * [NetworkResultHandler]
 * API 응답 결과를 핸들링하는 클래스
 * 예외가 발생할 경우 [NetworkResult.Fail] 반환
 * @since 2023-08-28 22:28:20
 */
class NetworkResultHandler @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
) {
    suspend operator fun <T : Any> invoke(call: suspend () -> Response<APIResultWrapper<T>>): Flow<NetworkResult<T>> =
        flowOf(call.invoke()).transform { response ->
            if (response.isSuccessful) {
                emit(
                    NetworkResult.Success(
                        data = response.body()?.data!!,
                        message = response.body()?.message
                    )
                )
            } else {
                emit(
                    NetworkResult.Fail(
                        message = JSONObject(
                            response.errorBody()?.string()!!
                        ).getString("message")
                    )
                )
            }
        }
            .flowOn(dispatcherProvider.io)
            .catch { error ->
                if (error is SocketTimeoutException) {
                    emit(NetworkResult.Fail(message = "네트워크 시간이 만료되었습니다.\n 잠시후에 다시 시도해주세요"))
                }
            }
            .catch { error ->
                if (error is HttpException) {
                    emit(NetworkResult.Fail(message = "네트워크 연결을 실패하였습니다."))
                }
            }


}