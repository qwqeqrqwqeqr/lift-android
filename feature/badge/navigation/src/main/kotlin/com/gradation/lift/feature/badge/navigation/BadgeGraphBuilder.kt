package com.gradation.lift.feature.badge.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.badge.badge.badgeBadgeScreen
import com.gradation.lift.feature.badge.setting.badgeSettingScreen
import com.gradation.lift.navigation.Route.BADGE_BADGE_ROUTER_NAME
import com.gradation.lift.navigation.Route.BADGE_GRAPH_NAME

fun NavGraphBuilder.badgeGraphBuilder(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    navigation(
        route = BADGE_GRAPH_NAME,
        startDestination = BADGE_BADGE_ROUTER_NAME,
        popEnterTransition = null,
        popExitTransition = null,
        enterTransition = null,
        exitTransition = null,
    ) {
        badgeBadgeScreen(navController, this)
        badgeSettingScreen(navController, this)
    }
}

