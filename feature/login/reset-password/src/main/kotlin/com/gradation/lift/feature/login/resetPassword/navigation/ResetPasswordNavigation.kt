package com.gradation.lift.feature.login.resetPassword.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route

fun NavGraphBuilder.resetPasswordScreen(
    modifier: Modifier=Modifier,
    navController: NavController,
) {


    composable(Route.LOGIN_RESET_PASSWORD_ROUTER_NAME) {
        ResetPasswordRoute()
    }

}


