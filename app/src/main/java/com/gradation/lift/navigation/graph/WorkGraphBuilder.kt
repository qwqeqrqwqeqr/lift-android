package com.gradation.lift.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.navigation.Router.WORK_GRAPH_NAME
import com.gradation.lift.navigation.Router.WORK_WORK_ROUTER_NAME

fun workGraphBuilder(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {

    navGraphBuilder.navigation(
        route = WORK_GRAPH_NAME,
        startDestination = WORK_WORK_ROUTER_NAME,
    ) {
    }
}