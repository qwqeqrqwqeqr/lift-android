package com.gradation.lift.my_info.update_profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateUpdateProfileToMyInfo

fun myInfoUpdateProfileScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(Router.MY_INFO_UPDATE_PROFILE_ROUTER_NAME) {

        val navigateUpdateProfileToMyInfo:() -> Unit = { navController.navigateUpdateProfileToMyInfo() }
        MyInfoUpdateProfileRoute(
            navigateUpdateProfileToMyInfo=navigateUpdateProfileToMyInfo
        )
    }

}
