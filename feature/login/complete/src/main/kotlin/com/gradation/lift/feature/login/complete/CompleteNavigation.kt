package com.gradation.lift.feature.login.complete

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateCompleteToSignInInLoginGraph

fun completeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    val navigateCompleteToSignInInLoginGraph: () -> Unit =
        { navController.navigateCompleteToSignInInLoginGraph() }

    navGraphBuilder.composable(Router.LOGIN_COMPLETE_ROUTER_NAME) {
        CompleteRoute(
            navigateCompleteToSignInInLoginGraph = navigateCompleteToSignInInLoginGraph
        )
    }

}


