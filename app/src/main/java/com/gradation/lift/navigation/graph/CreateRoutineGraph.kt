package com.gradation.lift.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.create_routine.find_workpart.createRoutineFindWorkpartScreen
import com.gradation.lift.create_routine.routine_detail.createRoutineRoutineDetailScreen
import com.gradation.lift.feature.create_routine.routile_set.createRoutineRoutineSetScreen
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_GRAPH_ROUTER_NAME
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME
import com.gradation.lift.navigation.route.create_routine.CreateRoutineGraph

fun createRoutineGraph(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    CreateRoutineGraph { route, graphStartDestination ->
        navGraphBuilder.navigation(
            route = route,
            startDestination = graphStartDestination,
        ) {
            createRoutineRoutineSetScreen(navController, this)
            createRoutineRoutineDetailScreen(navController, this)
            createRoutineFindWorkpartScreen(navController, this)
        }
    }.createRoutineGraph(
        route = CREATE_ROUTINE_GRAPH_ROUTER_NAME,
        startDestination = CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME,
    )
}