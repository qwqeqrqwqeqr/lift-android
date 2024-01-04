package com.gradation.lift.feature.registerDetail.common.data

/**
 * [CreateUserDetailState]
 * 기본값은 [None]으로 설정되어 있음
 * @since 2024-01-03 20:26:29
 */
sealed interface CreateUserDetailState {
    data object Success : CreateUserDetailState
    data class Fail(val message: String) : CreateUserDetailState
    data object None : CreateUserDetailState
}


