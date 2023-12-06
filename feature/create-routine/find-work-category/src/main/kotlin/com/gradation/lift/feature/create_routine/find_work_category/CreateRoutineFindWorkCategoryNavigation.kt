package com.gradation.lift.feature.create_routine.find_work_category

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME
import androidx.navigation.NavController
import com.gradation.lift.navigation.navigation.navigateFindWorkCategoryToRoutineInCreateRoutineGraph
import com.gradation.lift.navigation.navigation.navigateFindWorkCategoryToRoutineSetInCreateRoutineGraph


fun createRoutineFindWorkCategoryScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    val navigateFindWorkCategoryToRoutineSetInCreateRoutineGraph: () -> Unit =
        { navController.navigateFindWorkCategoryToRoutineSetInCreateRoutineGraph() }

    val navigateFindWorkCategoryToRoutineInCreateRoutineGraph: () -> Unit =
        { navController.navigateFindWorkCategoryToRoutineInCreateRoutineGraph() }

    navGraphBuilder.composable(CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME) {
        CreateRoutineFindWorkCategoryRoute(
            navController = navController,
            navigateFindWorkCategoryToRoutineSetInCreateRoutineGraph=navigateFindWorkCategoryToRoutineSetInCreateRoutineGraph,
            navigateFindWorkCategoryToRoutineInCreateRoutineGraph=navigateFindWorkCategoryToRoutineInCreateRoutineGraph
        )
    }

}
