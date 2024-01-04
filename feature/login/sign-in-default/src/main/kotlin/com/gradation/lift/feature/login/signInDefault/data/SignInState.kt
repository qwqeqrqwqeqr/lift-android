package com.gradation.lift.feature.login.signInDefault.data


/**
* [SignInState]
* 로그인 상태
* @since 2023-12-29 00:03:44
*/
sealed interface SignInState {

    data class Success(val existUserDetail: Boolean) : SignInState
    data class Fail(val message: String) : SignInState
    data object None : SignInState
}