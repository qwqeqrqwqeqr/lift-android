package com.gradation.lift.feature.history.daily_log

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router


fun historyDailyLogScreen(navController: NavController, navGraphBuilder: NavGraphBuilder) {
    navGraphBuilder.composable(Router.HISTORY_DAILY_LOG_ROUTER_NAME) {
        HistoryDailyLogRoute()
    }

}
