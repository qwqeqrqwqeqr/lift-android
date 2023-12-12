package com.gradation.lift.feature.login.terms_of_use

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router

fun loginTermsOfUseScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(Router.LOGIN_TERMS_OF_USE_ROUTER_NAME) {
        LoginTermsOfUseRoute(navController)
    }

}



