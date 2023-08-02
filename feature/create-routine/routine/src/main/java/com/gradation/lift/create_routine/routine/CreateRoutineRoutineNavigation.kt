package com.gradation.lift.create_routine.routine

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_ROUTINE_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateCreateRoutineRoutineToFindWorkCategory
import com.gradation.lift.navigation.navigation.navigateCreateRoutineRoutineToRoot


fun createRoutineRoutineScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {

    val navigateCreateRoutineRoutineToFindWorkCategory =
        { navController.navigateCreateRoutineRoutineToFindWorkCategory() }

    val navigateCreateRoutineRoutineToRoot =
        { navController.navigateCreateRoutineRoutineToRoot() }

    navGraphBuilder.composable(CREATE_ROUTINE_ROUTINE_ROUTER_NAME) {
        CreateRoutineRoutineRoute(
            navController = navController,
            navigateCreateRoutineRoutineToFindWorkCategory = navigateCreateRoutineRoutineToFindWorkCategory,
            navigateCreateRoutineRoutineToRoot = navigateCreateRoutineRoutineToRoot

        )
    }
}
