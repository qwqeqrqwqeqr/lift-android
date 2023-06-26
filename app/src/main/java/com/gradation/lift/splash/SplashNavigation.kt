package com.gradation.lift.splash

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.route.SplashRoute





fun splashScreen(navController: NavController,navGraphBuilder: NavGraphBuilder) {
    SplashRoute { route, navigationGraphBuilder ->
        navigationGraphBuilder.composable(route) {
            SplashRoute()
        }
    }.splashScreen(route = Router.SPLASH_ROUTER_NAME, navGraphBuilder)
}