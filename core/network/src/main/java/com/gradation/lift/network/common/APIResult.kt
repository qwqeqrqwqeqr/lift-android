package com.gradation.lift.network.common

import com.gradation.lift.network.common.AuthAPIResult.*
import kotlinx.serialization.Serializable
import javax.annotation.Nullable

/**
 *  [APIResultWrapper] : API의 응답 형태에 맞게 래핑 해주는 클래스
 *  [status] : 상태 값 success or fail
 *  [message] : 성공 또는 실패에 대한 응답 결과 메시지
 *  [data] : API를 통해 요청된 실제 데이터
 */
@Serializable
data class APIResultWrapper<out T : Any>(
    val status: Boolean,
    val message: String,
    val data: T?
)
/**
 *  [AuthAPIResult] : API 통신 과정에서의 상태 클래스 (토큰 기반 통신에 사용)
 *  [Success] : API 통신 성공 시의 상태 (not null)
 *  [Fail] : API 통신 실패 시의 상태 (ex: 비밀번호가 맞지 않음)
 *  [Error] : 통신 과정에서 에러가 발생 하였을 떄의 상태 (ex: HTTP Exception, IO Exception)
 *  [Loading] : 통신 과정에서의 대기상태
 *  [Refresh] : 토근이 만료 되어 토큰 재요청 상태
 */
sealed class AuthAPIResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : AuthAPIResult<T>()
    @Nullable
    data class Fail<out T : Any>(val message: String) : AuthAPIResult<T>()
    data class Error(val exception: Throwable) : AuthAPIResult<Nothing>()
    object Refresh : AuthAPIResult<Nothing>()
    object Loading : AuthAPIResult<Nothing>()
}


/**
 *  [DefaultAPIResult] : API 통신 과정에서의 상태 클래스
 *  [Success] : API 통신 성공 시의 상태 (not null)
 *  [Fail] : API 통신 실패 시의 상태 (ex: 비밀번호가 맞지 않음)
 *  [Error] : 통신 과정에서 에러가 발생 하였을 떄의 상태 (ex: HTTP Exception, IO Exception)
 *  [Loading] : 통신 과정에서의 대기상태
 */
sealed class DefaultAPIResult<out T : Any>{
    data class Success<out T : Any>(val data: T) : DefaultAPIResult<T>()
    @Nullable
    data class Fail<out T : Any>(val message: String) : DefaultAPIResult<T>()
    data class Error(val exception: Throwable) : DefaultAPIResult<Nothing>()
    object Loading : DefaultAPIResult<Nothing>()
}


sealed class RefreshResult<out T : Any>{
    data class Success<out T : Any>(val accessToken: String) : RefreshResult<T>()
    object Fail : RefreshResult<Nothing>()
}
