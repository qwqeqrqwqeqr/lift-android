package com.gradation.lift.myInfo.updateProfilePicture.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gradation.lift.navigation.Route.MY_INFO_UPDATE_PROFILE_PICTURE_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateUpdateProfilePictureToProfileInMyInfoGraph
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.MyInfo.USER_PROFILE_PICTURE_KEY

fun NavGraphBuilder.updateProfilePicture(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    composable(
        route = "$MY_INFO_UPDATE_PROFILE_PICTURE_ROUTER_NAME/{$USER_PROFILE_PICTURE_KEY}",
        arguments = listOf(navArgument(USER_PROFILE_PICTURE_KEY) { type = NavType.StringType }),
    ) {

        val navigateUpdateProfileToMyInfoInMyInfoGraph: () -> Unit = { navController.navigateUpdateProfilePictureToProfileInMyInfoGraph() }


        UpdateProfilePictureRoute(
            modifier,
            navigateUpdateProfileToMyInfoInMyInfoGraph
        )
    }

}


