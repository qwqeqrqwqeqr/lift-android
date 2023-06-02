package com.gradation.lift.feature.create_routine.routile_list

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation


const val CREATE_ROUTINE_GRAPH_ROUTER_NAME = "create_routine_graph"
const val CREATE_ROUTINE_ROUTINE_LIST_ROUTER_NAME = "create_routine_routine_list"



fun NavController.navigateToCreateRoutineGraph(navOptions: NavOptions? = null) {
    this.navigate(CREATE_ROUTINE_GRAPH_ROUTER_NAME, navOptions)
}



fun NavGraphBuilder.createRoutineGraph(
    nestedGraphs: NavGraphBuilder.() -> Unit,
) {
    navigation(
        route = CREATE_ROUTINE_GRAPH_ROUTER_NAME,
        startDestination = CREATE_ROUTINE_ROUTINE_LIST_ROUTER_NAME,
    ) {
        composable(route = CREATE_ROUTINE_ROUTINE_LIST_ROUTER_NAME) {
            CreateRoutineRoutineListRoute()
        }
        nestedGraphs()
    }
}
