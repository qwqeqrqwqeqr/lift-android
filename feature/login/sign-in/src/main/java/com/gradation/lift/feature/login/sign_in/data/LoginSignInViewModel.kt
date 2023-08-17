package com.gradation.lift.feature.login.sign_in.data

import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.auth.SignInDefaultUseCase
import com.gradation.lift.domain.usecase.auth.SignInKakaoUseCase
import com.gradation.lift.domain.usecase.auth.SignInNaverUseCase
import com.gradation.lift.domain.usecase.setting.GetAutoLoginSettingUseCase
import com.gradation.lift.domain.usecase.setting.SetAutoLoginSettingUseCase
import com.gradation.lift.domain.usecase.user.ExistUserDetailUseCase
import com.gradation.lift.model.model.auth.DefaultSignInInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @property emailText  이메일 텍스트 [signInDefault] 에 사용
 * @property passwordText 비밀번호 텍스트 [signInDefault] 에 사용
 * @property passwordVisibleToggle 비밀번호 가시 여부
 * @property autoLoginCheckToggle 자동 로그인 여부, 자동 설징 시 다음 로그인 부터는 자동으로 진행
 * @property passwordVisualTransformation 비밀번호 가시성 변수 기본 값은 비 가시로 설정
 * @property signInState [SignInState] 을 기반으로 하는 로그인 상태
 * @since 2023-08-16 22:29:18
 */
@HiltViewModel
class LoginSignInViewModel @Inject constructor(
    private val signInDefaultUseCase: SignInDefaultUseCase,
    private val setAutoLoginSettingUseCase: SetAutoLoginSettingUseCase,
    private val signInNaverUseCase: SignInNaverUseCase,
    private val signInKakaoUseCase: SignInKakaoUseCase,
    private val existUserDetail: ExistUserDetailUseCase,
    getAutoLoginSettingUseCase: GetAutoLoginSettingUseCase,
) : ViewModel() {


    var emailText: MutableStateFlow<String> = MutableStateFlow("")
    var passwordText: MutableStateFlow<String> = MutableStateFlow("")


    var passwordVisibleToggle: MutableStateFlow<Boolean> = MutableStateFlow(false)


    var autoLoginCheckToggle: StateFlow<Boolean> =
        getAutoLoginSettingUseCase().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = true
        )

    var passwordVisualTransformation =
        passwordVisibleToggle.map {
            if (it) VisualTransformation.None else PasswordVisualTransformation()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = PasswordVisualTransformation()
        )

    var signInState: MutableStateFlow<SignInState> = MutableStateFlow<SignInState>(SignInState.None)


    fun updateEmailText(): (String) -> Unit = { emailText.value = it }
    fun updatePasswordText(): (String) -> Unit = { passwordText.value = it }
    fun clearPasswordText(): () -> Unit = { passwordText.value = "" }

    fun updatePasswordVisibleToggle(): (Boolean) -> Unit = { passwordVisibleToggle.value = it  }

    fun updateAutoLoginCheckToggle(): (Boolean) -> Unit = {
        viewModelScope.launch {
            setAutoLoginSettingUseCase(value = it)
        }
    }


    /**
     * [signInNaver]
     * 네이버 로그인, [signInState]를 업데이트 함
     */
    fun signInNaver(): () -> Unit = {
        viewModelScope.launch {
            signInNaverUseCase().collect { signInResult ->
                when (signInResult) {
                    is DataState.Fail -> {
                        signInState.value = SignInState.Fail(signInResult.message)
                    }
                    is DataState.Success -> {
                        existUserDetail().collect { existUserDetailResult ->
                            when (existUserDetailResult) {
                                is DataState.Fail -> {
                                    signInState.value = SignInState.Fail("로그인에 실패하였습니다.")
                                }
                                is DataState.Success -> signInState.value =
                                    SignInState.Success(
                                        existUserDetailResult.data
                                    )
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * [signInKakao]
     * 카카오 로그인, [signInState]를 업데이트 함
     */
    fun signInKakao(): () -> Unit = {
        viewModelScope.launch {
            signInKakaoUseCase().collect { signInResult ->
                when (signInResult) {
                    is DataState.Fail -> {
                        signInState.value = SignInState.Fail(signInResult.message)
                    }
                    is DataState.Success -> {
                        existUserDetail().collect { existUserDetailResult ->
                            when (existUserDetailResult) {
                                is DataState.Fail -> {
                                    signInState.value = SignInState.Fail("로그인에 실패하였습니다.")
                                }
                                is DataState.Success -> signInState.value =
                                    SignInState.Success(
                                        existUserDetailResult.data
                                    )
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * [signInDefault]
     * 일반 로그인, [signInState]를 업데이트 함
     */
    fun signInDefault(): () -> Unit = {
        viewModelScope.launch {
            if (emailText.value.isBlank() || passwordText.value.isBlank()) {
                signInState.value = SignInState.Fail(
                    message = "아이디 또는 비밀번호를 입력해주세요."
                )
            } else {
                signInDefaultUseCase(
                    DefaultSignInInfo(
                        id = emailText.value,
                        password = passwordText.value
                    )
                ).collect { signInResult ->
                    when (signInResult) {
                        is DataState.Fail -> {
                            signInState.value = SignInState.Fail(signInResult.message)
                        }
                        is DataState.Success -> {
                            existUserDetail().collect { existUserDetailResult ->
                                when (existUserDetailResult) {
                                    is DataState.Fail -> {
                                        signInState.value =
                                            SignInState.Fail("로그인에 실패하였습니다.")
                                    }
                                    is DataState.Success -> signInState.value =
                                        SignInState.Success(
                                            existUserDetailResult.data
                                        )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


