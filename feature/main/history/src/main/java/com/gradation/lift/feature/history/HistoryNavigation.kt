package com.gradation.lift.feature.history

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.HISTORY_ROUTER_NAME


fun historyScreen(navController: NavController, navGraphBuilder: NavGraphBuilder) {
    navGraphBuilder.composable(HISTORY_ROUTER_NAME) {
        HistoryRoute()
    }

}


