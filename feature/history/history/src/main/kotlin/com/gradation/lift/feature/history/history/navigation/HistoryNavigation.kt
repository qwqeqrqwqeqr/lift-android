package com.gradation.lift.feature.history.history.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route.HISTORY_HISTORY_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateHistoryGraphToWorkReadyReadyRouter
import com.gradation.lift.navigation.navigation.navigateHistoryGraphToWorkReadyRoutineSelectionRouter
import com.gradation.lift.navigation.navigation.navigateHistoryToUpdateInfoInHistoryGraph


fun NavGraphBuilder.historyScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val navigateHistoryGraphToWorkReadyReadyRouter: () -> Unit =
        { navController.navigateHistoryGraphToWorkReadyReadyRouter() }
    val navigateHistoryGraphToWorkReadyRoutineSelectionRouter: () -> Unit =
        { navController.navigateHistoryGraphToWorkReadyRoutineSelectionRouter() }
    val navigateHistoryToUpdateInfoInHistoryGraph: (String, Int, Int, String) -> Unit =
        { comment, score, historyId, date ->
            navController.navigateHistoryToUpdateInfoInHistoryGraph(comment, score, historyId, date)
        }


    composable(
        route = HISTORY_HISTORY_ROUTER_NAME,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        popEnterTransition = { fadeIn() },
        popExitTransition = { fadeOut() }
    ) {
        HistoryRoute(
            modifier,
            navigateHistoryGraphToWorkReadyReadyRouter,
            navigateHistoryGraphToWorkReadyRoutineSelectionRouter,
            navigateHistoryToUpdateInfoInHistoryGraph
        )
    }
}
