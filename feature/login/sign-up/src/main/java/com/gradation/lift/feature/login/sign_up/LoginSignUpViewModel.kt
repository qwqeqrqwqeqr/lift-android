package com.gradation.lift.feature.login.sign_up

import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.common.utils.*
import com.gradation.lift.domain.usecase.auth.SignUpDefaultUseCase
import com.gradation.lift.domain.usecase.checker.CheckDuplicateEmailUseCase
import com.gradation.lift.feature.login.sign_up.data.SignUpState
import com.gradation.lift.model.model.auth.DefaultSignUpInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @property emailText 이메일 텍스트
 * @property passwordText 비밀번호 텍스트
 * @property passwordVerificationText 비밀번호 확인 텍스트
 *
 * @property passwordTextVisibleToggle 비밀번호 가시 여부
 * @property passwordVerificationTextVisibleToggle 비밀번호 확인 가시 여부
 *
 * @property emailValidator 이메일 유효성 확인
 * @property passwordValidator 비밀번호 유효성 확인
 * @property passwordVerificationValidator 비밀번호 확인 유효성 확인
 *
 * @property passwordVisualTransformation  비밀번호 가시성 변수 기본 값은 비 가시로 설정
 * @property passwordVerificationVisualTransformation 비밀번호 확인 가시성 변수 기본 값은 비 가시로 설정
 *
 * @property signUpCondition 회원가입 조건, 조건에 충족하지 않을경우 false로 설정
 * @property signUpState 회원가입 상태
 */
@ExperimentalCoroutinesApi
@FlowPreview
@HiltViewModel
class LoginSignUpViewModel @Inject constructor(
    private val checkDuplicateEmailUseCase: CheckDuplicateEmailUseCase,
    private val signUpDefaultUseCase: SignUpDefaultUseCase,
) : ViewModel() {


    val emailText: MutableStateFlow<String> = MutableStateFlow("")
    val passwordText: MutableStateFlow<String> = MutableStateFlow("")
    val passwordVerificationText: MutableStateFlow<String> = MutableStateFlow("")

    val passwordTextVisibleToggle: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val passwordVerificationTextVisibleToggle: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val emailValidator: StateFlow<Validator> =
        emailText.debounce(1000).distinctUntilChanged().flatMapLatest { email ->
            validateEmail(email)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Validator()
        )

    val passwordValidator: StateFlow<Validator> =
        passwordText.map { validatePassWord(it) }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Validator()
        )


    val passwordVerificationValidator: StateFlow<Validator> =
        combine(passwordText, passwordVerificationText) { password, passwordVerification ->
            validatePasswordVerification(password, passwordVerification)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Validator()
        )


    val passwordVisualTransformation = passwordTextVisibleToggle.map {
        if (it) VisualTransformation.None else PasswordVisualTransformation()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = PasswordVisualTransformation()
    )

    val passwordVerificationVisualTransformation = passwordVerificationTextVisibleToggle.map {
        if (it) VisualTransformation.None else PasswordVisualTransformation()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = PasswordVisualTransformation()
    )


    val signUpCondition: StateFlow<Boolean> = combine(
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

    var signUpState: MutableStateFlow<SignUpState> = MutableStateFlow(SignUpState.None)



    internal fun clearPasswordText(): () -> Unit = { passwordText.value = "" }
    internal fun clearPasswordVerificationText(): () -> Unit =
        { passwordVerificationText.value = "" }


    internal fun updateEmailText(): (String) -> Unit = { emailText.value = it }
    internal fun updatePasswordText(): (String) -> Unit = { passwordText.value = it }
    internal fun updatePasswordVerification(): (String) -> Unit =
        { passwordVerificationText.value = it }

    internal fun updatePasswordVisibleToggle(): (Boolean) -> Unit =
        { passwordTextVisibleToggle.value = it }

    internal fun updatePasswordVerificationVisibleToggle(): (Boolean) -> Unit =
        { passwordVerificationTextVisibleToggle.value = it }

    internal fun updateSignInState(none: SignUpState.None): (SignUpState) -> Unit =
        { signUpState.value = it }


    private fun validateEmail(email: String): Flow<Validator> {
        return checkDuplicateEmailUseCase(email).map {
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


    fun signUp() {
        viewModelScope.launch {
            signUpDefaultUseCase(
                DefaultSignUpInfo(
                    id = emailText.value,
                    password = passwordText.value
                )
            ).collect { signUpResult ->
                when (signUpResult) {
                    is DataState.Fail -> {
                        signUpState.value = SignUpState.Fail(signUpResult.message)
                    }
                    is DataState.Success -> {
                        signUpState.value = SignUpState.Success
                    }
                }
            }
        }
    }
}




