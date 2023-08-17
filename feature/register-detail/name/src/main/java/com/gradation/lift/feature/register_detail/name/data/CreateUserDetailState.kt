package com.gradation.lift.feature.register_detail.name.data

/**
 * [CreateUserDetailState]
 * 기본값은 [None]으로 설정되어 있음
 * @since 2023-08-17 19:22:25
 */
sealed interface CreateUserDetailState {
    object Success : CreateUserDetailState
    data class Fail(val message: String) : CreateUserDetailState
    object None : CreateUserDetailState
}


