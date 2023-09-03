package com.gradation.lift.feature.work.work

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.WORK_WORK_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateWorkGraphToHomeGraph
import com.gradation.lift.navigation.navigation.navigateWorkToComplete


fun workWorkScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(WORK_WORK_ROUTER_NAME) {

        val navigateWorkToComplete: () -> Unit = { navController.navigateWorkToComplete() }
        val navigateWorkGraphToHomeGraph: () -> Unit = { navController.navigateWorkGraphToHomeGraph() }

        WorkWorkRoute(
            navController = navController,
            navigateWorkToComplete=navigateWorkToComplete,
            navigateWorkGraphToHomeGraph=navigateWorkGraphToHomeGraph
        )
    }

}
