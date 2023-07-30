package com.gradation.lift.feature.login.complete

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router

fun loginCompleteScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(Router.LOGIN_COMPLETE_ROUTER_NAME) {
        LoginCompleteRoute(navController)
    }
}


