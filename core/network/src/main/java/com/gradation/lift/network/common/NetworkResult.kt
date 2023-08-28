package com.gradation.lift.network.common


/**
 *  [NetworkResult]
 *  NetWorkHandler 에 따른 결과 클래스
 *  [Success] : Network를 성공적으로 핸들링
 *  [Fail] : Network 핸들링을 실패함
 *  @since 2023-08-28 22:06:10
 */
sealed class NetworkResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : NetworkResult<T>()
    data class Fail(val message: String) : NetworkResult<Nothing>()
}
