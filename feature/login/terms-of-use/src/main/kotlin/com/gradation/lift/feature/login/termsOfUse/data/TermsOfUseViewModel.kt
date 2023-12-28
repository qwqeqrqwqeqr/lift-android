package com.gradation.lift.feature.login.termsOfUse.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.auth.SignInDefaultUseCase
import com.gradation.lift.domain.usecase.auth.SignInGoogleUseCase
import com.gradation.lift.domain.usecase.auth.SignInKakaoUseCase
import com.gradation.lift.domain.usecase.auth.SignInNaverUseCase
import com.gradation.lift.domain.usecase.auth.SignUpDefaultUseCase
import com.gradation.lift.domain.usecase.auth.SignUpGoogleUseCase
import com.gradation.lift.domain.usecase.auth.SignUpKakaoUseCase
import com.gradation.lift.domain.usecase.auth.SignUpNaverUseCase
import com.gradation.lift.domain.usecase.terms.CreateUserTermsConsentUseCase
import com.gradation.lift.feature.login.common.data.LoginMethodState
import com.gradation.lift.feature.login.termsOfUse.data.state.CreateUserTermsConsentState
import com.gradation.lift.feature.login.termsOfUse.data.state.SignUpState
import com.gradation.lift.model.model.auth.DefaultSignInInfo
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
    private val signInNaverUseCase: SignInNaverUseCase,
    private val signInKakaoUseCase: SignInKakaoUseCase,
    private val signInGoogleUseCase: SignInGoogleUseCase,
    private val signInDefaultUseCase: SignInDefaultUseCase,
) : ViewModel() {

    val signUpState: MutableStateFlow<SignUpState> = MutableStateFlow(SignUpState.None)
    val createUserTermsConsentState: MutableStateFlow<CreateUserTermsConsentState> =
        MutableStateFlow(CreateUserTermsConsentState.None)
    val updateSignUpState: (SignUpState) -> Unit = { signUpState.value = it }
    val updateCreateUserTermsConsentState: (CreateUserTermsConsentState) -> Unit =
        { createUserTermsConsentState.value = it }

    fun createUserTermsConsent(
        consent: Boolean,
        marketingConsent: Boolean,
    ) {
        viewModelScope.launch {
            createUserTermsConsentUseCase(consent, marketingConsent).collect {
                when (it) {
                    is DataState.Fail -> {
                        createUserTermsConsentState.value =
                            CreateUserTermsConsentState.Fail("회원가입을 실패하였습니다.\n잠시후에 다시 시도해주세요.")
                    }

                    is DataState.Success -> {
                        createUserTermsConsentState.value = CreateUserTermsConsentState.Success
                    }
                }
            }
        }
    }

    fun signUp(
        loginMethodState: LoginMethodState,
    ) {
        viewModelScope.launch {
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
                                SignUpState.Fail("회원가입을 실패하였습니다.\n잠시후에 다시 시도해주세요.")

                            is DataState.Success -> {
                                if (defaultResult.data) {

                                    signInDefaultUseCase(
                                        DefaultSignInInfo(
                                            id = loginMethodState.email,
                                            password = loginMethodState.password
                                        )
                                    ).collect {
                                        when (it) {
                                            is DataState.Fail -> signUpState.value =SignUpState.Fail("회원가입을 실패하였습니다.\n잠시후에 다시 시도해주세요.")
                                            is DataState.Success -> {
                                                signUpState.value = SignUpState.Success
                                            }
                                        }
                                    }
                                } else {
                                    signUpState.value =
                                        SignUpState.Fail("회원가입을 실패하였습니다.\n잠시후에 다시 시도해주세요.")

                                }
                            }
                        }
                    }
                }

                LoginMethodState.Google -> {
                    signUpGoogleUseCase().collect { googleResult ->
                        when (googleResult) {
                            is DataState.Fail -> signUpState.value =
                                SignUpState.Fail("회원가입을 실패하였습니다.\n잠시후에 다시 시도해주세요.")

                            is DataState.Success -> {
                                if (googleResult.data) {
                                    signInGoogleUseCase().collect {
                                        when (it) {
                                            is DataState.Fail ->signUpState.value = SignUpState.Fail("회원가입을 실패하였습니다.\n잠시후에 다시 시도해주세요.")
                                            is DataState.Success -> {
                                                signUpState.value = SignUpState.Success
                                            }
                                        }
                                    }
                                } else {
                                    signUpState.value =
                                        SignUpState.Fail("회원가입을 실패하였습니다.\n잠시후에 다시 시도해주세요.")

                                }
                            }
                        }
                    }
                }

                LoginMethodState.Kakao -> {
                    signUpKakaoUseCase().collect { kakaoResult ->
                        when (kakaoResult) {
                            is DataState.Fail -> signUpState.value =
                                SignUpState.Fail("회원가입을 실패하였습니다.\n잠시후에 다시 시도해주세요.")

                            is DataState.Success -> {
                                if (kakaoResult.data) {
                                    signInKakaoUseCase().collect {
                                        when (it) {
                                            is DataState.Fail -> signUpState.value =SignUpState.Fail("회원가입을 실패하였습니다.\n잠시후에 다시 시도해주세요.")
                                            is DataState.Success -> {
                                                signUpState.value = SignUpState.Success
                                            }
                                        }
                                    }
                                } else {
                                    signUpState.value =
                                        SignUpState.Fail("회원가입을 실패하였습니다.\n잠시후에 다시 시도해주세요.")
                                }
                            }
                        }
                    }
                }

                else -> {
                    signUpNaverUseCase().collect { naverResult ->
                        when (naverResult) {
                            is DataState.Fail -> signUpState.value =
                                SignUpState.Fail("회원가입을 실패하였습니다.\n잠시후에 다시 시도해주세요.")

                            is DataState.Success -> {
                                if (naverResult.data) {
                                    signInNaverUseCase().collect {
                                        when (it) {
                                            is DataState.Fail ->  signUpState.value =SignUpState.Fail("회원가입을 실패하였습니다.\n잠시후에 다시 시도해주세요.")
                                            is DataState.Success -> {
                                                signUpState.value = SignUpState.Success
                                            }
                                        }
                                    }
                                } else {
                                    signUpState.value =
                                        SignUpState.Fail("회원가입을 실패하였습니다.\n잠시후에 다시 시도해주세요.")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


