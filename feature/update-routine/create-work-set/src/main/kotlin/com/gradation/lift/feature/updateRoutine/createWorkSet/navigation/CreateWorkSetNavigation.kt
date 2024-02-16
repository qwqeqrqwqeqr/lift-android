package com.gradation.lift.feature.updateRoutine.createWorkSet.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gradation.lift.navigation.Route.UPDATE_ROUTINE_CREATE_WORK_SET_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateCreateWorkSetRouteToFindWorkCategoryInUpdateRoutineGraph
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey


fun createWorkSetScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {

    val navigateCreateWorkSetRouteToFindWorkCategoryInUpdateRoutineGraph: () -> Unit =
        { navController.navigateCreateWorkSetRouteToFindWorkCategoryInUpdateRoutineGraph() }


    navGraphBuilder.composable(
        route = "${UPDATE_ROUTINE_CREATE_WORK_SET_ROUTER_NAME}/{${SavedStateHandleKey.UpdateRoutine.UPDATE_WORK_CATEGORY_ID_KEY}}",
        arguments = listOf(
            navArgument(SavedStateHandleKey.UpdateRoutine.UPDATE_WORK_CATEGORY_ID_KEY) {
                type = NavType.IntType
            },
        ),
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        popEnterTransition = { fadeIn() },
        popExitTransition = { fadeOut() }
    ) {
        CreateWorkSetRoute(
            modifier,
            navController,
            navigateCreateWorkSetRouteToFindWorkCategoryInUpdateRoutineGraph,
        )
    }
}
