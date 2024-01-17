package com.gradation.lift.feature.workReady.findWorkCategory.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavController
import com.gradation.lift.navigation.Route.WORK_READY_FIND_WORK_CATEGORY_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateFindWorkCategoryToCreateWorkSetInWorkReadyGraph
import com.gradation.lift.navigation.navigation.navigateFindWorkCategoryToReadyInWorkReadyGraph


fun NavGraphBuilder.findWorkCategoryScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val navigateFindWorkCategoryToReadyInWorkReadyGraph: () -> Unit =
        { navController.navigateFindWorkCategoryToReadyInWorkReadyGraph() }

    val navigateFindWorkCategoryToCreateWorkSetInWorkReadyGraph: (Int) -> Unit =
        { navController.navigateFindWorkCategoryToCreateWorkSetInWorkReadyGraph(it) }

    composable(WORK_READY_FIND_WORK_CATEGORY_ROUTER_NAME) {
        FindWorkCategoryRoute(
            modifier = modifier,
            navigateFindWorkCategoryToReadyInWorkReadyGraph,
            navigateFindWorkCategoryToCreateWorkSetInWorkReadyGraph
        )
    }

}
