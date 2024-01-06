package com.gradation.lift.feature.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route.HOME_HOME_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateHomeGraphToBadgeGraph
import com.gradation.lift.navigation.navigation.navigateHomeGraphToBadgeSettingRouter
import com.gradation.lift.navigation.navigation.navigateHomeGraphToCreateRoutineGraph
import com.gradation.lift.navigation.navigation.navigateHomeGraphToNewBadgeGraph
import com.gradation.lift.navigation.navigation.navigateHomeGraphToNotificationGraph
import com.gradation.lift.navigation.navigation.navigateHomeGraphToRoutineDetailGraph
import com.gradation.lift.navigation.navigation.navigateHomeGraphToRoutineDetailRoutineRouter
import com.gradation.lift.navigation.navigation.navigateHomeGraphToWorkGraph


fun homeScreen(navController: NavController, navGraphBuilder: NavGraphBuilder) {
    val navigateMainGraphToCreateRoutineGraph: () -> Unit =
        { navController.navigateHomeGraphToCreateRoutineGraph() }
    val navigateMainGraphToWorkGraph: () -> Unit = { navController.navigateHomeGraphToWorkGraph() }
    val navigateHomeGraphToBadgeGraph: () -> Unit =
        { navController.navigateHomeGraphToBadgeGraph() }
    val navigateHomeGraphToNewBadgeGraph: () -> Unit =
        { navController.navigateHomeGraphToNewBadgeGraph() }
    val navigateHomeGraphToNotificationGraph: () -> Unit =
        { navController.navigateHomeGraphToNotificationGraph() }
    val navigateHomeGraphToRoutineDetailGraph: () -> Unit =
        { navController.navigateHomeGraphToRoutineDetailGraph() }
    val navigateHomeGraphToRoutineDetailRoutineRouter: (Int) -> Unit =
        { navController.navigateHomeGraphToRoutineDetailRoutineRouter(it) }
    val navigateHomeGraphToBadgeSettingRouter: () -> Unit =
        { navController.navigateHomeGraphToBadgeSettingRouter() }

    navGraphBuilder.composable(HOME_HOME_ROUTER_NAME) {
        HomeRoute(
            navigateMainGraphToCreateRoutineGraph = navigateMainGraphToCreateRoutineGraph,
            navigateMainGraphToWorkGraph = navigateMainGraphToWorkGraph,
            navigateHomeGraphToBadgeGraph = navigateHomeGraphToBadgeGraph,
            navigateHomeGraphToNewBadgeGraph = navigateHomeGraphToNewBadgeGraph,
            navigateHomeGraphToNotificationGraph = navigateHomeGraphToNotificationGraph,
            navigateHomeGraphToRoutineDetailGraph = navigateHomeGraphToRoutineDetailGraph,
            navigateHomeGraphToRoutineDetailRoutineRouter = navigateHomeGraphToRoutineDetailRoutineRouter,
            navigateHomeGraphToBadgeSettingRouter = navigateHomeGraphToBadgeSettingRouter
        )
    }

}






