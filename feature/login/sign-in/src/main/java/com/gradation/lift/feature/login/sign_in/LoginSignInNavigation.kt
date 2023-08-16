package com.gradation.lift.feature.login.sign_in

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.*
import com.gradation.lift.oauth.state.OAuthSignInState

fun loginSignInScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
    naverOAuthSignInState: OAuthSignInState,
    kakaoOauthSignInState: OAuthSignInState,
    signInNaver: ()->Unit,
    signInKakao: ()->Unit,
) {

        navGraphBuilder.composable(Router.LOGIN_SIGN_IN_ROUTER_NAME) {
            LoginSignInRoute(
                navigateToLoginFindEmailPassword = { navController.navigateToLoginFindEmailPassword() },
                navigateToLoginSignUp = { navController.navigateToLoginSignUp() },
                navigateLoginToHome= { navController.navigateLoginToHome() },
                navigateLoginToRegisterDetail= { navController.navigateLoginToRegisterDetail() },
                naverOAuthSignInState =naverOAuthSignInState,
                kakaoOauthSignInState =kakaoOauthSignInState,
                signInNaver = { signInNaver() },
                signInKakao = { signInKakao() }
            )

    }

}
