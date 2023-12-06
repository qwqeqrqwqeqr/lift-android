package com.gradation.lift.feature.work.routine_selection

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.WORK_ROUTINE_SELECTION_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateSelectionRoutineToWorkInWorkGraph
import com.gradation.lift.navigation.navigation.navigateWorkGraphToHomeGraph
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import com.gradation.lift.navigation.saved_state.getValueSavedStateHandle



fun workRoutineSelectionScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(WORK_ROUTINE_SELECTION_ROUTER_NAME) {


        val navigateSelectionRoutineToWorkInWorkGraph: () -> Unit =
            { navController.navigateSelectionRoutineToWorkInWorkGraph() }
        val navigateWorkGraphToHomeGraph: () -> Unit =
            { navController.navigateWorkGraphToHomeGraph() }

        WorkRoutineSelectionRoute(
            navController = navController,
            navigateSelectionRoutineToWorkInWorkGraph = navigateSelectionRoutineToWorkInWorkGraph,
            navigateWorkGraphToHomeGraph = navigateWorkGraphToHomeGraph,
        )
    }

}
