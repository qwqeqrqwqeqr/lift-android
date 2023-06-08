package com.gradation.lift.feature.create_routine.routile_set

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME
import com.gradation.lift.navigation.route.create_routine.CreateRoutineRoutineSetRoute




fun createRoutineRoutineSetScreen(
    navController : NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    CreateRoutineRoutineSetRoute {route ->
        navGraphBuilder.composable(route) {
            CreateRoutineRoutineSetRoute(navController = navController)
        }
    }.createRoutineRoutineSetScreen(route = CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME)
}

