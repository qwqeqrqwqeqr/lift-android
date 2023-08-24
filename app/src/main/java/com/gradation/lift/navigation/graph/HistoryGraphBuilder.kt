package com.gradation.lift.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.history.history.historyHistoryScreen
import com.gradation.lift.navigation.Router.HISTORY_GRAPH_NAME
import com.gradation.lift.navigation.Router.HISTORY_HISTORY_ROUTER_NAME

fun historyGraphBuilder(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.navigation(
        route = HISTORY_GRAPH_NAME,
        startDestination = HISTORY_HISTORY_ROUTER_NAME,
    ) {
        historyHistoryScreen(navController, this)
    }
}
