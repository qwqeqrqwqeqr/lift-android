package com.gradation.lift.feature.updateRoutine.routine.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route.UPDATE_ROUTINE_ROUTINE_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateRoutineToFindWorkCategoryInUpdateRoutineGraph
import com.gradation.lift.navigation.navigation.navigateRoutineToRoutineSetInUpdateRoutineGraph


fun routineScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {

    val navigateRoutineToFindWorkCategoryInUpdateRoutineGraph: () -> Unit =
        { navController.navigateRoutineToFindWorkCategoryInUpdateRoutineGraph() }

    val navigateRoutineToRoutineSetInUpdateRoutineGraph: () -> Unit =
        { navController.navigateRoutineToRoutineSetInUpdateRoutineGraph() }

    navGraphBuilder.composable(UPDATE_ROUTINE_ROUTINE_ROUTER_NAME) {
        RoutineRoute(
            modifier,
            navController,
            navigateRoutineToFindWorkCategoryInUpdateRoutineGraph,
            navigateRoutineToRoutineSetInUpdateRoutineGraph
        )
    }
}
