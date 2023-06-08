package com.gradation.lift.feature.history

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.HISTORY_ROUTER_NAME


fun NavGraphBuilder.historyScreen() {
    composable(route = HISTORY_ROUTER_NAME){
        HistoryRoute()
    }
}