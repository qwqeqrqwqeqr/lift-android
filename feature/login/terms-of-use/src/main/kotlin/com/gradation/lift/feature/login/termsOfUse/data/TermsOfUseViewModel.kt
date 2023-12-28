package com.gradation.lift.feature.login.termsOfUse.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.auth.SignUpDefaultUseCase
import com.gradation.lift.domain.usecase.auth.SignUpGoogleUseCase
import com.gradation.lift.domain.usecase.auth.SignUpKakaoUseCase
import com.gradation.lift.domain.usecase.auth.SignUpNaverUseCase
import com.gradation.lift.domain.usecase.terms.CreateUserTermsConsentUseCase
import com.gradation.lift.feature.login.common.data.LoginMethodState
import com.gradation.lift.feature.login.termsOfUse.data.state.SignUpState
import com.gradation.lift.model.model.auth.DefaultSignUpInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TermsOfUseViewModel @Inject constructor(
    private val createUserTermsConsentUseCase: CreateUserTermsConsentUseCase,
    private val signUpDefaultUseCase: SignUpDefaultUseCase,
    private val signUpGoogleUseCase: SignUpGoogleUseCase,
    private val signUpKakaoUseCase: SignUpKakaoUseCase,
    private val signUpNaverUseCase: SignUpNaverUseCase,
) : ViewModel() {

    val signUpState: MutableStateFlow<SignUpState> = MutableStateFlow(SignUpState.None)
    val updateSignUpState: (SignUpState) -> Unit = { signUpState.value = it }

    fun createUserTermsConsent(
        consent: Boolean,
        marketingConsent: Boolean,
        loginMethodState: LoginMethodState,
    ) {
        viewModelScope.launch {
            createUserTermsConsentUseCase(consent, marketingConsent).collect {
                when (it) {
                    is DataState.Fail -> {
                        SignUpState.Fail("회원가입을 실패하였습니다. 잠시후에 다시 시도해주세요.")
                    }

                    is DataState.Success -> {
                        when (loginMethodState) {
                            is LoginMethodState.Default -> {
                                signUpDefaultUseCase(
                                    DefaultSignUpInfo(
                                        id = loginMethodState.email,
                                        password = loginMethodState.password
                                    )
                                ).collect { defaultResult ->
                                    when (defaultResult) {
                                        is DataState.Fail -> signUpState.value =
                                            SignUpState.Fail("회원가입을 실패하였습니다. 잠시후에 다시 시도해주세요.")

                                        is DataState.Success -> signUpState.value =
                                            SignUpState.Success
                                    }
                                }
                            }

                            LoginMethodState.Google -> {
                                signUpGoogleUseCase().collect { googleResult ->
                                    when (googleResult) {
                                        is DataState.Fail -> signUpState.value =
                                            SignUpState.Fail("회원가입을 실패하였습니다. 잠시후에 다시 시도해주세요.")

                                        is DataState.Success -> signUpState.value =
                                            SignUpState.Success
                                    }
                                }
                            }

                            LoginMethodState.Kakao -> {
                                signUpKakaoUseCase().collect { kakaoResult ->
                                    when (kakaoResult) {
                                        is DataState.Fail -> signUpState.value =
                                            SignUpState.Fail("회원가입을 실패하였습니다. 잠시후에 다시 시도해주세요.")

                                        is DataState.Success -> signUpState.value =
                                            SignUpState.Success
                                    }
                                }
                            }

                            else -> {
                                signUpNaverUseCase().collect { naverResult ->
                                    when (naverResult) {
                                        is DataState.Fail -> signUpState.value =
                                            SignUpState.Fail("회원가입을 실패하였습니다. 잠시후에 다시 시도해주세요.")

                                        is DataState.Success -> signUpState.value =
                                            SignUpState.Success
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}