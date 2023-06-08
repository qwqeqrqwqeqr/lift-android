package com.gradation.lift.feature.history

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.route.main.HistoryRoute


fun historyScreen(navGraphBuilder: NavGraphBuilder) {
    HistoryRoute {router, navigationGraphBuilder ->
        navigationGraphBuilder.composable(router) {
            HistoryRoute()
        }
    }.historyScreen(router = Router.HISTORY_ROUTER_NAME, navGraphBuilder)
}


