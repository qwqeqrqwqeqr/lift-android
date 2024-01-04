package com.gradation.lift.feature.login.signInDefault.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateLoginGraphToHomeGraph
import com.gradation.lift.navigation.navigation.navigateLoginGraphToRegisterDetailGraph
import com.gradation.lift.navigation.navigation.navigateSignInDefaultToSignInInLoginGraph
import com.gradation.lift.navigation.navigation.navigateSignInDefaultToVerifyEmailInLoginGraph

fun NavGraphBuilder.signInDefaultScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val navigateSignInDefaultToSignInInLoginGraph: () -> Unit = {
        navController.navigateSignInDefaultToSignInInLoginGraph()
    }
    val navigateSignInDefaultToVerifyEmailInLoginGraph: () -> Unit = {
        navController.navigateSignInDefaultToVerifyEmailInLoginGraph()
    }
    val navigateLoginGraphToHomeGraph: () -> Unit = {
        navController.navigateLoginGraphToHomeGraph()
    }
    val navigateLoginGraphToRegisterDetailGraph: () -> Unit = {
        navController.navigateLoginGraphToRegisterDetailGraph()
    }

    composable(Router.LOGIN_SIGN_IN_DEFAULT_ROUTER_NAME) {
        SignInDefaultRoute(
            modifier,
            navigateSignInDefaultToSignInInLoginGraph,
            navigateSignInDefaultToVerifyEmailInLoginGraph,
            navigateLoginGraphToHomeGraph,
            navigateLoginGraphToRegisterDetailGraph
        )
    }

}


