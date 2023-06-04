package com.gradation.lift.navigation.routor

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.gradation.lift.feature.history.HistoryRoute

const val HISTORY_ROUTER_NAME = "history"



fun NavController.navigateToHistory(navOptions: NavOptions? = null) {
    this.navigate(HISTORY_ROUTER_NAME, navOptions)
}


fun NavGraphBuilder.historyScreen() {
    composable(route = HISTORY_ROUTER_NAME){
        HistoryRoute()
    }
}