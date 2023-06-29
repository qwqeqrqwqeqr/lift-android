package com.gradation.lift.feature.login.terms_of_use

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.route.login.LoginTermsOfUseRoute

fun loginTermsOfUseScreen(
    navController : NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    LoginTermsOfUseRoute {route ->
        navGraphBuilder.composable(route) {
            LoginTermsOfUseRoute(navController)
        }
    }.loginTermsOfUseScreen(route = Router.LOGIN_TERMS_OF_USE_ROUTER_NAME)
}



