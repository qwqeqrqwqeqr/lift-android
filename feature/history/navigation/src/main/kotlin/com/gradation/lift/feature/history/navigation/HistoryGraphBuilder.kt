package com.gradation.lift.feature.history.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.history.history.historyHistoryScreen
import com.gradation.lift.navigation.Route.HISTORY_GRAPH_NAME
import com.gradation.lift.navigation.Route.HISTORY_HISTORY_ROUTER_NAME


fun historyGraphBuilder(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.navigation(
        route = HISTORY_GRAPH_NAME,
        startDestination = HISTORY_HISTORY_ROUTER_NAME,
        popEnterTransition = null,
        popExitTransition = null,
        enterTransition = null,
        exitTransition = null,
    ) {
        historyHistoryScreen(navController, this)
    }
}
