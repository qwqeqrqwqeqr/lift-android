package com.gradation.lift.feature.work.complete

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.WORK_WORK_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateReadyWorkToWorkGraph


fun workWorkScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(WORK_WORK_ROUTER_NAME) {

        val navigateToReadyWorkSelection = { navController.navigateUp() }
        val navigateReadyWorkToWorkGraph = { navController.navigateReadyWorkToWorkGraph() }

        WorkWorkRoute(
            navController = navController,
        )
    }

}
