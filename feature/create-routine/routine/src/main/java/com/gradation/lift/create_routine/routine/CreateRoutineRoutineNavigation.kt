package com.gradation.lift.create_routine.routine

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.route.create_routine.CreateRoutineRoutineRoute
import com.gradation.lift.navigation.Router



fun createRoutineRoutineScreen(
    navController : NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    CreateRoutineRoutineRoute {route ->
        navGraphBuilder.composable(route) {
            CreateRoutineRoutineRoute(navController = navController)
        }
    }.createRoutineRoutineScreen(route = Router.CREATE_ROUTINE_ROUTINE_ROUTER_NAME)
}
