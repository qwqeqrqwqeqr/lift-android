package com.gradation.lift.feature.login.sign_up

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


    var emailValidator: StateFlow<Validator> =
        email.debounce(1000).distinctUntilChanged().flatMapLatest { email ->
            validateEmail(email)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Validator()
        )

    var passwordValidator: StateFlow<Validator> =
        password.map { validatePassWord(it) }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Validator()
        )


    var passwordVerificationValidator: StateFlow<Validator> =
        passwordVerification.combine(password) { passwordVerification, password ->
            validatePasswordVerification(password, passwordVerification)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Validator()
        )


    var navigateCondition: StateFlow<Boolean> = combine(
        emailValidator,
        passwordValidator,
        passwordVerificationValidator
    ) { e1, e2, e3 ->
        (e1.status && e2.status && e3.status)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = false
    )


    var passwordVisible = MutableStateFlow(false)
    var passwordVerificationVisible = MutableStateFlow(false)

    var passwordVisualTransformation = passwordVisible.map {
        if (it) VisualTransformation.None else PasswordVisualTransformation()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = PasswordVisualTransformation()
    )

    var passwordVerificationVisualTransformation = passwordVerificationVisible.map {
        if (it) VisualTransformation.None else PasswordVisualTransformation()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = PasswordVisualTransformation()
    )


    internal fun clearPassword(): () -> Unit = { password.value = "" }
    internal fun clearPasswordVerification(): () -> Unit = { passwordVerification.value = "" }


    internal fun updateEmail(): (String) -> Unit = { email.value = it }
    internal fun updatePassword(): (String) -> Unit = { password.value = it }
    internal fun updatePasswordVerification(): (String) -> Unit =
        { passwordVerification.value = it }


    internal fun updatePasswordVisible(): (Boolean) -> Unit = { passwordVisible.value = it }
    internal fun updatePasswordVerificationVisible(): (Boolean) -> Unit =
        { passwordVerificationVisible.value = it }


    private fun validateEmail(email: String): Flow<Validator> {
        return checkDuplicateEmailUseCase(Email(email)).map {
            when (it) {
                is DataState.Fail -> Validator(false, "")
                is DataState.Success -> if (it.data) {
                    Validator(false, "이미 사용중인 이메일이에요.")
                } else {
                    if (email.isBlank()) {
                        Validator(false, "")
                    } else if (emailValidator(email)) {
                        Validator(true, "")
                    } else {
                        Validator(false, "이메일 형식이 올바르지 않습니다.")
                    }
                }
            }
        }
    }


    private fun validatePassWord(password: String): Validator =
        if (password.isBlank()) {
            Validator(false, "")
        } else if (!passwordValidator(password)) {
            Validator(status = false, message = "영문, 숫자 및 특수 문자를 포함해주세요")
        } else if (!lengthValidator(password, 8, 16)) {
            Validator(status = false, message = "8~16자이내로 입력해주세요")
        } else {
            Validator(status = true, message = "")
        }


    private fun validatePasswordVerification(
        password: String,
        passwordVerification: String,
    ): Validator =
        if (passwordVerification.isBlank()) {
            Validator(false, "")
        } else if (passwordVerification.isBlank() && password.isBlank()) {
            Validator(false, "")
        } else if (!passwordVerificationValidator(
                password = password, passwordVerification = passwordVerification
            )
        ) {
            Validator(status = false, message = "비밀번호가 일치하지 않아요")
        } else {
            Validator(
                status = true, message = ""
            )
        }


    fun updateKey(navController: NavController) {
        navController.setStringValue(EMAIL_KEY, email.value)
        navController.setStringValue(PASSWORD_KEY, password.value)
    }

}


