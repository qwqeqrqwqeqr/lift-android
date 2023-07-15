package com.gradation.lift.feature.login.sign_up

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.gradation.lift.common.model.DataState
import com.gradation.lift.common.utils.*
import com.gradation.lift.domain.usecase.checker.CheckDuplicateEmailUseCase
import com.gradation.lift.model.user.Email
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.SignUpKey.EMAIL_KEY
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.SignUpKey.PASSWORD_KEY
import com.gradation.lift.navigation.saved_state.setStringValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@ExperimentalCoroutinesApi
@FlowPreview
@HiltViewModel
class LoginSignUpViewModel @Inject constructor(
    private val checkDuplicateEmailUseCase: CheckDuplicateEmailUseCase,
) : ViewModel() {


    var email = MutableStateFlow("")
    var password = MutableStateFlow("")
    var passwordVerification = MutableStateFlow("")


    var emailValidationSupportText: StateFlow<Validator> =
        email.debounce(1000).distinctUntilChanged().flatMapLatest { email ->
            validateEmail(email)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Validator()
        )

    var passwordValidationSupportText: StateFlow<Validator> =
        password.map { validatePassWord(it) }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Validator()
        )


    var passwordVerificationValidationSupportText: StateFlow<Validator> =
        passwordVerification.combine(password) { passwordVerification, password ->
            validatePasswordVerification(password, passwordVerification)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Validator()
        )


    var navigateCondition by mutableStateOf(false)


    var passwordVisible = MutableStateFlow(false)
    var passwordVerificationVisible = MutableStateFlow(false)

    var passwordVisualTransformation: VisualTransformation by mutableStateOf(
        PasswordVisualTransformation()
    )
    var passwordVerificationVisualTransformation: VisualTransformation by mutableStateOf(
        PasswordVisualTransformation()
    )


    internal fun clearPassword(): () -> Unit = { password.value = "" }
    internal fun clearPasswordVerification(): () -> Unit = { passwordVerification.value = "" }

    internal fun updateEmail(): (String) -> Unit = {
        email.value = it
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
        passwordVisible = it
        passwordVisualTransformation =
            if (passwordVisualTransformation == VisualTransformation.None) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            }
    }

    internal fun onChangePasswordVerificationVisible(): (Boolean) -> Unit = {
        passwordVerificationVisible = it
        passwordVerificationVisualTransformation =
            if (passwordVerificationVisualTransformation == VisualTransformation.None) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            }
    }


    private fun updateNavigateCondition() {
        navigateCondition = (emailValidationSupportText.status &&
                passwordValidationSupportText.status &&
                passwordVerificationValidationSupportText.status)
    }

    fun updateKey(navController: NavController) {
        navController.setStringValue(EMAIL_KEY, email)
        navController.setStringValue(PASSWORD_KEY, password)
    }

    private fun validateEmail(email: String): Flow<Validator> {
        return checkDuplicateEmailUseCase(Email(email)).map {
            when (it) {
                is DataState.Fail -> Validator(false, "")
                is DataState.Success -> if (it.data) {
                    Validator(false, "이미 사용중인 닉네임이에요")
                } else {
                    if (emailValidator(email)) {
                        Validator(true, "")
                    } else {
                        if (email.isBlank()) {
                            Validator(false, "")
                        } else {
                            Validator(false, "이메일 형식이 올바르지 않습니다.")
                        }
                    }
                }
            }
        }
    }

    private fun validatePassWord(password: String): Validator =
        if (!lengthValidator(password, 8, 16)) {
            Validator(status = false, message = "8~16자이내로 입력해주세요")
        } else if (!passwordValidator(password)) {
            Validator(status = false, message = "영문, 숫자 및 특수 문자를 포함해주세요")
        } else {
            Validator(status = true, message = "")
        }


    private fun validatePasswordVerification(
        password: String,
        passwordVerification: String,
    ): Validator =
        if (!passwordVerificationValidator(
                password = password, passwordVerification = passwordVerification
            )
        ) Validator(status = false, message = "비밀번호가 일치하지 않아요") else Validator(
            status = true,
            message = ""
        )


}


