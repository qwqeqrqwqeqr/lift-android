package com.gradation.lift.feature.create_routine.find_work_category

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME
import androidx.navigation.NavController
import com.gradation.lift.navigation.navigation.navigateCreateRoutineFindWorkCategoryToRoutine
import com.gradation.lift.navigation.navigation.navigateCreateRoutineFindWorkCategoryToRoutineSet


fun createRoutineFindWorkCategoryScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {

    val navigateCreateRoutineFindWorkCategoryToRoutineSet =
        { navController.navigateCreateRoutineFindWorkCategoryToRoutineSet() }

    val navigateCreateRoutineFindWorkCategoryToRoutine =
        { navController.navigateCreateRoutineFindWorkCategoryToRoutine() }

    navGraphBuilder.composable(CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME) {
        CreateRoutineFindWorkCategoryRoute(
            navController = navController,
            navigateCreateRoutineFindWorkCategoryToRoutineSet=navigateCreateRoutineFindWorkCategoryToRoutineSet,
            navigateCreateRoutineFindWorkCategoryToRoutine=navigateCreateRoutineFindWorkCategoryToRoutine
        )
    }

}
