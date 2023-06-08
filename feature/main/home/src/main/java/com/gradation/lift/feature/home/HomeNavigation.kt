package com.gradation.lift.feature.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.HOME_ROUTER_NAME
import com.gradation.lift.navigation.route.main.HomeRoute
fun homeScreen(navGraphBuilder: NavGraphBuilder) {
    HomeRoute { route, navigationGraphBuilder ->
        navigationGraphBuilder.composable(route) {
            HomeRoute()
        }
    }.homeScreen(route = HOME_ROUTER_NAME, navGraphBuilder)
}






