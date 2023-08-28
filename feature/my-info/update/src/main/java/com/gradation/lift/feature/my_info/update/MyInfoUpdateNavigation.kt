package com.gradation.lift.feature.my_info.update

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateUpdateToMyInfo

fun myInfoUpdateScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(Router.MY_INFO_UPDATE_ROUTER_NAME) {

        val navigateUpdateToMyInfo:() -> Unit = { navController.navigateUpdateToMyInfo() }


        MyInfoUpdateRoute(
            navigateUpdateToMyInfo=navigateUpdateToMyInfo

        )
    }

}
