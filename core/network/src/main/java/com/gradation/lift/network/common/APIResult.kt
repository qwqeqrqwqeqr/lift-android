package com.gradation.lift.network.common

import kotlinx.serialization.Serializable
import org.jetbrains.annotations.NotNull
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
 *  [APIResult] : API 통신 과정에서의 상태 클래스
 *  [Success] : API 통신 성공 시의 상태 (not null)
 *  [Fail] : API 통신 실패 시의 상태 (ex: 비밀번호가 맞지 않음)
 *  [Error] : 통신 과정에서 에러가 발생 하였을 떄의 상태 (ex: HTTP Exception, IO Exception)
 *  [Loading] : 통신 과정에서의 대기상태
 */
sealed class APIResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : APIResult<T>()
    @Nullable
    data class Fail<out T : Any>(val message: String) : APIResult<T>()
    data class Error(val exception: Throwable) : APIResult<Nothing>()
    object Loading : APIResult<Nothing>()
}


