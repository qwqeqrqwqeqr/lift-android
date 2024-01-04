package com.gradation.lift.feature.login.signIn.data.state

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.auth.CheckExistUserUseCase
import com.gradation.lift.domain.usecase.auth.SignInGoogleUseCase
import com.gradation.lift.domain.usecase.auth.SignInKakaoUseCase
import com.gradation.lift.domain.usecase.auth.SignInNaverUseCase
import com.gradation.lift.domain.usecase.user.ExistUserDetailUseCase
import com.gradation.lift.model.model.auth.LoginMethod
import com.gradation.lift.oauth.common.OAuthConnectionManager
import com.gradation.lift.oauth.google.GoogleOauthManager
import com.gradation.lift.oauth.kakao.KakaoOauthManager
import com.gradation.lift.oauth.naver.NaverOauthManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class OAuthSignInState(
    private val viewModelScope: CoroutineScope,
    private val signInNaverUseCase: SignInNaverUseCase,
    private val signInKakaoUseCase: SignInKakaoUseCase,
    private val signInGoogleUseCase: SignInGoogleUseCase,
    private val checkExistUserUseCase: CheckExistUserUseCase,
    private val existUserDetail: ExistUserDetailUseCase,
    private val oAuthConnectionManager: OAuthConnectionManager,
    private val naverOauthManager: NaverOauthManager,
    private val googleOauthManager: GoogleOauthManager,
    private val kakaoOauthManager: KakaoOauthManager,
    var oAuthSignInState: MutableStateFlow<SignInState> = MutableStateFlow<SignInState>(SignInState.None),
    var connectState: MutableStateFlow<ConnectState> = MutableStateFlow<ConnectState>(ConnectState.None),
) {

    val updateOAuthSignInState: (SignInState) -> Unit = { oAuthSignInState.value = it }
    val updateConnectState: (ConnectState) -> Unit = { it ->
        if (it is ConnectState.Success) {
            viewModelScope.launch {
                combine(
                    when (it.loginMethod) {
                        is LoginMethod.Google -> googleOauthManager.getUserId()
                        is LoginMethod.Kakao -> kakaoOauthManager.getUserId()
                        else -> naverOauthManager.getUserId()
                    }, when (it.loginMethod) {
                        is LoginMethod.Google -> googleOauthManager.getUserEmail()
                        is LoginMethod.Kakao -> kakaoOauthManager.getUserEmail()
                        else -> naverOauthManager.getUserEmail()
                    }
                ) { id, email ->
                    when (id) {
                        is DataState.Fail -> connectState.value =ConnectState.Fail("연결을 실패하였습니다.")
                        is DataState.Success -> {
                            when(email){
                                is DataState.Fail ->connectState.value =ConnectState.Fail("연결을 실패하였습니다.")
                                is DataState.Success -> {
                                    checkExistUserUseCase(id.data,email.data).collect { checkExistUserResult ->
                                        when (checkExistUserResult) {
                                            is DataState.Fail -> ConnectState.Fail("연결을 실패하였습니다.")
                                            is DataState.Success -> {
                                                connectState.value = ConnectState.Success(
                                                    loginMethod = it.loginMethod,
                                                    isSigned = checkExistUserResult.data
                                                )
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }.collect()
            }
        } else {
            connectState.value = it
        }
    }


    val connectOAuthFromNaver: (Context) -> Unit = {
        viewModelScope.launch {
            oAuthConnectionManager.connectNaver(it).collect {
                when (it) {
                    is DataState.Fail -> {
                        updateConnectState(
                            ConnectState.Fail(
                                it.message
                            )
                        )
                    }

                    is DataState.Success -> {
                        updateConnectState(
                            ConnectState.Success(
                                LoginMethod.Naver()
                            )
                        )
                    }
                }

            }
        }
    }

    val connectOAuthFromKakao: () -> Unit = {
        viewModelScope.launch {
            oAuthConnectionManager.connectKakao().collect {
                when (it) {
                    is DataState.Fail -> {
                        updateConnectState(
                            ConnectState.Fail(
                                it.message
                            )
                        )
                    }
                    is DataState.Success -> {
                        updateConnectState(
                            ConnectState.Success(
                                LoginMethod.Kakao()
                            )
                        )

                    }
                }
            }
        }
    }

    fun getGoogleClient(): GoogleSignInClient = oAuthConnectionManager.getGoogleClient()

    /**
     * [signInNaver]
     */
    fun signInNaver() {
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
                                    SignInState.Success(existUserDetailResult.data)
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

    /**
     * [signInGoogle]
     */
    fun signInGoogle() {
        viewModelScope.launch {
            signInGoogleUseCase().collect { signInResult ->
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