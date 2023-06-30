package com.gradation.lift.feature.login.sign_up

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gradation.lift.common.utils.*
import com.gradation.lift.ui.SavedStateHandleKey
import com.gradation.lift.ui.SavedStateHandleKey.SignKey.EMAIL_KEY
import com.gradation.lift.ui.SavedStateHandleKey.SignKey.PASSWORD_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginSignUpViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    var email by mutableStateOf("")

    var password by mutableStateOf("")
    var passwordVerification by mutableStateOf("")

    var passwordVisible by mutableStateOf(false)
    var passwordVerificationVisible by mutableStateOf(false)

    var passwordVisualTransformation: VisualTransformation by mutableStateOf(
        PasswordVisualTransformation()
    )
    var passwordVerificationVisualTransformation: VisualTransformation by mutableStateOf(
        PasswordVisualTransformation()
    )


    var emailValidationSupportText by mutableStateOf(Validator())
    var passwordValidationSupportText by mutableStateOf(Validator())
    var passwordVerificationValidationSupportText by mutableStateOf(Validator())


    var navigateCondition by mutableStateOf(false)

    internal fun clearPassword(): () -> Unit = { password = "" }
    internal fun clearPasswordVerification(): () -> Unit = { passwordVerification = "" }
    internal fun updateEmail(): (String) -> Unit = {
        email = it
        validateEmail()
        updateNavigateCondition()
    }

    internal fun updatePassword(): (String) -> Unit = {
        password = it
        validatePassword()
        updateNavigateCondition()
    }


    internal fun updatePasswordVerification(): (String) -> Unit = {
        passwordVerification = it
        validatePasswordVerification()
        updateNavigateCondition()
    }


    internal fun onChangePasswordVisible(): (Boolean) -> Unit = {
        passwordVisible = !passwordVisible
        passwordVisualTransformation =
            if (passwordVisualTransformation == VisualTransformation.None) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            }
    }

    internal fun onChangePasswordVerificationVisible(): (Boolean) -> Unit = {
        passwordVerificationVisible = !passwordVerificationVisible
        passwordVerificationVisualTransformation =
            if (passwordVerificationVisualTransformation == VisualTransformation.None) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            }
    }

    private fun validateEmail() {
        emailValidationSupportText = if (emailValidator(email)) {
            emailValidationSupportText.copy(status = true, message = "")
        } else {
            emailValidationSupportText.copy(status = false, message = "이메일 형식이 올바르지 않습니다.")
        }
    }


    private fun validatePassword() {
        passwordValidationSupportText = if (!lengthValidator(password, 8, 16)) {
            passwordValidationSupportText.copy(status = false, message = "8~16자이내로 입력해주세요")
        } else if (!passwordValidator(password)) {
            passwordValidationSupportText.copy(status = false, message = "영문이나 숫자를 포함해주세요")
        } else {
            passwordValidationSupportText.copy(status = true, message = "")
        }
    }

    private fun validatePasswordVerification() {
        passwordVerificationValidationSupportText = if (!passwordVerificationValidator(
                password = password,
                passwordVerification = passwordVerification
            )
        ) {
            passwordVerificationValidationSupportText.copy(
                status = false,
                message = "비밀번호가 일치하지 않아요"
            )
        } else {
            passwordVerificationValidationSupportText.copy(status = true, message = "")
        }
    }

    fun updateNavigateCondition() {
        navigateCondition = (emailValidationSupportText.status &&
                passwordValidationSupportText.status &&
                passwordVerificationValidationSupportText.status)
    }


    fun updateKey() {
        with(savedStateHandle) {
            set(EMAIL_KEY, email)
            set(PASSWORD_KEY, password)
        }
    }

}


