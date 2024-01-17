package com.gradation.lift.createRoutine.profilePicture.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route.CREATE_ROUTINE_PROFILE_PICTURE_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateProfilePictureToRoutineSetInCreateRoutineGraph


fun profilePictureScreen(
    modifier:Modifier=Modifier,
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(CREATE_ROUTINE_PROFILE_PICTURE_ROUTER_NAME) {

        val navigateProfilePictureToRoutineSetInCreateRoutineGraph: () -> Unit =
            { navController.navigateProfilePictureToRoutineSetInCreateRoutineGraph() }

       ProfilePictureRoute(
            modifier,
            navController ,
            navigateProfilePictureToRoutineSetInCreateRoutineGraph
        )
    }
}


