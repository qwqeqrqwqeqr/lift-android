package com.gradation.lift.feature.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.HOME_HOME_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateHomeGraphToCreateRoutineGraph
import com.gradation.lift.navigation.navigation.navigateHomeGraphToUpdateRoutineGraph
import com.gradation.lift.navigation.navigation.navigateHomeGraphToWorkGraph


fun homeScreen(navController: NavController, navGraphBuilder: NavGraphBuilder) {
    val navigateMainGraphToCreateRoutineGraph: () -> Unit = { navController.navigateHomeGraphToCreateRoutineGraph() }
    val navigateHomeGraphToUpdateRoutineGraph: () -> Unit = { navController.navigateHomeGraphToUpdateRoutineGraph() }
    val navigateMainGraphToWorkGraph: () -> Unit = { navController.navigateHomeGraphToWorkGraph() }

    navGraphBuilder.composable(HOME_HOME_ROUTER_NAME) {
        HomeRoute(
            navController = navController,
            navigateMainGraphToCreateRoutineGraph = navigateMainGraphToCreateRoutineGraph,
            navigateHomeGraphToUpdateRoutineGraph=navigateHomeGraphToUpdateRoutineGraph,
            navigateMainGraphToWorkGraph = navigateMainGraphToWorkGraph
        )
    }

}






