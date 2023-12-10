package com.gradation.lift.feature.createRoutine.routineSet

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateRoutineSetToFindWorkCategoryInCreateRoutineGraph
import com.gradation.lift.navigation.navigation.navigateCreateRoutineGraphToHomeGraph
import com.gradation.lift.navigation.navigation.navigateRoutineSetToProfilePictureInCreateRoutineGraph


fun createRoutineScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    val navigateRoutineSetToFindWorkCategoryInCreateRoutineGraph: ()->Unit =
        { navController.navigateRoutineSetToFindWorkCategoryInCreateRoutineGraph() }
    val navigateRoutineSetToProfilePictureInCreateRoutineGraph: ()->Unit = { navController.navigateRoutineSetToProfilePictureInCreateRoutineGraph() }
    val navigateCreateRoutineGraphToHomeGraph: ()->Unit = { navController.navigateCreateRoutineGraphToHomeGraph() }

    navGraphBuilder.composable(CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME) {
        CreateRoutineRoutineSetRoute(
            navController = navController,
            navigateRoutineSetToFindWorkCategoryInCreateRoutineGraph=navigateRoutineSetToFindWorkCategoryInCreateRoutineGraph,
            navigateRoutineSetToProfilePictureInCreateRoutineGraph=navigateRoutineSetToProfilePictureInCreateRoutineGraph,
            navigateCreateRoutineGraphToHomeGraph=navigateCreateRoutineGraphToHomeGraph
        )
    }
}

