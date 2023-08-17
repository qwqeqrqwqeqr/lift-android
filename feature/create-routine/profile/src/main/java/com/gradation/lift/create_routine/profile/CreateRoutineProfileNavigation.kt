package com.gradation.lift.create_routine.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_PROFILE_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateProfileToRoutineSet

fun createRoutineProfileScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(CREATE_ROUTINE_PROFILE_ROUTER_NAME) {

        val navigateProfileToRoutineSet =
            { navController.navigateProfileToRoutineSet() }

        CreateRoutineProfileRoute(
            navController = navController,
            navigateProfileToRoutineSet = navigateProfileToRoutineSet
        )
    }
}


