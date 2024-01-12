package com.gradation.lift.myInfo.updateProfilePicture

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route

fun myInfoUpdateProfileScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(Route.MY_INFO_UPDATE_PROFILE_PICTURE_ROUTER_NAME) {

        val navigateUpdateProfileToMyInfoInMyInfoGraph:() -> Unit = {  }
        MyInfoUpdateProfileRoute(
            navigateUpdateProfileToMyInfoInMyInfoGraph=navigateUpdateProfileToMyInfoInMyInfoGraph
        )
    }

}
