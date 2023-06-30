package com.gradation.lift.feature.login.sign_up

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class LoginSignUpViewModel @Inject constructor(
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


    internal fun clearPassword(): () -> Unit = { password = "" }
    internal fun clearPasswordVerification(): () -> Unit = { passwordVerification = "" }
    internal fun updateEmail(): (String) -> Unit = {
        email = it
        validateEmail()
    }
    internal fun updatePassword(): (String) -> Unit = {
        password = it
    }
    internal fun updatePasswordVerification(): (String) -> Unit = {
        passwordVerification = it
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
        emailValidationSupportText = if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailValidationSupportText.copy(status = true, message = "")
        } else {
            emailValidationSupportText.copy(status = false, message = "이메일 형식이 올바르지 않습니다.")
        }
    }


}


data class Validator(
    val status: Boolean = false,
    val message: String = "",
)