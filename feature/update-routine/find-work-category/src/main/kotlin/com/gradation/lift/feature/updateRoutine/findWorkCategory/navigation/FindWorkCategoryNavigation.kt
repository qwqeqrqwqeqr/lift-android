package com.gradation.lift.feature.updateRoutine.findWorkCategory.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavController
import com.gradation.lift.navigation.Route.UPDATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateFindWorkCategoryToRoutineInUpdateRoutineGraph
import com.gradation.lift.navigation.navigation.navigateFindWorkCategoryToRoutineSetInUpdateRoutineGraph


fun findWorkCategoryScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    val navigateFindWorkCategoryToRoutineSetInUpdateRoutineGraph: () -> Unit =
        { navController.navigateFindWorkCategoryToRoutineSetInUpdateRoutineGraph() }

    val navigateFindWorkCategoryToRoutineInUpdateRoutineGraph: (Int) -> Unit =
        { navController.navigateFindWorkCategoryToRoutineInUpdateRoutineGraph(it) }

    navGraphBuilder.composable(UPDATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME) {
        FindWorkCategoryRoute(
            modifier = modifier,
            navigateFindWorkCategoryToRoutineSetInUpdateRoutineGraph = navigateFindWorkCategoryToRoutineSetInUpdateRoutineGraph,
            navigateFindWorkCategoryToRoutineInUpdateRoutineGraph = navigateFindWorkCategoryToRoutineInUpdateRoutineGraph
        )
    }

}
