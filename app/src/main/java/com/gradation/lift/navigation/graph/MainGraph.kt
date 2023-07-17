package com.gradation.lift.navigation.graph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.history.historyScreen
import com.gradation.lift.feature.home.homeScreen
import com.gradation.lift.feature.my_info.myInfoScreen
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.route.main.MainGraph

@RequiresApi(Build.VERSION_CODES.O)
fun mainGraph(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    MainGraph { route, graphStartDestination ->
        navGraphBuilder.navigation(
            route = route,
            startDestination = graphStartDestination,
        ) {
            homeScreen(navController, this)
            historyScreen(navController, this)
            myInfoScreen(navController, this)
        }
    }.mainGraph(
        route = Router.MAIN_GRAPH_ROUTER_NAME,
        startDestination = Router.HOME_ROUTER_NAME,
    )
}