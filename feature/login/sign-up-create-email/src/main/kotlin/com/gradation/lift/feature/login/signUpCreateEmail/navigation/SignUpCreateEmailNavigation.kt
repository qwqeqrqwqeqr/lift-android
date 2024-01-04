package com.gradation.lift.feature.login.signUpCreateEmail.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateSignUpCreateEmailToSignUpCreatePasswordInLoginGraph
import com.gradation.lift.navigation.navigation.navigateSignUpToSignInInLoginGraph

fun NavGraphBuilder.signUpCreateEmailScreen(
    modifier: Modifier=Modifier,
    navController: NavController,
) {
    val navigateSignUpCreateEmailToSignUpCreatePasswordInLoginGraph: () -> Unit = {
        navController.navigateSignUpCreateEmailToSignUpCreatePasswordInLoginGraph()
    }

    val navigateSignUpToSignInInLoginGraph: () -> Unit = {
        navController.navigateSignUpToSignInInLoginGraph()
    }


    composable(Router.LOGIN_SIGN_UP_CREATE_EMAIL_ROUTER_NAME) {
        SignUpCreateEmailRoute(
            modifier,
            navController,
            navigateSignUpCreateEmailToSignUpCreatePasswordInLoginGraph,
            navigateSignUpToSignInInLoginGraph
        )
    }

}


