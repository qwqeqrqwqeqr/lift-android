package com.gradation.lift.feature.login.signIn.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.domain.usecase.auth.ConnectOAuthFromKakaoUseCase
import com.gradation.lift.domain.usecase.auth.ConnectOAuthFromNaverUseCase
import com.gradation.lift.domain.usecase.auth.SignInKakaoUseCase
import com.gradation.lift.domain.usecase.auth.SignInNaverUseCase
import com.gradation.lift.domain.usecase.user.ExistUserDetailUseCase
import com.gradation.lift.feature.login.signIn.data.state.OAuthSignInState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    signInNaverUseCase: SignInNaverUseCase,
    signInKakaoUseCase: SignInKakaoUseCase,
    existUserDetail: ExistUserDetailUseCase,
    connectOAuthFromNaverUseCase: ConnectOAuthFromNaverUseCase,
    connectOAuthFromKakaoUseCase: ConnectOAuthFromKakaoUseCase,
) : ViewModel() {

    val oAuthSignInState = OAuthSignInState(
        viewModelScope,
        signInNaverUseCase,
        signInKakaoUseCase,
        existUserDetail,
        connectOAuthFromNaverUseCase,
        connectOAuthFromKakaoUseCase
    )
}


