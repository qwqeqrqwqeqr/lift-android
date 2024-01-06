package com.gradation.lift.feature.work.work.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route.WORK_WORK_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateWorkGraphToHomeGraph
import com.gradation.lift.navigation.navigation.navigateWorkToCompleteInWorkGraph


fun workScreen(
    modifier: Modifier=Modifier,
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(WORK_WORK_ROUTER_NAME) {

        val navigateWorkToCompleteInWorkGraph: () -> Unit =
            { navController.navigateWorkToCompleteInWorkGraph() }
        val navigateWorkGraphToHomeGraph: () -> Unit =
            { navController.navigateWorkGraphToHomeGraph() }

        WorkRoute(
            modifier,
            navController,
            navigateWorkToCompleteInWorkGraph,
            navigateWorkGraphToHomeGraph
        )
    }

}
