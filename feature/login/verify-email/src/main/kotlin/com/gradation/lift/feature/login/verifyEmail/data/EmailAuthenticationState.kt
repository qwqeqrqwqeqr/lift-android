package com.gradation.lift.feature.login.verifyEmail.data


/**
* [EmailAuthenticationState]
* 이메일 전송 상태
* @since 2024-01-06 18:41:39
*/
sealed interface EmailAuthenticationState {

    data object Success : EmailAuthenticationState
    data class Fail(val message: String) : EmailAuthenticationState
    data object None : EmailAuthenticationState
    data object  Loading : EmailAuthenticationState
}