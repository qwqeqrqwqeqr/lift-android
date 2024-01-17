package com.gradation.lift.feature.dailyLog.dailyLog.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route.DAILY_LOG_DAILY_LOG_ROUTER_NAME


fun NavGraphBuilder.dailyLogRouteScreen(
    modifier: Modifier = Modifier,
) {
    composable(
        route= DAILY_LOG_DAILY_LOG_ROUTER_NAME,
        enterTransition = { fadeIn()},
        exitTransition = { fadeOut() },
        popEnterTransition = { fadeIn() },
        popExitTransition = { fadeOut() }
    ) {

        DailyLogRoute(modifier)
    }
}
