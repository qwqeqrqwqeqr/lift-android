package com.gradation.lift.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.ready_work.change_order.readyWorkChangeOrderScreen
import com.gradation.lift.feature.ready_work.selection.readyWorkSelectionScreen
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.route.ready_work.ReadyWorkGraph
import com.gradation.lift.navigation.route.work.WorkGraph

fun workGraph(
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