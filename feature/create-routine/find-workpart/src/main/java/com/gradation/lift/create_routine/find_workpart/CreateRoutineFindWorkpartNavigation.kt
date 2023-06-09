package com.gradation.lift.create_routine.find_workpart

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.route.create_routine.CreateRoutineFindWorkpartRoute



fun createRoutineFindWorkpartScreen(
    navController : NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    CreateRoutineFindWorkpartRoute { route ->
        navGraphBuilder.composable(route) {
            CreateRoutineFindWorkpartRoute(navController = navController)
        }
    }.createRoutineFindWorkpartScreen(route = Router.CREATE_ROUTINE_FIND_WORKPART_ROUTER_NAME)
}
