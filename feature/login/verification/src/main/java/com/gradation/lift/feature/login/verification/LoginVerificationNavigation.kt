package com.gradation.lift.feature.login.verification

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.route.login.LoginVerificationRoute

fun loginVerificationScreen(
    navController : NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    LoginVerificationRoute {route ->
        navGraphBuilder.composable(route) {
            LoginVerificationRoute(navController)
        }
    }.loginVerificationScreen(route = Router.LOGIN_VERIFICATION_ROUTER_NAME)
}



