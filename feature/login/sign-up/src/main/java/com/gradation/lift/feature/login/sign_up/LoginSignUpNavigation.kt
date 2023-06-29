package com.gradation.lift.feature.login.sign_up

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.route.login.LoginSignUpRoute

fun loginSignUpScreen(
    navController : NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    LoginSignUpRoute {route ->
        navGraphBuilder.composable(route) {
            LoginSignUpRoute(navController)
        }
    }.loginSignUpScreen(route = Router.LOGIN_SIGN_UP_ROUTER_NAME)
}



