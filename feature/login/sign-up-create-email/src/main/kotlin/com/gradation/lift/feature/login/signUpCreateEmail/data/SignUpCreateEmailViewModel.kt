package com.gradation.lift.feature.login.signUpCreateEmail.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.common.utils.emailValidator
import com.gradation.lift.domain.usecase.auth.CreateEmailAuthenticationCodeUseCase
import com.gradation.lift.domain.usecase.auth.ValidateEmailAuthenticationUseCase
import com.gradation.lift.model.model.auth.EmailAuthenticationInfo
import com.gradation.lift.model.model.auth.EmailAuthenticationValidationInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalTime
import javax.inject.Inject

@HiltViewModel
class SignUpCreateEmailViewModel @Inject constructor(
    createEmailAuthenticationCodeUseCase: CreateEmailAuthenticationCodeUseCase,
    validateEmailAuthenticationUseCase: ValidateEmailAuthenticationUseCase,
) : ViewModel() {

    val createEmailState = CreateEmailState(viewModelScope = viewModelScope)

    val emailAuthenticationState: MutableStateFlow<EmailAuthenticationState> =
        MutableStateFlow(EmailAuthenticationState.None)
    val emailValidationState: MutableStateFlow<EmailValidationState> =
        MutableStateFlow(EmailValidationState.None)


    val updateEmailAuthenticationState: (EmailAuthenticationState) -> Unit =
        { emailAuthenticationState.value = it }

    val updateEmailValidationState: (EmailValidationState) -> Unit =
        { emailValidationState.value = it }


    val createEmailAuthenticationCode: (String) -> Unit = { email ->
        viewModelScope.launch {
            if (!emailValidator(email)) {
                emailAuthenticationState.value =
                    EmailAuthenticationState.Fail("이메일 형식이 올바르지 않습니다.")
                createEmailState.emailValidator.value = Validator(false, "이메일 형식이 올바르지 않습니다.")
            } else if (createEmailState.authenticationTimer.value.toSecondOfDay() > LocalTime(0, 4, 50).toSecondOfDay()
            ) {
                emailValidationState.value =
                    EmailValidationState.Fail("이미 인증번호를 전송했습니다.\n잠시후에 다시 시도해주세요.")
            } else {
                emailAuthenticationState.value = EmailAuthenticationState.Loading
                createEmailAuthenticationCodeUseCase(
                    EmailAuthenticationInfo(
                        email,
                        false
                    )
                ).collect {
                    when (it) {
                        is DataState.Fail -> {
                            emailAuthenticationState.value =
                                EmailAuthenticationState.Fail(it.message)
                            createEmailState.emailValidator.value = Validator(false, it.message)
                        }

                        is DataState.Success -> {
                            if (it.data) {
                                createEmailState.updateSelectedEmail(email)
                                createEmailState.updateEmailValidator(Validator(true, ""))
                                createEmailState.startTimer()
                            } else {
                                createEmailState.updateEmailValidator(
                                    Validator(
                                        false,
                                        "이미 사용중인 이메일 입니다."
                                    )
                                )
                            }
                            emailAuthenticationState.value =
                                EmailAuthenticationState.Success(it.data)
                        }
                    }
                }
            }
        }
    }

    val validateEmailAuthentication: (String, Int?) -> Unit =
        { email, code ->
            if (email != createEmailState.selectedEmail.value) {
                emailValidationState.value = EmailValidationState.Fail("인증이 유효하지 않습니다.")
                createEmailState.authenticationValidator.value = Validator(false, "인증이 유효하지 않습니다.")
            } else if (createEmailState.authenticationTimer.value.toSecondOfDay() <= 0) {
                emailValidationState.value = EmailValidationState.Fail("인증 시간이 만료되었습니다.")
                createEmailState.authenticationValidator.value = Validator(false, "인증 시간이 만료되었습니다.")
            } else {
                code?.let { it ->
                    viewModelScope.launch {
                        validateEmailAuthenticationUseCase(
                            EmailAuthenticationValidationInfo(
                                email = email,
                                authenticationCode = it,
                                isSigned = false
                            )
                        ).collect {
                            when (it) {
                                is DataState.Fail -> {
                                    emailValidationState.value =
                                        EmailValidationState.Fail("인증을 실패하였습니다.")
                                    createEmailState.authenticationValidator.value =
                                        Validator(false, "인증을 실패하였습니다.")
                                }

                                is DataState.Success -> {
                                    if (it.data) {
                                        createEmailState.updateAuthenticationValidator(
                                            Validator(true, "")
                                        )
                                        createEmailState.stopTimer()
                                    } else {
                                        createEmailState.updateAuthenticationValidator(
                                            Validator(false, "인증을 실패하였습니다.")
                                        )
                                    }
                                    emailValidationState.value =
                                        EmailValidationState.Success(it.data)
                                }
                            }
                        }
                    }
                } ?: {
                    emailValidationState.value = EmailValidationState.Fail("올바르지 않은 번호를 입력하였습니다.")
                    createEmailState.authenticationValidator.value =
                        Validator(false, "올바르지 않은 번호를 입력하였습니다.")
                }
            }
        }


}