package com.gradation.lift.feature.login.sign_in

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginSignInViewModel  @Inject constructor(
): ViewModel() {



    var email by mutableStateOf("")
    var password by mutableStateOf("")

    fun updateEmail(text: String){
        email = text
    }

    fun updatePassword(text: String){
        password = text
    }
}