package com.gradation.lift.feature.login.find_password

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router

fun loginFindPasswordScreen(
    navController : NavController,
    navGraphBuilder: NavGraphBuilder,
) {

        navGraphBuilder.composable(Router.LOGIN_FIND_EMAIL_PASSWORD_ROUTER_NAME) {
            LoginFindPasswordRoute(navController)
        }
}



