package com.gradation.lift.feature.login.signIn.data.state

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.auth.ConnectOAuthFromKakaoUseCase
import com.gradation.lift.domain.usecase.auth.ConnectOAuthFromNaverUseCase
import com.gradation.lift.domain.usecase.auth.SignInKakaoUseCase
import com.gradation.lift.domain.usecase.auth.SignInNaverUseCase
import com.gradation.lift.domain.usecase.user.ExistUserDetailUseCase
import com.gradation.lift.oauth.common.OAuthConnectState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class OAuthSignInState(
    private val viewModelScope: CoroutineScope,
    private val signInNaverUseCase: SignInNaverUseCase,
    private val signInKakaoUseCase: SignInKakaoUseCase,
    private val existUserDetail: ExistUserDetailUseCase,
    private val connectOAuthFromNaverUseCase: ConnectOAuthFromNaverUseCase,
    private val connectOAuthFromKakaoUseCase: ConnectOAuthFromKakaoUseCase,
    val naverOAuthConnectState: MutableStateFlow<OAuthConnectState> =
        MutableStateFlow(OAuthConnectState.None),
    val kakaoOAuthConnectState: MutableStateFlow<OAuthConnectState> =
        MutableStateFlow(OAuthConnectState.None),
    var oAuthSignInState: MutableStateFlow<SignInState> = MutableStateFlow<SignInState>(SignInState.None),
) {

    val updateOAuthSignInState: (SignInState) -> Unit = { oAuthSignInState.value = it }


    val connectOAuthFromNaver: () -> Unit = {
        viewModelScope.launch {
            connectOAuthFromNaverUseCase().collect {
                when (it) {
                    is DataState.Fail -> {
                        naverOAuthConnectState.value = OAuthConnectState.Fail(it.message)
                    }

                    is DataState.Success -> {
                        naverOAuthConnectState.value = OAuthConnectState.Success

                    }
                }

            }
        }
    }

    val connectOAuthFromKakao: () -> Unit = {
        viewModelScope.launch {
            connectOAuthFromKakaoUseCase().collect {
                when (it) {
                    is DataState.Fail -> {
                        kakaoOAuthConnectState.value = OAuthConnectState.Fail(it.message)
                    }

                    is DataState.Success -> {
                        kakaoOAuthConnectState.value = OAuthConnectState.Success

                    }
                }
            }
        }
    }

    /**
     * [signInNaver]
     */
    fun signInNaver(){
        viewModelScope.launch {
            signInNaverUseCase().collect { signInResult ->
                when (signInResult) {
                    is DataState.Fail -> {
                        oAuthSignInState.value = SignInState.Fail(signInResult.message)
                    }

                    is DataState.Success -> {
                        existUserDetail().collect { existUserDetailResult ->
                            when (existUserDetailResult) {
                                is DataState.Fail -> {
                                    oAuthSignInState.value = SignInState.Fail("로그인에 실패하였습니다.")
                                }

                                is DataState.Success -> oAuthSignInState.value =
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
     */
    fun signInKakao() {
        viewModelScope.launch {
            signInKakaoUseCase().collect { signInResult ->
                when (signInResult) {
                    is DataState.Fail -> {
                        oAuthSignInState.value = SignInState.Fail(signInResult.message)
                    }

                    is DataState.Success -> {
                        existUserDetail().collect { existUserDetailResult ->
                            when (existUserDetailResult) {
                                is DataState.Fail -> {
                                    oAuthSignInState.value = SignInState.Fail("로그인에 실패하였습니다.")
                                }

                                is DataState.Success -> oAuthSignInState.value =
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