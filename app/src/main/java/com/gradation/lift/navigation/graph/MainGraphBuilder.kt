package com.gradation.lift.navigation.graph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.history.historyScreen
import com.gradation.lift.feature.home.homeScreen
import com.gradation.lift.feature.my_info.myInfoScreen
import com.gradation.lift.navigation.Router.HOME_ROUTER_NAME
import com.gradation.lift.navigation.Router.MAIN_GRAPH_ROUTER_NAME

@RequiresApi(Build.VERSION_CODES.O)
fun mainGraphBuilder(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.navigation(
        route = MAIN_GRAPH_ROUTER_NAME,
        startDestination = HOME_ROUTER_NAME,
    ) {
        homeScreen(navController, this)
        historyScreen(navController, this)
        myInfoScreen(navController, this)
    }
}
