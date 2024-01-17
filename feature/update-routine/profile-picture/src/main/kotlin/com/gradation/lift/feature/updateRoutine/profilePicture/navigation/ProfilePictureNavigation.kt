package com.gradation.lift.feature.updateRoutine.profilePicture.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route.UPDATE_ROUTINE_PROFILE_PICTURE_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateProfilePictureToRoutineSetInUpdateRoutineGraph


fun profilePictureScreen(
    modifier:Modifier=Modifier,
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(UPDATE_ROUTINE_PROFILE_PICTURE_ROUTER_NAME) {

        val navigateProfilePictureToRoutineSetInUpdateRoutineGraph: () -> Unit =
            { navController.navigateProfilePictureToRoutineSetInUpdateRoutineGraph() }

       ProfilePictureRoute(
            modifier=modifier,
            navController = navController,
            navigateProfilePictureToRoutineSetInUpdateRoutineGraph = navigateProfilePictureToRoutineSetInUpdateRoutineGraph
        )
    }
}


