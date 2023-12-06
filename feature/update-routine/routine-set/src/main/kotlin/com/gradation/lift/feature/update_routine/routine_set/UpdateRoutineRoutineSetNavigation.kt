package com.gradation.lift.feature.update_routine.routine_set

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.UPDATE_ROUTINE_ROUTINE_SET_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateRoutineSetToFindWorkCategoryInUpdateRoutineGraph
import com.gradation.lift.navigation.navigation.navigateRoutineSetToProfilePictureInUpdateRoutineGraph


fun updateRoutineRoutineSetScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(UPDATE_ROUTINE_ROUTINE_SET_ROUTER_NAME) {

        val navigateRoutineSetToFindWorkCategoryInUpdateRoutineGraph: () -> Unit =
            { navController.navigateRoutineSetToFindWorkCategoryInUpdateRoutineGraph() }

        val navigateRoutineSetToProfilePictureInUpdateRoutineGraph: () -> Unit =
            { navController.navigateRoutineSetToProfilePictureInUpdateRoutineGraph() }

        UpdateRoutineRoutineSetRoute(
            navController,
            navigateRoutineSetToFindWorkCategoryInUpdateRoutineGraph,
            navigateRoutineSetToProfilePictureInUpdateRoutineGraph
        )
    }
}


