package com.gradation.lift.create_routine.routine

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_ROUTINE_ROUTER_NAME


fun createRoutineRoutineScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {

    navGraphBuilder.composable(CREATE_ROUTINE_ROUTINE_ROUTER_NAME) {
        CreateRoutineRoutineRoute(navController = navController)
    }
}
