package com.gradation.lift.feature.login.complete

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateCompleteToSignIn

fun loginCompleteScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    val navigateSignUpToSignIn = { navController.navigateCompleteToSignIn() }

    navGraphBuilder.composable(Router.LOGIN_COMPLETE_ROUTER_NAME) {
        LoginCompleteRoute(
            navigateSignUpToSignIn
        )
    }
}


