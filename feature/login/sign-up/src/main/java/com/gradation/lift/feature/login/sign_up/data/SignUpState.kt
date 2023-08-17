package com.gradation.lift.feature.login.sign_up.data

interface SignUpState {
    object Success : SignUpState
    data class Fail(val message: String) : SignUpState
    object None : SignUpState
}

