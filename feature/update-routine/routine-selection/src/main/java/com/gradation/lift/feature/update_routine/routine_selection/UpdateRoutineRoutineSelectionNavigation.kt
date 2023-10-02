package com.gradation.lift.feature.update_routine.routine_selection

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateRoutineSelectionToRoutineSetInUpdateRoutineGraph
import com.gradation.lift.navigation.navigation.navigateUpdateRoutineGraphToHomeGraph


fun updateRoutineRoutineSelectionScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(Router.UPDATE_ROUTINE_ROUTINE_SELECTION_ROUTER_NAME) {

        val navigateRoutineSelectionToRoutineSetInUpdateRoutineGraph: () -> Unit =
            { navController.navigateRoutineSelectionToRoutineSetInUpdateRoutineGraph() }


        val navigateUpdateRoutineGraphToHomeGraph: () -> Unit =
            { navController.navigateUpdateRoutineGraphToHomeGraph() }

        UpdateRoutineRoutineSelectionRoute(
            navController,
            navigateRoutineSelectionToRoutineSetInUpdateRoutineGraph,
            navigateUpdateRoutineGraphToHomeGraph
        )
    }
}



