package com.gradation.lift.feature.badge.new_badge

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route
import com.gradation.lift.navigation.navigation.navigateNewBadgeGraphToHomeGraph

fun newBadgeScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    val navigateNewBadgeGraphToHomeGraph: () -> Unit = { navController.navigateNewBadgeGraphToHomeGraph() }

    navGraphBuilder.composable(Route.NEW_BADGE_NEW_BADGE_ROUTER_NAME) {

        NewBadgeRoute(
            navigateNewBadgeGraphToHomeGraph=navigateNewBadgeGraphToHomeGraph,
            navController=navController
        )
    }

}
