package com.gradation.lift.feature.home.home.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route.HOME_HOME_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateHomeGraphToBadgeBadgeRouter
import com.gradation.lift.navigation.navigation.navigateHomeGraphToCreateRoutineGraph
import com.gradation.lift.navigation.navigation.navigateHomeGraphToInquiryGraph
import com.gradation.lift.navigation.navigation.navigateHomeGraphToMyinfoProfileRouter
import com.gradation.lift.navigation.navigation.navigateHomeGraphToRoutineDetailGraph
import com.gradation.lift.navigation.navigation.navigateHomeGraphToRoutineDetailRoutineRouter
import com.gradation.lift.navigation.navigation.navigateHomeGraphToWorkGraph
import com.gradation.lift.navigation.navigation.navigateHomeGraphToWorkReadyReadyRouter
import com.gradation.lift.navigation.navigation.navigateHomeGraphToWorkReadyRoutineSelectionRouter
import com.gradation.lift.navigation.navigation.navigateHomeToBadgeInHomeGraph


fun NavGraphBuilder.homeScreen(modifier:Modifier=Modifier,navController: NavController) {


    val navigateMainGraphToCreateRoutineGraph: () -> Unit =
        { navController.navigateHomeGraphToCreateRoutineGraph() }
    val navigateHomeGraphToBadgeBadgeRouter: (Int) -> Unit =
        { page -> navController.navigateHomeGraphToBadgeBadgeRouter(page) }
    val navigateHomeToBadgeInHomeGraph: () -> Unit =
        { navController.navigateHomeToBadgeInHomeGraph() }
    val navigateHomeGraphToRoutineDetailGraph: () -> Unit =
        { navController.navigateHomeGraphToRoutineDetailGraph() }
    val navigateHomeGraphToRoutineDetailRoutineRouter: (Int) -> Unit =
        { navController.navigateHomeGraphToRoutineDetailRoutineRouter(it) }
    val navigateHomeGraphToWorkReadyReadyRouter: () -> Unit =
        { navController.navigateHomeGraphToWorkReadyReadyRouter() }
    val navigateHomeGraphToWorkReadyRoutineSelectionRouter: () -> Unit =
        { navController.navigateHomeGraphToWorkReadyRoutineSelectionRouter() }
    val navigateHomeGraphToMyinfoProfileRouter: () -> Unit =
        { navController.navigateHomeGraphToMyinfoProfileRouter() }
    val navigateHomeGraphToInquiryGraph: () -> Unit =
        { navController.navigateHomeGraphToInquiryGraph() }
    val navigateHomeGraphToWorkGraph: () -> Unit =
        { navController.navigateHomeGraphToWorkGraph() }

    composable(HOME_HOME_ROUTER_NAME) {
        HomeRoute(
            modifier,
            navController,
            navigateMainGraphToCreateRoutineGraph,
            navigateHomeGraphToBadgeBadgeRouter,
            navigateHomeToBadgeInHomeGraph,
            navigateHomeGraphToRoutineDetailGraph,
            navigateHomeGraphToRoutineDetailRoutineRouter,
            navigateHomeGraphToWorkReadyRoutineSelectionRouter,
            navigateHomeGraphToWorkReadyReadyRouter,
            navigateHomeGraphToMyinfoProfileRouter,
            navigateHomeGraphToInquiryGraph,
            navigateHomeGraphToWorkGraph
        )
    }

}






