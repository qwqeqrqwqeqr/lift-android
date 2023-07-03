package com.gradation.lift.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.route.register_detail.RegisterDetailGraph

fun registerDetailGraph(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    RegisterDetailGraph { route, graphStartDestination ->
        navGraphBuilder.navigation(
            route = route,
            startDestination = graphStartDestination,
        ) {
        }
    }.registerDetailGraph(
        route = Router.REGISTER_GRAPH_ROUTER_NAME,
        startDestination = Router.REGISTER_DETAIL_NAME_ROUTER_NAME,
    )
}