package com.gradation.lift.feature.login.signIn.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.domain.usecase.auth.CheckExistUserUseCase
import com.gradation.lift.domain.usecase.auth.SignInGoogleUseCase
import com.gradation.lift.domain.usecase.auth.SignInKakaoUseCase
import com.gradation.lift.domain.usecase.auth.SignInNaverUseCase
import com.gradation.lift.domain.usecase.user.ExistUserDetailUseCase
import com.gradation.lift.feature.login.signIn.data.state.OAuthSignInState
import com.gradation.lift.oauth.common.OAuthConnectionManager
import com.gradation.lift.oauth.google.GoogleOauthManager
import com.gradation.lift.oauth.kakao.KakaoOauthManager
import com.gradation.lift.oauth.naver.NaverOauthManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    signInNaverUseCase: SignInNaverUseCase,
    signInKakaoUseCase: SignInKakaoUseCase,
    signInGoogleUseCase: SignInGoogleUseCase,
    checkExistUserUseCase: CheckExistUserUseCase,
    existUserDetail: ExistUserDetailUseCase,
    oAuthConnectionManager: OAuthConnectionManager,
    naverOauthManager: NaverOauthManager,
    googleOauthManager: GoogleOauthManager,
    kakaoOauthManager: KakaoOauthManager,
) : ViewModel() {

    val oAuthSignInState = OAuthSignInState(
        viewModelScope,
        signInNaverUseCase,
        signInKakaoUseCase,
        signInGoogleUseCase,
        checkExistUserUseCase,
        existUserDetail,
        oAuthConnectionManager,
        naverOauthManager,
        googleOauthManager,
        kakaoOauthManager
    )

}


