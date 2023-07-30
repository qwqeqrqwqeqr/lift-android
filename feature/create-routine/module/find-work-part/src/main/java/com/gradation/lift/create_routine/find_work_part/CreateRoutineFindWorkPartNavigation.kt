package com.gradation.lift.create_routine.find_work_part

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.route.create_routine.CreateRoutineFindWorkPartRoute



fun createRoutineFindWorkPartScreen(
    navController : NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    CreateRoutineFindWorkPartRoute { route ->
        navGraphBuilder.composable(route) {
            CreateRoutineFindWorkPartRoute(navController = navController)
        }
    }.createRoutineFindWorkPartScreen(route = Router.CREATE_ROUTINE_FIND_WORKPART_ROUTER_NAME)
}
