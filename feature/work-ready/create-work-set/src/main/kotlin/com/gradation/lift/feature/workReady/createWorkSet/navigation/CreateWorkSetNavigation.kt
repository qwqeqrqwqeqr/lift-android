package com.gradation.lift.feature.workReady.createWorkSet.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gradation.lift.navigation.Route.WORK_READY_CREATE_WORK_SET_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateCreateWorkSetToFindWorkCategoryInWorkReadyGraph
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.Work.WORK_READY_WORK_CATEGORY_ID_KEY


fun NavGraphBuilder.createWorkSetScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {

    val navigateCreateWorkSetToFindWorkCategoryInWorkReadyGraph: () -> Unit =
        { navController.navigateCreateWorkSetToFindWorkCategoryInWorkReadyGraph() }


    composable(
        route="${WORK_READY_CREATE_WORK_SET_ROUTER_NAME}/{${WORK_READY_WORK_CATEGORY_ID_KEY}}",
        arguments = listOf(
            navArgument(WORK_READY_WORK_CATEGORY_ID_KEY) {
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
            navigateCreateWorkSetToFindWorkCategoryInWorkReadyGraph,
        )
    }
}
