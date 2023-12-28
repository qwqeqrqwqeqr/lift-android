package com.gradation.lift.feature.login.termsOfUse.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateTermsOfUseToCompleteInLoginGraph
import com.gradation.lift.navigation.navigation.navigateTermsOfUseToSignInInLoginGraph
import com.gradation.lift.navigation.navigation.navigateTermsOfUseToTermsOfUseDetailInLoginGraph

fun termsOfUseScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    val navigateTermsOfUseToTermsOfUseDetailInLoginGraph: () -> Unit =
        { navController.navigateTermsOfUseToTermsOfUseDetailInLoginGraph() }

    val navigateTermsOfUseToCompleteInLoginGraph: () -> Unit =
        { navController.navigateTermsOfUseToCompleteInLoginGraph() }

    val navigateTermsOfUseToSignInInLoginGraph: () -> Unit =
        { navController.navigateTermsOfUseToSignInInLoginGraph() }

    navGraphBuilder.composable(Router.LOGIN_TERMS_OF_USE_ROUTER_NAME) {
        TermsOfUseRoute(
            modifier,
            navController,
            navigateTermsOfUseToTermsOfUseDetailInLoginGraph,
            navigateTermsOfUseToCompleteInLoginGraph,
            navigateTermsOfUseToSignInInLoginGraph
        )
    }

}


