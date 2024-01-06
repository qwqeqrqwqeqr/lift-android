package com.gradation.lift.feature.badge.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.badge.new_badge.newBadgeScreen
import com.gradation.lift.navigation.Route

fun newBadgeGraphBuilder(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.navigation(
        route = Route.NEW_BADGE_GRAPH_NAME,
        startDestination = Route.NEW_BADGE_NEW_BADGE_ROUTER_NAME,
    ) {
        newBadgeScreen(navController, this)
    }
}
