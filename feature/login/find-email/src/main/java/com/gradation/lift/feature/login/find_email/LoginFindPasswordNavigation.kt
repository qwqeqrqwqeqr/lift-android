package com.gradation.lift.feature.login.find_email

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router

fun loginFindEmailScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(Router.LOGIN_FIND_EMAIL_PASSWORD_ROUTER_NAME) {
        LoginFindEmailRoute(navController)
    }
}



