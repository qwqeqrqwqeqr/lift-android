package com.gradation.lift.navigation.graph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.ready_work.change_order.readyWorkChangeOrderScreen
import com.gradation.lift.feature.ready_work.selection.readyWorkSelectionScreen
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.route.ready_work.ReadyWorkGraph

@RequiresApi(Build.VERSION_CODES.O)
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