package com.gradation.lift.feature.badge.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.badge.badge.navigation.badgeScreen
import com.gradation.lift.navigation.Route.BADGE_BADGE_ROUTER_NAME
import com.gradation.lift.navigation.Route.BADGE_GRAPH_NAME

fun NavGraphBuilder.badgeGraphBuilder(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    navigation(
        route = BADGE_GRAPH_NAME,
        startDestination = BADGE_BADGE_ROUTER_NAME,
        popEnterTransition = { fadeIn() },
        popExitTransition = { fadeOut() },
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
    ) {
        badgeScreen(modifier, navController, this)
    }
}

