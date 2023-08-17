package com.gradation.lift.create_routine.routine

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_ROUTINE_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateRoutineToFindWorkCategory
import com.gradation.lift.navigation.navigation.navigateRoutineToRoutineSet


fun createRoutineRoutineScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {

    val navigateRoutineToFindWorkCategory =
        { navController.navigateRoutineToFindWorkCategory() }

    val navigateRoutineToRoutineSet =
        { navController.navigateRoutineToRoutineSet() }

    navGraphBuilder.composable(CREATE_ROUTINE_ROUTINE_ROUTER_NAME) {
        CreateRoutineRoutineRoute(
            navController = navController,
            navigateRoutineToFindWorkCategory = navigateRoutineToFindWorkCategory,
            navigateRoutineToRoutineSet = navigateRoutineToRoutineSet

        )
    }
}
