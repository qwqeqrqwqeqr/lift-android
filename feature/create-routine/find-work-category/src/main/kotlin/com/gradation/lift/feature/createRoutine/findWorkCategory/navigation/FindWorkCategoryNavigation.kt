package com.gradation.lift.feature.createRoutine.findWorkCategory.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavController
import com.gradation.lift.navigation.Route.CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateFindWorkCategoryToCreateWorkSetInCreateRoutineGraph
import com.gradation.lift.navigation.navigation.navigateFindWorkCategoryToRoutineSetInCreateRoutineGraph


fun findWorkCategoryScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    val navigateFindWorkCategoryToRoutineSetInCreateRoutineGraph: () -> Unit =
        { navController.navigateFindWorkCategoryToRoutineSetInCreateRoutineGraph() }

    val navigateFindWorkCategoryToCreateWorkSetInCreateRoutineGraph: (Int) -> Unit =
        { navController.navigateFindWorkCategoryToCreateWorkSetInCreateRoutineGraph(it) }

    navGraphBuilder.composable(CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME) {
        FindWorkCategoryRoute(
            modifier = modifier,
            navigateFindWorkCategoryToRoutineSetInCreateRoutineGraph,
            navigateFindWorkCategoryToCreateWorkSetInCreateRoutineGraph
        )
    }

}
