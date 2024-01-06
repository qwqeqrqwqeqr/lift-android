package com.gradation.lift.feature.login.verifyEmail.data


/**
* [EmailValidationState]
* 이메일 인증 성공여부 상태
* @since 2024-01-06 18:41:33
*/
sealed interface EmailValidationState {

    data class Success(val isSuccess:Boolean): EmailValidationState
    data class Fail(val message: String) : EmailValidationState
    data object None : EmailValidationState
}