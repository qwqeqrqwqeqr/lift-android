package com.gradation.lift.feature.create_routine.find_work_category

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME
import androidx.navigation.NavController
import com.gradation.lift.navigation.navigation.navigateFindWorkCategoryToRoutine
import com.gradation.lift.navigation.navigation.navigateFindWorkCategoryToRoutineSet


fun createRoutineFindWorkCategoryScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    val navigateFindWorkCategoryToRoutineSet: () -> Unit =
        { navController.navigateFindWorkCategoryToRoutineSet() }

    val navigateFindWorkCategoryToRoutine: () -> Unit =
        { navController.navigateFindWorkCategoryToRoutine() }

    navGraphBuilder.composable(CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME) {
        CreateRoutineFindWorkCategoryRoute(
            navController = navController,
            navigateFindWorkCategoryToRoutineSet=navigateFindWorkCategoryToRoutineSet,
            navigateFindWorkCategoryToRoutine=navigateFindWorkCategoryToRoutine
        )
    }

}
