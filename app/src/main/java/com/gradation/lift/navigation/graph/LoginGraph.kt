package com.gradation.lift.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.create_routine.find_workpart.createRoutineFindWorkpartScreen
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.route.login.LoginGraph

fun loginGraph(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    LoginGraph { route, graphStartDestination ->
        navGraphBuilder.navigation(
            route = route,
            startDestination = graphStartDestination,
        ) {
            createRoutineFindWorkpartScreen(navController, this)
        }
    }.loginGraph(
        route = Router.LOGIN_GRAPH_ROUTER_NAME,
        startDestination = Router.LOGIN_SIGN_IN_ROUTER_NAME,
    )
}