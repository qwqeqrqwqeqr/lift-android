package com.gradation.lift.feature.login.signUpCreatePassword.data

import com.gradation.lift.common.utils.Validator
import com.gradation.lift.common.utils.lengthValidator
import com.gradation.lift.common.utils.passwordValidator
import com.gradation.lift.common.utils.passwordVerificationValidator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * [CreatePasswordState]
 */
data class CreatePasswordState(
    val password: MutableStateFlow<String> = MutableStateFlow(""),
    val passwordVerification: MutableStateFlow<String> = MutableStateFlow(""),
    private val viewmodelScope: CoroutineScope,
) {

    val passwordValidator: StateFlow<Validator> = password.map { password ->
        if (password.isEmpty()) {
            Validator(status = true, message = "")
        } else if (!passwordValidator(password)) {
            Validator(status = false, message = "영문, 숫자 및 특수 문자를 포함해주세요")
        } else if (!lengthValidator(password, 8, 16)) {
            Validator(status = false, message = "8~16자이내로 입력해주세요")
        } else {
            Validator(status = true, message = "")
        }

    }.stateIn(
        scope = viewmodelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = Validator(
            status = true,
            message = ""
        )
    )


    val passwordVerificationValidator: StateFlow<Validator> =
        combine(password, passwordVerification) { password, passwordVerification ->
            if (password.isEmpty() || passwordVerification.isEmpty()) {
                Validator(status = true, message = "")
            } else if (!passwordVerificationValidator(
                    password = password,
                    passwordVerification = passwordVerification
                )
            ) {
                Validator(status = false, message = "비밀번호가 일치하지 않아요")
            } else {
                Validator(status = true, message = "")
            }
        }.stateIn(
            scope = viewmodelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Validator(
                status = true,
                message = ""
            )
        )

    val isValidPassword: StateFlow<Boolean> =
        combine(password, passwordValidator) { password, passwordValidator ->
            password.isNotEmpty() && passwordValidator.status
        }.stateIn(
            scope = viewmodelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )

    val isValidPasswordVerification: StateFlow<Boolean> =
        combine(passwordVerification, passwordVerificationValidator) { passwordVerification, passwordVerificationValidator ->
            passwordVerification.isNotEmpty() && passwordVerificationValidator.status
        }.stateIn(
            scope = viewmodelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )

    val updatePassword: (String) -> Unit = { password.value = it }
    val updatePasswordVerification: (String) -> Unit = { passwordVerification.value = it }
    val clearPassword: () -> Unit = { password.value = "" }
    val clearPasswordVerification: () -> Unit = { passwordVerification.value = "" }

}
