package com.gradation.lift.feature.history.analytics

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.HISTORY_ANALYTICS_ROUTER_NAME


fun historyAnalyticsScreen(navController: NavController, navGraphBuilder: NavGraphBuilder) {
    navGraphBuilder.composable(HISTORY_ANALYTICS_ROUTER_NAME) {
        HistoryRoute()
    }

}


