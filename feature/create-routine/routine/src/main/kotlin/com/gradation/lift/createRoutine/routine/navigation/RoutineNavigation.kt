package com.gradation.lift.createRoutine.routine.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gradation.lift.navigation.Route.CREATE_ROUTINE_ROUTINE_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateRoutineToFindWorkCategoryInCreateRoutineGraph
import com.gradation.lift.navigation.navigation.navigateRoutineToRoutineSetInCreateRoutineGraph
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.RoutineSet.CREATE_WORK_CATEGORY_ID_KEY


fun routineScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {

    val navigateRoutineToFindWorkCategoryInCreateRoutineGraph: () -> Unit =
        { navController.navigateRoutineToFindWorkCategoryInCreateRoutineGraph() }

    val navigateRoutineToRoutineSetInCreateRoutineGraph: () -> Unit =
        { navController.navigateRoutineToRoutineSetInCreateRoutineGraph() }

    navGraphBuilder.composable(
        route="${CREATE_ROUTINE_ROUTINE_ROUTER_NAME}/{${CREATE_WORK_CATEGORY_ID_KEY}}",
        arguments = listOf(
            navArgument(CREATE_WORK_CATEGORY_ID_KEY) {
                type = NavType.IntType
            },
        ),
        enterTransition = { fadeIn()},
        exitTransition = { fadeOut() },
        popEnterTransition = { fadeIn() },
        popExitTransition = { fadeOut() }
    ) { navBackstackEntry ->

        val workCategoryId: Int = navBackstackEntry.arguments?.getInt(CREATE_WORK_CATEGORY_ID_KEY) ?: 0


        RoutineRoute(
            modifier,
            navController,
            workCategoryId,
            navigateRoutineToFindWorkCategoryInCreateRoutineGraph,
            navigateRoutineToRoutineSetInCreateRoutineGraph
        )
    }
}
