package com.gradation.lift.feature.login.signUpCreateEmail.data


/**
* [EmailAuthenticationState]
* 이메일 전송 상태
* @since 2023-12-29 16:56:18
*/
sealed interface EmailAuthenticationState {

    data class Success(val isSuccess :Boolean): EmailAuthenticationState
    data class Fail(val message: String) : EmailAuthenticationState
    data object None : EmailAuthenticationState
    data object  Loading : EmailAuthenticationState
}