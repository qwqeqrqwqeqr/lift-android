package com.gradation.lift.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.route.work.WorkGraph

fun workGraphBuilder(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    WorkGraph { route, graphStartDestination ->
        navGraphBuilder.navigation(
            route = route,
            startDestination = graphStartDestination,
        ) {
        }
    }.workGraph(
        route = Router.WORK_GRAPH_ROUTER_NAME,
        startDestination = Router.WORK_WORK_ROUTER_NAME,
    )
}