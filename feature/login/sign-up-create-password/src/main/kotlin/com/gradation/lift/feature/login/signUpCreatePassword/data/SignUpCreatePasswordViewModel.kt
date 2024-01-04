package com.gradation.lift.feature.login.signUpCreatePassword.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpCreatePasswordViewModel @Inject constructor(
) : ViewModel() {
    val createPasswordState = CreatePasswordState(viewmodelScope=viewModelScope)
}