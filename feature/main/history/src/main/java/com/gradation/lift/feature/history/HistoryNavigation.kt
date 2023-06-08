package com.gradation.lift.feature.history

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.HISTORY_ROUTER_NAME
import com.gradation.lift.navigation.route.main.HistoryRoute


fun historyScreen(navGraphBuilder: NavGraphBuilder) {
    HistoryRoute {route, navigationGraphBuilder ->
        navigationGraphBuilder.composable(route) {
            HistoryRoute()
        }
    }.historyScreen(route = HISTORY_ROUTER_NAME, navGraphBuilder)
}


