package com.gradation.lift.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.badge.badge.badgeBadgeScreen
import com.gradation.lift.feature.badge.setting.badgeSettingScreen
import com.gradation.lift.navigation.Router

fun badgeGraphBuilder(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.navigation(
        route = Router.BADGE_GRAPH_NAME,
        startDestination = Router.BADGE_BADGE_ROUTER_NAME,
    ) {
        badgeBadgeScreen(navController, this)
        badgeSettingScreen(navController, this)
    }
}

