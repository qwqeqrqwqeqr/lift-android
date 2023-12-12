package com.gradation.lift.feature.my_info.update

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateUpdateToMyInfoInMyInfoGraph

fun myInfoUpdateScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(Router.MY_INFO_UPDATE_ROUTER_NAME) {

        val navigateUpdateToMyInfoInMyInfoGraph:() -> Unit = { navController.navigateUpdateToMyInfoInMyInfoGraph() }


        MyInfoUpdateRoute(
            navigateUpdateToMyInfoInMyInfoGraph=navigateUpdateToMyInfoInMyInfoGraph

        )
    }

}
