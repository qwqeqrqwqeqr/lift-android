package com.gradation.lift.feature.createRoutine.routineSet.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateCreateRoutineGraphToRoutineDetailGraph
import com.gradation.lift.navigation.navigation.navigateRoutineSetToFindWorkCategoryInCreateRoutineGraph
import com.gradation.lift.navigation.navigation.navigateRoutineSetToProfilePictureInCreateRoutineGraph


fun routineSetScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME) {

        val navigateRoutineSetToFindWorkCategoryInCreateRoutineGraph: () -> Unit =
            { navController.navigateRoutineSetToFindWorkCategoryInCreateRoutineGraph() }

        val navigateRoutineSetToProfilePictureInCreateRoutineGraph: () -> Unit =
            { navController.navigateRoutineSetToProfilePictureInCreateRoutineGraph() }

        val navigateCreateRoutineGraphToRoutineDetailGraph: () -> Unit = {
            navController.navigateCreateRoutineGraphToRoutineDetailGraph()
        }

        RoutineSetRoute(
            modifier,
            navController,
            navigateRoutineSetToFindWorkCategoryInCreateRoutineGraph,
            navigateRoutineSetToProfilePictureInCreateRoutineGraph,
            navigateCreateRoutineGraphToRoutineDetailGraph
        )
    }
}



