package com.gradation.lift.feature.my_info.my_info

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router

fun myInfoScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(Router.MY_INFO_ROUTER_NAME) {
        MyInfoRoute(navController = navController)
    }
}
