package com.gradation.lift.feature.updateRoutine.routineSet

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.UPDATE_ROUTINE_ROUTINE_SET_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateRoutineSetToFindWorkCategoryInUpdateRoutineGraph
import com.gradation.lift.navigation.navigation.navigateRoutineSetToProfilePictureInUpdateRoutineGraph


fun routineSetScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(UPDATE_ROUTINE_ROUTINE_SET_ROUTER_NAME) {

        val navigateRoutineSetToFindWorkCategoryInUpdateRoutineGraph: () -> Unit =
            { navController.navigateRoutineSetToFindWorkCategoryInUpdateRoutineGraph() }

        val navigateRoutineSetToProfilePictureInUpdateRoutineGraph: () -> Unit =
            { navController.navigateRoutineSetToProfilePictureInUpdateRoutineGraph() }

        UpdateRoutineRoutineSetRoute(
            modifier,
            navController,
            navigateRoutineSetToFindWorkCategoryInUpdateRoutineGraph,
            navigateRoutineSetToProfilePictureInUpdateRoutineGraph
        )
    }
}


