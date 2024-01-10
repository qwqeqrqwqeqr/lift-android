package com.gradation.lift.feature.updateRoutine.updateWorkSet.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gradation.lift.navigation.Route.UPDATE_ROUTINE_UPDATE_WORK_SET_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateUpdateWorkSetToRoutineSetInUpdateRoutineGraph
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.UpdateRoutine.UPDATE_ROUTINE_SELECTED_ROUTINE_INDEX_KEY


fun NavGraphBuilder.updateWorkSetScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {

    val navigateUpdateWorkSetToRoutineSetInUpdateRoutineGraph: () -> Unit =
        { navController.navigateUpdateWorkSetToRoutineSetInUpdateRoutineGraph() }


    composable(
        route = "${UPDATE_ROUTINE_UPDATE_WORK_SET_ROUTER_NAME}/{${UPDATE_ROUTINE_SELECTED_ROUTINE_INDEX_KEY}}",
        arguments = listOf(
            navArgument(UPDATE_ROUTINE_SELECTED_ROUTINE_INDEX_KEY) {
                type = NavType.IntType
            },
        ),
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        popEnterTransition = { fadeIn() },
        popExitTransition = { fadeOut() }
    ) { navBackstackEntry ->

        val routineIndex: Int? = navBackstackEntry.arguments?.getInt(UPDATE_ROUTINE_SELECTED_ROUTINE_INDEX_KEY)

        UpdateWorkSetRoute(
            modifier,
            navController,
            routineIndex,
            navigateUpdateWorkSetToRoutineSetInUpdateRoutineGraph,
        )
    }
}
