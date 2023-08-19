package com.gradation.lift.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.history.analytics.historyAnalyticsScreen
import com.gradation.lift.navigation.Router.HISTORY_ANALYTICS_ROUTER_NAME
import com.gradation.lift.navigation.Router.HISTORY_GRAPH_NAME

fun historyGraphBuilder(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.navigation(
        route = HISTORY_GRAPH_NAME,
        startDestination = HISTORY_ANALYTICS_ROUTER_NAME,
    ) {
        historyAnalyticsScreen(navController, this)
    }
}
