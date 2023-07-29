package com.gradation.lift.create_routine.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_PROFILE_ROUTER_NAME
import com.gradation.lift.navigation.route.create_routine.CreateRoutineProfileRoute

fun createRoutineProfileScreen(
    navController : NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    CreateRoutineProfileRoute {route ->
        navGraphBuilder.composable(route) {
            CreateRoutineProfileRoute(
                navController = navController
            )
        }
    }.createRoutineProfileScreen(route = CREATE_ROUTINE_PROFILE_ROUTER_NAME)
}


