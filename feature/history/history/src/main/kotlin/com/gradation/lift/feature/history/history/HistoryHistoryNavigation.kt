package com.gradation.lift.feature.history.history

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route
import com.gradation.lift.navigation.navigation.navigateHistoryGraphToWorkGraph


fun historyHistoryScreen(navController: NavController, navGraphBuilder: NavGraphBuilder) {
    navGraphBuilder.composable(Route.HISTORY_HISTORY_ROUTER_NAME) {

        val navigateHistoryGraphToWorkGraph: () -> Unit = { navController.navigateHistoryGraphToWorkGraph() }

        HistoryHistoryRoute(
            navigateHistoryGraphToWorkGraph=navigateHistoryGraphToWorkGraph
        )

    }

}
