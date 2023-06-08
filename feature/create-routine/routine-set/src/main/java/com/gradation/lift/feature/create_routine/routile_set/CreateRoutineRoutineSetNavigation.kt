package com.gradation.lift.feature.create_routine.routile_set

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_GRAPH_ROUTER_NAME
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME
import com.gradation.lift.navigation.route.create_routine.CreateRoutineGraph

fun createRoutineGraph(
    navGraphBuilder: NavGraphBuilder,
    nestedGraphs: NavGraphBuilder.() -> Unit,
) {
    CreateRoutineGraph {
            route,
            graphStartDestination,
            navigationGraphBuilder,
            navigationNestedGraphs->
        navGraphBuilder.navigation(
            route = route,
            startDestination = graphStartDestination,
        ) {
            navigationGraphBuilder.composable(route = route) {
                CreateRoutineRoutineSetRoute()
            }
            navigationGraphBuilder.navigationNestedGraphs()
        }

    }.createRoutineGraph(
        route = CREATE_ROUTINE_GRAPH_ROUTER_NAME,
        startDestination = CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME,
        navGraphBuilder = navGraphBuilder,
        nestedGraphs = nestedGraphs
    )
}
