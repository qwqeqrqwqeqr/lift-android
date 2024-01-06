package com.gradation.lift.common.model

/**
 * [DataState]
 * 계층 간 이동 시에 사용되는 상태
 * 해당 상태를 기준으로 계층간 통신이 이루어짐
 * @since 2023-08-28 22:48:35
 */
sealed interface DataState<out T> {
    data class Success<out T>(val data: T, val message: String? = null) : DataState<T>
    data class Fail(val message: String) : DataState<Nothing>
}




