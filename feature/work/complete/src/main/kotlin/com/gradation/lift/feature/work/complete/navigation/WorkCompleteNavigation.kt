package com.gradation.lift.feature.work.complete.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.WORK_COMPLETE_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateWorkGraphToHomeGraph



fun completeScreen(
    modifier:Modifier= Modifier,
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {

    val navigateWorkGraphToHomeGraph: () -> Unit = { navController.navigateWorkGraphToHomeGraph() }

    navGraphBuilder.composable(WORK_COMPLETE_ROUTER_NAME) {
        CompleteRoute(
            modifier,
            navController,
            navigateWorkGraphToHomeGraph
        )
    }

}
