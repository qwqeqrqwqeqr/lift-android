package com.gradation.lift.feature.badge.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.badge.new_badge.newBadgeScreen
import com.gradation.lift.navigation.Router

fun newBadgeGraphBuilder(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.navigation(
        route = Router.NEW_BADGE_GRAPH_NAME,
        startDestination = Router.NEW_BADGE_NEW_BADGE_ROUTER_NAME,
    ) {
        newBadgeScreen(navController, this)
    }
}
