package com.gradation.lift.feature.login.signUpCreatePassword.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateSignUpCreatePasswordToTermsOfUseInLoginGraph
import com.gradation.lift.navigation.navigation.navigateSignUpToSignInInLoginGraph

fun NavGraphBuilder.signUpCreatePasswordScreen(
    modifier: Modifier=Modifier,
    navController: NavController,
) {

    val navigateSignUpCreatePasswordToTermsOfUseInLoginGraph: () -> Unit = {
        navController.navigateSignUpCreatePasswordToTermsOfUseInLoginGraph()
    }

    val navigateSignUpToSignInInLoginGraph: () -> Unit = {
        navController.navigateSignUpToSignInInLoginGraph()
    }


    composable(Router.LOGIN_SIGN_UP_CREATE_PASSWORD_ROUTER_NAME) {
        SignUpCreatePasswordRoute(
            modifier,
            navController,
            navigateSignUpCreatePasswordToTermsOfUseInLoginGraph,
            navigateSignUpToSignInInLoginGraph,

        )
    }

}


