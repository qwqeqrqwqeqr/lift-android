package com.gradation.lift.feature.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.login.complete.loginCompleteScreen
import com.gradation.lift.feature.login.find_email_password.loginFindEmailPasswordScreen
import com.gradation.lift.feature.login.sign_in.loginSignInScreen
import com.gradation.lift.feature.login.sign_up.loginSignUpScreen
import com.gradation.lift.navigation.Router.LOGIN_GRAPH_NAME
import com.gradation.lift.navigation.Router.LOGIN_SIGN_IN_ROUTER_NAME
import com.gradation.lift.oauth.common.OAuthConnectState
import kotlinx.coroutines.flow.MutableStateFlow


fun loginGraphBuilder(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
    naverOAuthConnectState: MutableStateFlow<OAuthConnectState>,
    kakaoOAuthConnectState: MutableStateFlow<OAuthConnectState>,
    connectOAuthFromNaver: () -> Unit,
    connectOAuthFromKakao: () -> Unit,
) {
    navGraphBuilder.navigation(
        route = LOGIN_GRAPH_NAME,
        startDestination = LOGIN_SIGN_IN_ROUTER_NAME,
    ) {
        loginSignInScreen(
            navController = navController,
            navGraphBuilder = this,
            naverOAuthConnectState =naverOAuthConnectState,
            kakaoOAuthConnectState =kakaoOAuthConnectState,
            connectOAuthFromNaver =  connectOAuthFromNaver,
            connectOAuthFromKakao =  connectOAuthFromKakao
        )
        loginSignUpScreen(navController, this)
        loginCompleteScreen(navController, this)
        loginFindEmailPasswordScreen(navController, this)
    }

}