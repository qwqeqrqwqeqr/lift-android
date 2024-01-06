package com.gradation.lift.feature.login.verifyEmail.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route
import com.gradation.lift.navigation.navigation.navigateVerifyEmailToResetPasswordInLoginGraph
import com.gradation.lift.navigation.navigation.navigateVerifyEmailToSignInDefaultInLoginGraph

fun NavGraphBuilder.verifyEmailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {

    val navigateVerifyEmailToResetPasswordInLoginGraph: (String) -> Unit =
        { navController.navigateVerifyEmailToResetPasswordInLoginGraph(it) }
    val navigateVerifyEmailToSignInDefaultInLoginGraph: () -> Unit =
        { navController.navigateVerifyEmailToSignInDefaultInLoginGraph() }


    composable(Route.LOGIN_VERIFY_EMAIL_ROUTER_NAME) {
        VerifyEmailRoute(
            modifier,
            navigateVerifyEmailToResetPasswordInLoginGraph,
            navigateVerifyEmailToSignInDefaultInLoginGraph
        )
    }

}


