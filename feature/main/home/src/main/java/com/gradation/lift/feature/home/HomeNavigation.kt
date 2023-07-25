package com.gradation.lift.feature.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.HOME_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateHomeToCreateRoutineGraph
import com.gradation.lift.navigation.navigation.navigateHomeToReadyWorkGraph
import com.gradation.lift.navigation.route.main.HomeRoute

@RequiresApi(Build.VERSION_CODES.O)
fun homeScreen(navController: NavController, navGraphBuilder: NavGraphBuilder) {
    HomeRoute { route, navigationGraphBuilder ->
        val navigateHomeToCreateRoutineGraph = { navController.navigateHomeToCreateRoutineGraph() }
        val navigateHomeToReadyWorkGraph = {navController.navigateHomeToReadyWorkGraph()}

        navigationGraphBuilder.composable(route) {
            HomeRoute(navController = navController,
                navigateHomeToCreateRoutineGraph=navigateHomeToCreateRoutineGraph,
                navigateHomeToReadyWorkGraph=navigateHomeToReadyWorkGraph
            )
        }
    }.homeScreen(route = HOME_ROUTER_NAME, navGraphBuilder)
}






