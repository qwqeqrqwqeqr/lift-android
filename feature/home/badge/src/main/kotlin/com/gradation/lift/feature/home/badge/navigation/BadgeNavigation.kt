package com.gradation.lift.feature.home.badge.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route
import com.gradation.lift.navigation.navigation.navigateBadgeToHomeInHomeGraph

fun NavGraphBuilder.newBadgeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val navigateBadgeToHomeInHomeGraph: () -> Unit =
        { navController.navigateBadgeToHomeInHomeGraph() }

    composable(Route.HOME_BADGE_ROUTER_NAME) {

        BadgeRoute(
            modifier,
            navController,
            navigateBadgeToHomeInHomeGraph,
        )
    }

}
