package com.gradation.lift.feature.login.find_email

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.route.login.LoginFindEmailRoute

fun loginFindEmailScreen(
    navController : NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    LoginFindEmailRoute {route ->
        navGraphBuilder.composable(route) {
            LoginFindEmailRoute(navController)
        }
    }.loginFindEmailScreen(route = Router.LOGIN_FIND_EMAIL_ROUTER_NAME)
}



