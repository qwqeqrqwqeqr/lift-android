package com.gradation.lift.feature.login.termsOfUse.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateLoginGraphToRegisterDetailGraph
import com.gradation.lift.navigation.navigation.navigateTermsOfUseToSignInInLoginGraph
import com.gradation.lift.navigation.navigation.navigateTermsOfUseToTermsOfUseDetailInLoginGraph

fun NavGraphBuilder.termsOfUseScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val navigateTermsOfUseToTermsOfUseDetailInLoginGraph: () -> Unit =
        { navController.navigateTermsOfUseToTermsOfUseDetailInLoginGraph() }

    val navigateLoginGraphToRegisterDetailGraph: () -> Unit =
        { navController.navigateLoginGraphToRegisterDetailGraph() }

    val navigateTermsOfUseToSignInInLoginGraph: () -> Unit =
        { navController.navigateTermsOfUseToSignInInLoginGraph() }

    composable(Router.LOGIN_TERMS_OF_USE_ROUTER_NAME) {
        TermsOfUseRoute(
            modifier,
            navController,
            navigateTermsOfUseToTermsOfUseDetailInLoginGraph,
            navigateLoginGraphToRegisterDetailGraph,
            navigateTermsOfUseToSignInInLoginGraph
        )
    }

}


