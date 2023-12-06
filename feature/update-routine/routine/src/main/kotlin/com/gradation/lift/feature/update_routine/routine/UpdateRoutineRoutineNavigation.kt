package com.gradation.lift.feature.update_routine.routine

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.UPDATE_ROUTINE_ROUTINE_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateRoutineToFindWorkCategoryInUpdateRoutineGraph
import com.gradation.lift.navigation.navigation.navigateRoutineToRoutineSetInUpdateRoutineGraph



fun updateRoutineRoutineScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {

    val navigateRoutineToFindWorkCategoryInUpdateRoutineGraph: () -> Unit =
        { navController.navigateRoutineToFindWorkCategoryInUpdateRoutineGraph() }

    val navigateRoutineToRoutineSetInUpdateRoutineGraph: () -> Unit =
        { navController.navigateRoutineToRoutineSetInUpdateRoutineGraph() }

    navGraphBuilder.composable(UPDATE_ROUTINE_ROUTINE_ROUTER_NAME) {
        UpdateRoutineRoutineRoute(
            navController = navController,
            navigateRoutineToFindWorkCategoryInUpdateRoutineGraph = navigateRoutineToFindWorkCategoryInUpdateRoutineGraph,
            navigateRoutineToRoutineSetInUpdateRoutineGraph = navigateRoutineToRoutineSetInUpdateRoutineGraph

        )
    }
}
