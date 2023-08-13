package com.gradation.lift.feature.login.sign_in.data

import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.auth.SignInDefaultUseCase
import com.gradation.lift.domain.usecase.setting.GetAutoLoginSettingUseCase
import com.gradation.lift.domain.usecase.setting.SetAutoLoginSettingUseCase
import com.gradation.lift.domain.usecase.user.ExistUserDetailUseCase
import com.gradation.lift.model.model.auth.DefaultSignInInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginSignInViewModel @Inject constructor(
    private val signInDefaultUseCase: SignInDefaultUseCase,
    private val getAutoLoginSettingUseCase: GetAutoLoginSettingUseCase,
    private val setAutoLoginSettingUseCase: SetAutoLoginSettingUseCase,
    private val existUserDetail: ExistUserDetailUseCase,
) : ViewModel() {


    var email = MutableStateFlow("")
    var password = MutableStateFlow("")

    var passwordVisible = MutableStateFlow(false)
    var passwordVisualTransformation: MutableStateFlow<VisualTransformation> = MutableStateFlow(
        PasswordVisualTransformation()
    )


    var autoLoginChecked =
        getAutoLoginSettingUseCase().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = true
        )


    fun changePasswordVisible(): (Boolean) -> Unit = {
        passwordVisible.value = it
        passwordVisualTransformation.value =
            if (passwordVisualTransformation == VisualTransformation.None) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            }
    }

    fun changeAutoLoginChecked(): (Boolean) -> Unit = {
        viewModelScope.launch {
            setAutoLoginSettingUseCase(value = it)
        }

    }


    fun clearPassword(): () -> Unit = { password.update { "" } }
    fun updateEmail(): (String) -> Unit = { email.update { it } }
    fun updatePassword(): (String) -> Unit = { password.update { it } }

    var signInUiState = MutableStateFlow<SignInState>(SignInState.None)
    fun signIn(): () -> Unit = {
        viewModelScope.launch {
            signInUiState.value = SignInState.None
            if (email.value.isBlank() || password.value.isBlank()) {
                signInUiState.value = SignInState.Fail(
                    message = "아이디 또는 비밀번호를 입력해주세요."
                )
            } else {
                signInDefaultUseCase(
                    DefaultSignInInfo(
                        id = email.value,
                        password = password.value
                    )
                ).collect { signInResult ->
                    when (signInResult) {
                        is DataState.Fail -> {
                            signInUiState.value = SignInState.Fail(signInResult.message)
                        }
                        is DataState.Success -> {
                            existUserDetail().collect { existUserDetailResult ->
                                when (existUserDetailResult) {
                                    is DataState.Fail -> {
                                        signInUiState.value =
                                            SignInState.Fail("로그인을 실패하였습니다.")
                                    }
                                    is DataState.Success -> signInUiState.value =
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


