package com.gradation.lift.feature.login.sign_in.data


sealed interface SignInState {
    data class Success(val existUserDetail: Boolean) : SignInState
    data class Fail(val message: String) : SignInState
    object None : SignInState
}