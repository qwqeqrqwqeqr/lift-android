package com.gradation.lift.feature.login.find_password

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.route.login.LoginFindPasswordRoute

fun loginFindPasswordScreen(
    navController : NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    LoginFindPasswordRoute {route ->
        navGraphBuilder.composable(route) {
            LoginFindPasswordRoute(navController)
        }
    }.loginFindPasswordScreen(route = Router.LOGIN_FIND_PASSWORD_ROUTER_NAME)
}



