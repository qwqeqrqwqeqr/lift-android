package com.gradation.lift.feature.work.createWorkSet.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gradation.lift.navigation.Route.WORK_CREATE_WORK_SET_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateCreateWorkSetToFindWorkCategoryInWorkGraph
import com.gradation.lift.navigation.navigation.navigateCreateWorkSetToWorkInWorkGraph
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.Work.WORK_WORK_CATEGORY_ID_KEY


fun NavGraphBuilder.createWorkSetScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {

    val navigateCreateWorkSetToFindWorkCategoryInWorkGraph: () -> Unit =
        { navController.navigateCreateWorkSetToFindWorkCategoryInWorkGraph() }

    val navigateCreateWorkSetToWorkInWorkGraph: () -> Unit =
        { navController.navigateCreateWorkSetToWorkInWorkGraph() }

    composable(
        route="${WORK_CREATE_WORK_SET_ROUTER_NAME}/{${WORK_WORK_CATEGORY_ID_KEY}}",
        arguments = listOf(
            navArgument(WORK_WORK_CATEGORY_ID_KEY) {
                type = NavType.IntType
            },
        ),
        enterTransition = { fadeIn()},
        exitTransition = { fadeOut() },
        popEnterTransition = { fadeIn() },
        popExitTransition = { fadeOut() }
    ) {
        CreateWorkSetRoute(
            modifier,
            navController,
            navigateCreateWorkSetToFindWorkCategoryInWorkGraph,
            navigateCreateWorkSetToWorkInWorkGraph
        )
    }
}
