package com.gradation.lift.feature.work.work

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.WORK_WORK_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateWorkGraphToMainGraph
import com.gradation.lift.navigation.navigation.navigateWorkToComplete


fun workWorkScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(WORK_WORK_ROUTER_NAME) {

        val navigateWorkToComplete = { navController.navigateWorkToComplete() }
        val navigateWorkGraphToMainGraph = { navController.navigateWorkGraphToMainGraph() }

        WorkWorkRoute(
            navController = navController,
            navigateWorkToComplete=navigateWorkToComplete,
            navigateWorkGraphToMainGraph=navigateWorkGraphToMainGraph
        )
    }

}
