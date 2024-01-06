package com.gradation.lift.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.home.homeScreen
import com.gradation.lift.navigation.Route.HOME_GRAPH_NAME
import com.gradation.lift.navigation.Route.HOME_HOME_ROUTER_NAME


fun homeGraphBuilder(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.navigation(
        route = HOME_GRAPH_NAME,
        startDestination = HOME_HOME_ROUTER_NAME,
    ) {
        homeScreen(navController, this)
    }
}
