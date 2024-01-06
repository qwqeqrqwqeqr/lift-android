package com.gradation.lift.feature.work.routineSelection.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route.WORK_ROUTINE_SELECTION_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateRoutineSelectionToWorkInWorkGraph
import com.gradation.lift.navigation.navigation.navigateWorkGraphToHomeGraph


fun routineSelectionScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    val navigateWorkGraphToHomeGraph: () -> Unit =
        { navController.navigateWorkGraphToHomeGraph() }

    val navigateRoutineSelectionToWorkInWorkGraph : (IntArray) -> Unit =
        {navController.navigateRoutineSelectionToWorkInWorkGraph(it)}




    navGraphBuilder.composable(WORK_ROUTINE_SELECTION_ROUTER_NAME) {
        RoutineSelectionRoute(
            modifier,
            navigateWorkGraphToHomeGraph,
            navigateRoutineSelectionToWorkInWorkGraph,
        )
    }
}


