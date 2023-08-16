package com.gradation.lift.feature.login.sign_in

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.*
import com.gradation.lift.oauth.state.OAuthConnectState

fun loginSignInScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
    naverOAuthConnectState: OAuthConnectState,
    kakaoOauthConnectState: OAuthConnectState,
    connectOAuthFromNaver: ()->Unit,
    connectOAuthFromKakao: ()->Unit,
) {
        navGraphBuilder.composable(Router.LOGIN_SIGN_IN_ROUTER_NAME) {


            LoginSignInRoute(
                navigateToLoginFindEmailPassword = { navController.navigateToLoginFindEmailPassword() },
                navigateToLoginSignUp = { navController.navigateToLoginSignUp() },
                navigateLoginToHome= { navController.navigateLoginToHome() },
                navigateLoginToRegisterDetail= { navController.navigateLoginToRegisterDetail() },
                naverOAuthConnectState =naverOAuthConnectState,
                kakaoOauthConnectState =kakaoOauthConnectState,
                connectOAuthFromNaver = { connectOAuthFromNaver() },
                connectOAuthFromKakao = { connectOAuthFromKakao() }
            )

    }

}
