package com.gradation.lift.feature.login.resetPassword.data

sealed interface UpdatePasswordState {
    data object Success : UpdatePasswordState
    data class Fail(val message: String) : UpdatePasswordState
    data object None : UpdatePasswordState
}