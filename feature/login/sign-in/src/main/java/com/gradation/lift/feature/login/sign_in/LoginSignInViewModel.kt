package com.gradation.lift.feature.login.sign_in

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.auth.SignInUseCase
import com.gradation.lift.model.auth.Account
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


    fun updateEmail(text: String) {
        email = text
    }

    fun updatePassword(text: String) {
        password = text
    }

    var signInUiState = MutableStateFlow<SignInUiState>(SignInUiState.None)
    fun signIn() {
        viewModelScope.launch {
            signInUiState.value = SignInUiState.Loading
            signInUseCase(Account(id = email, password = password)).map {
                when (it) {
                    is DataState.Error -> {
                        Log.d("login", "${it.throwable.message} 성공")
                        SignInUiState.Fail
                    }
                    is DataState.Fail -> {
                        Log.d("login", "${it.message} 성공")

                        SignInUiState.Fail
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


sealed interface SignInUiState {

    object Success : SignInUiState
    object Fail : SignInUiState
    object Loading : SignInUiState
    object None : SignInUiState
}
