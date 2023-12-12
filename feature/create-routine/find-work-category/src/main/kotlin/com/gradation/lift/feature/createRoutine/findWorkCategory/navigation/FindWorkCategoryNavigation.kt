package com.gradation.lift.feature.createRoutine.findWorkCategory.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavController
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateFindWorkCategoryToRoutineInCreateRoutineGraph
import com.gradation.lift.navigation.navigation.navigateFindWorkCategoryToRoutineSetInCreateRoutineGraph


fun findWorkCategoryScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    val navigateFindWorkCategoryToRoutineSetInCreateRoutineGraph: () -> Unit =
        { navController.navigateFindWorkCategoryToRoutineSetInCreateRoutineGraph() }

    val navigateFindWorkCategoryToRoutineInCreateRoutineGraph: (Int) -> Unit =
        { navController.navigateFindWorkCategoryToRoutineInCreateRoutineGraph(it) }

    navGraphBuilder.composable(CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME) {
        FindWorkCategoryRoute(
            modifier = modifier,
            navigateFindWorkCategoryToRoutineSetInCreateRoutineGraph,
            navigateFindWorkCategoryToRoutineInCreateRoutineGraph
        )
    }

}
