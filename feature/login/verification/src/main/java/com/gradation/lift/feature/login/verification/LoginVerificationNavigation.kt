package com.gradation.lift.feature.login.verification

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router

fun loginVerificationScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(Router.LOGIN_VERIFICATION_ROUTER_NAME) {
        LoginVerificationRoute(navController)
    }
}



