package com.gradation.lift.feature.workReady.routineSelection.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route.WORK_READY_ROUTINE_SELECTION_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateRoutineSelectionToReadyInWorkReadyGraph


fun NavGraphBuilder.routineSelectionScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val popBackStack: () -> Unit =
        { navController.popBackStack() }

    val navigateRoutineSelectionToReadyInWorkReadyGraph: (String) -> Unit =
        { navController.navigateRoutineSelectionToReadyInWorkReadyGraph(it) }

    composable(WORK_READY_ROUTINE_SELECTION_ROUTER_NAME) {
        RoutineSelectionRoute(
            modifier,
            popBackStack,
            navigateRoutineSelectionToReadyInWorkReadyGraph,
        )
    }
}


