package com.gradation.lift.myInfo.updateProfilePicture.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route

fun NavGraphBuilder.updateProfilePicture(
    modifier: Modifier=Modifier,
    navController: NavController,
) {
    composable(Route.MY_INFO_UPDATE_PROFILE_PICTURE_ROUTER_NAME) {

        val navigateUpdateProfileToMyInfoInMyInfoGraph:() -> Unit = {  }
        UpdateProfilePictureRoute(
            navigateUpdateProfileToMyInfoInMyInfoGraph=navigateUpdateProfileToMyInfoInMyInfoGraph
        )
    }

}
