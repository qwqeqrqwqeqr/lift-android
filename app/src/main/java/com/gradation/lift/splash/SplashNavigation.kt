package com.gradation.lift.splash

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.google.accompanist.systemuicontroller.SystemUiController
import com.gradation.lift.MainActivityViewModel
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.route.SplashRoute





fun splashScreen(
    mainActivityViewModel: MainActivityViewModel,
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
    systemUiController: SystemUiController,
) {
    SplashRoute { route, navigationGraphBuilder ->
        navigationGraphBuilder.composable(route) {
            SplashRoute(
                mainActivityViewModel = mainActivityViewModel,
                navController= navController,
                systemUiController = systemUiController
                )
        }
    }.splashScreen(route = Router.SPLASH_ROUTER_NAME, navGraphBuilder)
}