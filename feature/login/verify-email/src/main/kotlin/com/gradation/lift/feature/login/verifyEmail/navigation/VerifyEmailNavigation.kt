package com.gradation.lift.feature.login.verifyEmail.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router

fun verifyEmailScreen(
    modifier: Modifier=Modifier,
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {


    navGraphBuilder.composable(Router.LOGIN_VERIFY_EMAIL_ROUTER_NAME) {
        VerifyEmailRoute()
    }

}


