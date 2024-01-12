package com.gradation.lift.feature.myInfo.updateInfo

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.feature.my_info.update.MyInfoUpdateRoute
import com.gradation.lift.navigation.Route

fun myInfoUpdateScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(Route.MY_INFO_UPDATE_INFO_ROUTER_NAME) {

        val navigateUpdateToMyInfoInMyInfoGraph:() -> Unit = { }


        MyInfoUpdateRoute(
            navigateUpdateToMyInfoInMyInfoGraph=navigateUpdateToMyInfoInMyInfoGraph

        )
    }

}
