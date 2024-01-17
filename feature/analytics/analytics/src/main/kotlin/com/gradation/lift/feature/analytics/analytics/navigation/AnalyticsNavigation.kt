package com.gradation.lift.feature.analytics.analytics.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route.ANALYTICS_ANALYTICS_ROUTER_NAME


fun NavGraphBuilder.analyticsScreen(
    modifier: Modifier = Modifier,
) {
    composable(
        route= ANALYTICS_ANALYTICS_ROUTER_NAME,
        enterTransition = { fadeIn()},
        exitTransition = { fadeOut() },
        popEnterTransition = { fadeIn() },
        popExitTransition = { fadeOut() }
    ) {

        AnalyticsRoute(modifier
        )
    }
}
