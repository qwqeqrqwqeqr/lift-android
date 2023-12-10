package com.gradation.lift.createRoutine.routine.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_ROUTINE_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateRoutineToFindWorkCategoryInCreateRoutineGraph
import com.gradation.lift.navigation.navigation.navigateRoutineToRoutineSetInCreateRoutineGraph


fun routineScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {

    val navigateRoutineToFindWorkCategoryInCreateRoutineGraph: () -> Unit =
        { navController.navigateRoutineToFindWorkCategoryInCreateRoutineGraph() }

    val navigateRoutineToRoutineSetInCreateRoutineGraph: () -> Unit =
        { navController.navigateRoutineToRoutineSetInCreateRoutineGraph() }

    navGraphBuilder.composable(CREATE_ROUTINE_ROUTINE_ROUTER_NAME) {
        RoutineRoute(
            modifier,
            navController,
            navigateRoutineToFindWorkCategoryInCreateRoutineGraph,
            navigateRoutineToRoutineSetInCreateRoutineGraph
        )
    }
}
