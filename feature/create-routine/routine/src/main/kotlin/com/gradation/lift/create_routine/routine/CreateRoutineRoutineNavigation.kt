package com.gradation.lift.create_routine.routine

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_ROUTINE_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateRoutineToFindWorkCategoryInCreateRoutineGraph
import com.gradation.lift.navigation.navigation.navigateRoutineToRoutineSetInCreateRoutineGraph


fun createRoutineRoutineScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {

    val navigateRoutineToFindWorkCategoryInCreateRoutineGraph: () -> Unit =
        { navController.navigateRoutineToFindWorkCategoryInCreateRoutineGraph() }

    val navigateRoutineToRoutineSetInCreateRoutineGraph: () -> Unit =
        { navController.navigateRoutineToRoutineSetInCreateRoutineGraph() }

    navGraphBuilder.composable(CREATE_ROUTINE_ROUTINE_ROUTER_NAME) {
        CreateRoutineRoutineRoute(
            navController = navController,
            navigateRoutineToFindWorkCategoryInCreateRoutineGraph = navigateRoutineToFindWorkCategoryInCreateRoutineGraph,
            navigateRoutineToRoutineSetInCreateRoutineGraph = navigateRoutineToRoutineSetInCreateRoutineGraph

        )
    }
}
