package com.gradation.lift.feature.work.work.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route.WORK_WORK_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateWorkGraphToHomeGraph
import com.gradation.lift.navigation.navigation.navigateWorkToCompleteInWorkGraph
import com.gradation.lift.navigation.navigation.navigateWorkToFindWorkCategoryInWorkGraph


fun NavGraphBuilder.workScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    composable(route = WORK_WORK_ROUTER_NAME) {

        val navigateWorkToCompleteInWorkGraph: () -> Unit =
            { navController.navigateWorkToCompleteInWorkGraph() }
        val navigateWorkGraphToHomeGraph: () -> Unit =
            { navController.navigateWorkGraphToHomeGraph() }

        val navigateWorkToFindWorkCategoryInWorkGraph: () -> Unit =
            { navController.navigateWorkToFindWorkCategoryInWorkGraph() }

        WorkRoute(
            modifier,
            navController,
            navigateWorkToCompleteInWorkGraph,
            navigateWorkGraphToHomeGraph,
            navigateWorkToFindWorkCategoryInWorkGraph
        )
    }

}
