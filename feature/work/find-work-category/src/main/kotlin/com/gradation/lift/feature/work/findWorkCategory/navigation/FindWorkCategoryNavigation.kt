package com.gradation.lift.feature.work.findWorkCategory.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavController
import com.gradation.lift.feature.work.findWorkCategory.navigation.FindWorkCategoryRoute
import com.gradation.lift.navigation.Route.WORK_FIND_WORK_CATEGORY_ROUTER_NAME
import com.gradation.lift.navigation.Route.WORK_READY_FIND_WORK_CATEGORY_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateFindWorkCategoryToCreateWorkSetInWorkGraph
import com.gradation.lift.navigation.navigation.navigateFindWorkCategoryToCreateWorkSetInWorkReadyGraph
import com.gradation.lift.navigation.navigation.navigateFindWorkCategoryToReadyInWorkReadyGraph
import com.gradation.lift.navigation.navigation.navigateFindWorkCategoryToWorkInWorkGraph


fun NavGraphBuilder.findWorkCategoryScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val navigateFindWorkCategoryToWorkInWorkGraph: () -> Unit =
        { navController.navigateFindWorkCategoryToWorkInWorkGraph() }

    val navigateFindWorkCategoryToCreateWorkSetInWorkGraph: (Int) -> Unit =
        { navController.navigateFindWorkCategoryToCreateWorkSetInWorkGraph(it) }

    composable(WORK_FIND_WORK_CATEGORY_ROUTER_NAME) {
        FindWorkCategoryRoute(
            modifier = modifier,
            navigateFindWorkCategoryToWorkInWorkGraph,
            navigateFindWorkCategoryToCreateWorkSetInWorkGraph
        )
    }

}
