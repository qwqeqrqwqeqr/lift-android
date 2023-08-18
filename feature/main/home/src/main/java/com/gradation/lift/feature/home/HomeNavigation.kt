package com.gradation.lift.feature.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.HOME_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateMainGraphToCreateRoutineGraph
import com.gradation.lift.navigation.navigation.navigateMainGraphToWorkGraph

@RequiresApi(Build.VERSION_CODES.O)
fun homeScreen(navController: NavController, navGraphBuilder: NavGraphBuilder) {
    val navigateMainGraphToCreateRoutineGraph: () -> Unit = { navController.navigateMainGraphToCreateRoutineGraph() }
    val navigateMainGraphToWorkGraph: () -> Unit = { navController.navigateMainGraphToWorkGraph() }

    navGraphBuilder.composable(HOME_ROUTER_NAME) {
        HomeRoute(
            navController = navController,
            navigateMainGraphToCreateRoutineGraph = navigateMainGraphToCreateRoutineGraph,
            navigateMainGraphToWorkGraph = navigateMainGraphToWorkGraph
        )
    }

}






