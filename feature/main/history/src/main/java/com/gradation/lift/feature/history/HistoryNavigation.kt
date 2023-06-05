package com.gradation.lift.feature.history

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.gradation.lift.feature.history.HistoryRoute
import com.gradation.lift.navigation.routor.HISTORY_ROUTER_NAME


fun NavGraphBuilder.historyScreen() {
    composable(route = HISTORY_ROUTER_NAME){
        HistoryRoute()
    }
}