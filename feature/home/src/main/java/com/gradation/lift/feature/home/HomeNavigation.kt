package com.gradation.lift.feature.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.HOME_HOME_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateHomeGraphToCreateRoutineGraph
import com.gradation.lift.navigation.navigation.navigateHomeGraphToWorkGraph

@RequiresApi(Build.VERSION_CODES.O)
fun homeScreen(navController: NavController, navGraphBuilder: NavGraphBuilder) {
    val navigateMainGraphToCreateRoutineGraph: () -> Unit = { navController.navigateHomeGraphToCreateRoutineGraph() }
    val navigateMainGraphToWorkGraph: () -> Unit = { navController.navigateHomeGraphToWorkGraph() }

    navGraphBuilder.composable(HOME_HOME_ROUTER_NAME) {
        HomeRoute(
            navController = navController,
            navigateMainGraphToCreateRoutineGraph = navigateMainGraphToCreateRoutineGraph,
            navigateMainGraphToWorkGraph = navigateMainGraphToWorkGraph
        )
    }

}





