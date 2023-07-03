package com.gradation.lift.feature.login.sign_in

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.auth.SignInUseCase
import com.gradation.lift.model.auth.SignInInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginSignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
) : ViewModel() {


    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var autoLoginChecked by mutableStateOf(true)
    var passwordVisible by mutableStateOf(false)
    var passwordVisualTransformation: VisualTransformation by mutableStateOf(
        PasswordVisualTransformation()
    )


    fun onChangePasswordVisible() {
        passwordVisible = !passwordVisible
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
            if(email.isBlank() || password.isBlank()){
                signInUiState.value = SignInUiState.Fail(
                    message = "아이디 또는 비밀번호를 입력해주세요."
                )
            }else{
                signInUseCase(SignInInfo(id = email, password = password)).map {
                    when (it) {
                        is DataState.Error -> {
                            Log.d("login", "${it.message} 에러")
                            SignInUiState.Fail(it.message)
                        }
                        is DataState.Fail -> {
                            Log.d("login", "${it.message} 실패")
                            SignInUiState.Fail(it.message)
                        }
                        is DataState.Success -> {
                            Log.d("login", "${it.data} 성공")
                            SignInUiState.Success
                        }
                    }
                }.collect {
                    signInUiState.value = it
                }
            }
        }
    }
}


sealed interface SignInUiState {

    object Success : SignInUiState
    data class Fail(val message: String) : SignInUiState
    object Loading : SignInUiState
    object None : SignInUiState
}
