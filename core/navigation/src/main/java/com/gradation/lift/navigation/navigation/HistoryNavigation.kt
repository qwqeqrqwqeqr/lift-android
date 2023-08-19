package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.gradation.lift.navigation.Router

fun NavController.navigateHistoryGraphToHomeGraph() {
    this.navigate(Router.HISTORY_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(this@navigateHistoryGraphToHomeGraph.graph.id) {
            inclusive = true
        }
    }
}


fun NavHostController.navigateAnalyticsToDailyLog() {
    this.navigate(Router.HISTORY_DAILY_LOG_ROUTER_NAME) {
        launchSingleTop = true

        popUpTo(currentDestination!!.id) {
            saveState = true
            inclusive = true
        }
    }
}

fun NavHostController.navigateDailyLogToAnalytics() {
    this.navigate(Router.HISTORY_ANALYTICS_ROUTER_NAME) {
        launchSingleTop = true

        popUpTo(currentDestination!!.id) {
            saveState = true
            inclusive = true
        }
    }
}
