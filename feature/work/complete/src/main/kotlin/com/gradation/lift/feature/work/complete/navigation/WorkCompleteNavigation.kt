package com.gradation.lift.feature.work.complete.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route.WORK_COMPLETE_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateWorkGraphToHomeGraph



fun NavGraphBuilder.completeScreen(
    modifier:Modifier= Modifier,
    navController: NavController,
) {

    val navigateWorkGraphToHomeGraph: () -> Unit = { navController.navigateWorkGraphToHomeGraph() }

    composable(WORK_COMPLETE_ROUTER_NAME) {
        CompleteRoute(
            modifier,
            navController,
            navigateWorkGraphToHomeGraph
        )
    }

}
