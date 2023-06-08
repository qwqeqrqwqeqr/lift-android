package com.gradation.lift.create_routine.routine_detail

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_ROUTINE_DETAIL_ROUTER_NAME
import com.gradation.lift.navigation.route.create_routine.CreateRoutineRoutineDetailRoute




fun createRoutineRoutineDetailScreen(navGraphBuilder: NavGraphBuilder) {
    CreateRoutineRoutineDetailRoute {route, navigationGraphBuilder ->
        navigationGraphBuilder.composable(route) {
            CreateRoutineRoutineDetailRoute()
        }
    }.createRoutineRoutineDetailScreen(route = CREATE_ROUTINE_ROUTINE_DETAIL_ROUTER_NAME, navGraphBuilder)
}


