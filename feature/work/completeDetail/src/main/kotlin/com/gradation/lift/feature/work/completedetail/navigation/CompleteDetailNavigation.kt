package com.gradation.lift.feature.work.completedetail.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route.WORK_COMPLETE_DETAIL_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateWorkGraphToHomeGraph


fun NavGraphBuilder.completeDetailScreen(
    modifier:Modifier= Modifier,
    navController: NavController,
) {

    val navigateWorkGraphToHomeGraph: () -> Unit = { navController.navigateWorkGraphToHomeGraph() }

    composable(WORK_COMPLETE_DETAIL_ROUTER_NAME) {
        CompleteDetailRoute(
            modifier,
            navController,
            navigateWorkGraphToHomeGraph
        )
    }

}
