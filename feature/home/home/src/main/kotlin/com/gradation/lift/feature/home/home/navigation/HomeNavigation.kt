package com.gradation.lift.feature.home.home.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route.HOME_HOME_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateHomeGraphToBadgeGraph
import com.gradation.lift.navigation.navigation.navigateHomeGraphToBadgeSettingRouter
import com.gradation.lift.navigation.navigation.navigateHomeGraphToCreateRoutineGraph
import com.gradation.lift.navigation.navigation.navigateHomeGraphToRoutineDetailGraph
import com.gradation.lift.navigation.navigation.navigateHomeGraphToRoutineDetailRoutineRouter
import com.gradation.lift.navigation.navigation.navigateHomeGraphToWorkGraph
import com.gradation.lift.navigation.navigation.navigateHomeToBadgeInHomeGraph


fun NavGraphBuilder.homeScreen(modifier:Modifier=Modifier,navController: NavController) {


    val navigateMainGraphToCreateRoutineGraph: () -> Unit =
        { navController.navigateHomeGraphToCreateRoutineGraph() }
    val navigateMainGraphToWorkGraph: () -> Unit = { navController.navigateHomeGraphToWorkGraph() }
    val navigateHomeGraphToBadgeGraph: () -> Unit =
        { navController.navigateHomeGraphToBadgeGraph() }
    val navigateHomeToBadgeInHomeGraph: () -> Unit =
        { navController.navigateHomeToBadgeInHomeGraph() }
    val navigateHomeGraphToRoutineDetailGraph: () -> Unit =
        { navController.navigateHomeGraphToRoutineDetailGraph() }
    val navigateHomeGraphToRoutineDetailRoutineRouter: (Int) -> Unit =
        { navController.navigateHomeGraphToRoutineDetailRoutineRouter(it) }
    val navigateHomeGraphToBadgeSettingRouter: () -> Unit =
        { navController.navigateHomeGraphToBadgeSettingRouter() }

    composable(HOME_HOME_ROUTER_NAME) {
        HomeRoute(
            modifier=modifier,
            navController=navController,
            navigateMainGraphToCreateRoutineGraph = navigateMainGraphToCreateRoutineGraph,
            navigateMainGraphToWorkGraph = navigateMainGraphToWorkGraph,
            navigateHomeGraphToBadgeGraph = navigateHomeGraphToBadgeGraph,
            navigateHomeToBadgeInHomeGraph = navigateHomeToBadgeInHomeGraph,
            navigateHomeGraphToRoutineDetailGraph = navigateHomeGraphToRoutineDetailGraph,
            navigateHomeGraphToRoutineDetailRoutineRouter = navigateHomeGraphToRoutineDetailRoutineRouter,
            navigateHomeGraphToBadgeSettingRouter = navigateHomeGraphToBadgeSettingRouter
        )
    }

}






