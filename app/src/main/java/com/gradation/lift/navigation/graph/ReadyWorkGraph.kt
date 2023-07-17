package com.gradation.lift.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.ready_work.change_order.readyWorkChangeOrderScreen
import com.gradation.lift.feature.ready_work.selection.readyWorkSelectionScreen
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.route.ready_work.ReadyWorkGraph

fun readyWorkGraph(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    ReadyWorkGraph { route, graphStartDestination ->
        navGraphBuilder.navigation(
            route = route,
            startDestination = graphStartDestination,
        ) {
            readyWorkSelectionScreen(navController, this)
            readyWorkChangeOrderScreen(navController, this)
        }
    }.readyWorkGraph(
        route = Router.READY_WORK_GRAPH_ROUTER_NAME,
        startDestination = Router.READY_WORK_SELECTION_ROUTER_NAME,
    )
}