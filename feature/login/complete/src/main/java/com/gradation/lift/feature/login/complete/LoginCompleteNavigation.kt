package com.gradation.lift.feature.login.complete

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.route.login.LoginCompleteRoute

fun loginCompleteScreen(
    navController : NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    LoginCompleteRoute {route ->
        navGraphBuilder.composable(route) {
            LoginCompleteRoute(navController)
        }
    }.loginCompleteScreen(route = Router.LOGIN_COMPLETE_ROUTER_NAME)
}



