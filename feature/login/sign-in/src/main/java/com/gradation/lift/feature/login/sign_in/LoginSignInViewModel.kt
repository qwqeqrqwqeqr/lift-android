package com.gradation.lift.feature.login.sign_in

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.auth.SignInDefaultUseCase
import com.gradation.lift.domain.usecase.user.ExistUserDetailUseCase
import com.gradation.lift.model.auth.SignInInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginSignInViewModel @Inject constructor(
    private val signInDefaultUseCase: SignInDefaultUseCase,
    private val existUserDetail: ExistUserDetailUseCase,
) : ViewModel() {


    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var autoLoginChecked by mutableStateOf(true)
    var passwordVisible by mutableStateOf(false)
    var passwordVisualTransformation: VisualTransformation by mutableStateOf(
        PasswordVisualTransformation()
    )


    fun onChangePasswordVisible(): (Boolean) -> Unit = {
        passwordVisible = it
        passwordVisualTransformation =
            if (passwordVisualTransformation == VisualTransformation.None) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            }
    }

    fun onChangeAutoLoginChecked() {
        autoLoginChecked = !autoLoginChecked
    }

    fun clearPassword(): () -> Unit = { password = "" }
    fun updateEmail(): (String) -> Unit = { email = it }
    fun updatePassword(): (String) -> Unit = { password = it }

    var signInUiState = MutableStateFlow<SignInUiState>(SignInUiState.None)
    fun signIn() {
        viewModelScope.launch {
            signInUiState.value = SignInUiState.Loading
            if (email.isBlank() || password.isBlank()) {
                signInUiState.value = SignInUiState.Fail(
                    message = "아이디 또는 비밀번호를 입력해주세요."
                )
            } else {
                signInDefaultUseCase(SignInInfo(id = email, password = password)).collect { signInResult ->
                    when (signInResult) {
                        is DataState.Fail -> {
                            signInUiState.value = SignInUiState.Fail(signInResult.message)
                        }
                        is DataState.Success -> {
                            existUserDetail().collect { existUserDetailResult ->
                                when (existUserDetailResult) {
                                    is DataState.Fail -> {
                                        signInUiState.value =
                                            SignInUiState.Fail(existUserDetailResult.message)
                                    }
                                    is DataState.Success -> signInUiState.value =
                                        SignInUiState.Success(
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


sealed interface SignInUiState {

    data class Success(val existUserDetail: Boolean) : SignInUiState
    data class Fail(val message: String) : SignInUiState
    object Loading : SignInUiState
    object None : SignInUiState
}
