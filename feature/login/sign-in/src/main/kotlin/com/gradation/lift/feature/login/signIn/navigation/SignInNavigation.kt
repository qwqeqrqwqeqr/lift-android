package com.gradation.lift.feature.login.signIn.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route
import com.gradation.lift.navigation.navigation.*

fun NavGraphBuilder.signInScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val navigateLoginGraphToHomeGraph: () -> Unit =
        { navController.navigateLoginGraphToHomeGraph() }
    val navigateLoginGraphToRegisterDetailGraph: () -> Unit =
        { navController.navigateLoginGraphToRegisterDetailGraph() }
    val navigateSignInToSignInDefaultInLoginGraph: () -> Unit =
        { navController.navigateSignInToSignInDefaultInLoginGraph() }
    val navigateSignInToSignUpCreateEmailDefaultInLoginGraph: () -> Unit =
        { navController.navigateSignInToSignUpCreateEmailDefaultInLoginGraph() }
    val navigateSignInToTermsOfUseInLoginGraph: () -> Unit =
        { navController.navigateSignInToTermsOfUseInLoginGraph() }




    composable(Route.LOGIN_SIGN_IN_ROUTER_NAME) {
        SignInRoute(
            modifier,
            navController,
            navigateLoginGraphToHomeGraph,
            navigateLoginGraphToRegisterDetailGraph,
            navigateSignInToSignInDefaultInLoginGraph,
            navigateSignInToSignUpCreateEmailDefaultInLoginGraph,
            navigateSignInToTermsOfUseInLoginGraph,
        )
    }

}
