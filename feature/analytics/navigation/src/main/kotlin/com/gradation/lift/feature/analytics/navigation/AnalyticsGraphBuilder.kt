package com.gradation.lift.feature.analytics.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.analytics.analytics.navigation.analyticsScreen
import com.gradation.lift.navigation.Route.ANALYTICS_ANALYTICS_ROUTER_NAME
import com.gradation.lift.navigation.Route.ANALYTICS_GRAPH_NAME


fun NavGraphBuilder.analyticsGraphBuilder(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    navigation(
        route = ANALYTICS_GRAPH_NAME,
        startDestination = ANALYTICS_ANALYTICS_ROUTER_NAME,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        popEnterTransition = { fadeIn() },
        popExitTransition = { fadeOut() }
    ) {
        analyticsScreen(modifier, navController)
    }
}
