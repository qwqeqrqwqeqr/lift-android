package com.gradation.lift.feature.analytics.analytics.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route.ANALYTICS_ANALYTICS_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateAnalyticsGraphToWorkReadyReadyRouter
import com.gradation.lift.navigation.navigation.navigateAnalyticsGraphToWorkReadyRoutineSelectionRouter


fun NavGraphBuilder.analyticsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val navigateAnalyticsGraphToWorkReadyReadyRouter: () -> Unit =
        { navController.navigateAnalyticsGraphToWorkReadyReadyRouter() }
    val navigateAnalyticsGraphToWorkReadyRoutineSelectionRouter: () -> Unit =
        { navController.navigateAnalyticsGraphToWorkReadyRoutineSelectionRouter() }

    composable(
        route = ANALYTICS_ANALYTICS_ROUTER_NAME,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        popEnterTransition = { fadeIn() },
        popExitTransition = { fadeOut() }
    ) {

        AnalyticsRoute(
            modifier,
            navController,
            navigateAnalyticsGraphToWorkReadyReadyRouter,
            navigateAnalyticsGraphToWorkReadyRoutineSelectionRouter
        )
    }
}
