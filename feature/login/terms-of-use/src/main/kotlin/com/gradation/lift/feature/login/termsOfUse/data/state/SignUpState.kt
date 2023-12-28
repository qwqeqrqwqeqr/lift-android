package com.gradation.lift.feature.login.termsOfUse.data.state


/**
 * [SignUpState]
 * 회원가입 상태
 * @since 2023-12-27 15:21:17
 */
sealed interface SignUpState {

    data object Success : SignUpState
    data class Fail(val message: String) : SignUpState
    data object None : SignUpState
}