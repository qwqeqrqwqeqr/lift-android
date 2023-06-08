package com.gradation.lift.create_routine.routine_detail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_ROUTINE_DETAIL_ROUTER_NAME
import com.gradation.lift.navigation.route.create_routine.CreateRoutineRoutineDetailRoute




fun createRoutineRoutineDetailScreen(
    navController : NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    CreateRoutineRoutineDetailRoute {route ->
        navGraphBuilder.composable(route) {
            CreateRoutineRoutineDetailRoute(navController = navController)
        }
    }.createRoutineRoutineDetailScreen(route = CREATE_ROUTINE_ROUTINE_DETAIL_ROUTER_NAME)
}


