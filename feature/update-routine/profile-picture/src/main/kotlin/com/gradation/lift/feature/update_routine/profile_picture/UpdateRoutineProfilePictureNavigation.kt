package com.gradation.lift.feature.update_routine.profile_picture

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.UPDATE_ROUTINE_PROFILE_PICTURE_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateProfilePictureToRoutineSetInUpdateRoutineGraph


fun updateRoutineProfilePictureScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(UPDATE_ROUTINE_PROFILE_PICTURE_ROUTER_NAME) {

        val navigateProfilePictureToRoutineSetInUpdateRoutineGraph: () -> Unit =
            { navController.navigateProfilePictureToRoutineSetInUpdateRoutineGraph() }

        UpdateRoutineProfilePictureRoute(
            navController = navController,
            navigateProfilePictureToRoutineSetInUpdateRoutineGraph = navigateProfilePictureToRoutineSetInUpdateRoutineGraph
        )
    }
}

