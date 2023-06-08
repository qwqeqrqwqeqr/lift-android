package com.gradation.lift.feature.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.HOME_ROUTER_NAME
import com.gradation.lift.navigation.route.HomeRoute




fun homeScreen(navGraphBuilder: NavGraphBuilder) {
    HomeRoute { router, navigationGraphBuilder ->
        navigationGraphBuilder.composable(router) {
            HomeRoute()
        }
    }.homeScreen(router = HOME_ROUTER_NAME, navGraphBuilder)
}






