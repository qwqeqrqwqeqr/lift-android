package com.gradation.lift.feature.login.verification

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gradation.lift.ui.SavedStateHandleKey.SignUpKey.EMAIL_KEY
import com.gradation.lift.ui.SavedStateHandleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginVerificationViewModel  @Inject constructor(
    private val savedStateHandle: SavedStateHandle

): ViewModel() {

    var test: String =savedStateHandle.get<String>(EMAIL_KEY) ?: "오류"

    init {

    }

}
