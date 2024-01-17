package com.gradation.lift.feature.login.resetPassword.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gradation.lift.navigation.Route
import com.gradation.lift.navigation.navigation.navigateResetPasswordToSignInDefaultInLoginGraph
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.Login.RESET_PASSWORD_EMAIL_KEY

fun NavGraphBuilder.resetPasswordScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {

    val navigateResetPasswordToSignInDefaultInLoginGraph: () -> Unit =
        { navController.navigateResetPasswordToSignInDefaultInLoginGraph() }



    composable(
        "${Route.LOGIN_RESET_PASSWORD_ROUTER_NAME}/{${RESET_PASSWORD_EMAIL_KEY}}",
        arguments = listOf(
            navArgument(RESET_PASSWORD_EMAIL_KEY) { type = NavType.StringType },
        )
    ) { navBackstackEntry ->

        val email: String = navBackstackEntry.arguments?.getString(RESET_PASSWORD_EMAIL_KEY) ?: ""

        ResetPasswordRoute(
            modifier,
            email,
            navigateResetPasswordToSignInDefaultInLoginGraph
        )
    }

}


