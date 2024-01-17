package com.gradation.lift.feature.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.dailyLog.dailyLog.navigation.dailyLogRouteScreen
import com.gradation.lift.navigation.Route.DAILY_LOG_DAILY_LOG_ROUTER_NAME
import com.gradation.lift.navigation.Route.DAILY_LOG_GRAPH_NAME


fun NavGraphBuilder.dailyLogGraphBuilder(
    modifier: Modifier=Modifier,
    navController: NavController,
) {
    navigation(
        route = DAILY_LOG_GRAPH_NAME,
        startDestination = DAILY_LOG_DAILY_LOG_ROUTER_NAME,
        popEnterTransition = {fadeIn()},
        popExitTransition = { fadeOut()},
        enterTransition = {fadeIn()},
        exitTransition = { fadeOut()},
    ) {
        dailyLogRouteScreen(modifier)
    }
}



