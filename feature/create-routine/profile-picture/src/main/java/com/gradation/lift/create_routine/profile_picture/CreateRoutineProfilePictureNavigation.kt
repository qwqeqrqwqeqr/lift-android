package com.gradation.lift.create_routine.profile_picture

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_PROFILE_PICTURE_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateProfilePictureToRoutineSetInCreateRoutineGraph

fun createRoutineProfilePictureScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(CREATE_ROUTINE_PROFILE_PICTURE_ROUTER_NAME) {

        val navigateProfilePictureToRoutineSetInCreateRoutineGraph: () -> Unit =
            { navController.navigateProfilePictureToRoutineSetInCreateRoutineGraph() }

        CreateRoutineProfilePictureRoute(
            navController = navController,
            navigateProfilePictureToRoutineSetInCreateRoutineGraph = navigateProfilePictureToRoutineSetInCreateRoutineGraph
        )
    }
}


