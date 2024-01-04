package com.gradation.lift.feature.login.signUpCreateEmail.data


/**
* [EmailValidationState]
* 이메일 인증 성공여부 상태
* @since 2023-12-29 16:56:13
*/
sealed interface EmailValidationState {

    data class Success(val isSuccess:Boolean): EmailValidationState
    data class Fail(val message: String) : EmailValidationState
    data object None : EmailValidationState
}