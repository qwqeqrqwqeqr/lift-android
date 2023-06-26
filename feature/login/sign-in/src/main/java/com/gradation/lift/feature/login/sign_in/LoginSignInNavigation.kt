package com.gradation.lift.feature.login.sign_in

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.route.login.LoginSignInRoute

fun loginSignInScreen(
    navController : NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    LoginSignInRoute {route ->
        navGraphBuilder.composable(route) {
            LoginSignInRoute(navController)
        }
    }.loginSignInScreen(route = Router.LOGIN_SIGN_IN_ROUTER_NAME)
}
