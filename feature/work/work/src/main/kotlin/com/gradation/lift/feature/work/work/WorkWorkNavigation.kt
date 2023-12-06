package com.gradation.lift.feature.work.work

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.WORK_WORK_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateWorkGraphToHomeGraph
import com.gradation.lift.navigation.navigation.navigateWorkToCompleteInWorkGraph


fun workWorkScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(WORK_WORK_ROUTER_NAME) {

        val navigateWorkToCompleteInWorkGraph: () -> Unit = { navController.navigateWorkToCompleteInWorkGraph() }
        val navigateWorkGraphToHomeGraph: () -> Unit = { navController.navigateWorkGraphToHomeGraph() }

        WorkWorkRoute(
            navController = navController,
            navigateWorkToCompleteInWorkGraph=navigateWorkToCompleteInWorkGraph,
            navigateWorkGraphToHomeGraph=navigateWorkGraphToHomeGraph
        )
    }

}
