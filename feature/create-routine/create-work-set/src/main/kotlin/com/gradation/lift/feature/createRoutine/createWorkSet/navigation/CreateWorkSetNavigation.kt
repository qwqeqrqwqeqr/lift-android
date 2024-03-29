package com.gradation.lift.feature.createRoutine.createWorkSet.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gradation.lift.navigation.Route.CREATE_ROUTINE_CREATE_WORK_SET_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateCreateWorkSetToFindWorkCategoryInCreateRoutineGraph
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.CreateRoutine.CREATE_WORK_CATEGORY_ID_KEY


fun createWorkSetScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {

    val navigateCreateWorkSetToFindWorkCategoryInCreateRoutineGraph: () -> Unit =
        { navController.navigateCreateWorkSetToFindWorkCategoryInCreateRoutineGraph() }


    navGraphBuilder.composable(
        route="${CREATE_ROUTINE_CREATE_WORK_SET_ROUTER_NAME}/{${CREATE_WORK_CATEGORY_ID_KEY}}",
        arguments = listOf(
            navArgument(CREATE_WORK_CATEGORY_ID_KEY) {
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
            navigateCreateWorkSetToFindWorkCategoryInCreateRoutineGraph,
        )
    }
}
