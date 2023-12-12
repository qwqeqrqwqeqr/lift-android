package com.gradation.lift.feature.myInfo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.my_info.my_info.myInfoMyInfoScreen
import com.gradation.lift.feature.my_info.update.myInfoUpdateScreen
import com.gradation.lift.my_info.update_profile.myInfoUpdateProfileScreen
import com.gradation.lift.navigation.Router.MY_INFO_GRAPH_NAME
import com.gradation.lift.navigation.Router.MY_INFO_MY_INFO_ROUTER_NAME

fun myInfoGraphBuilder(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.navigation(
        route = MY_INFO_GRAPH_NAME,
        startDestination = MY_INFO_MY_INFO_ROUTER_NAME,
    ) {
        myInfoMyInfoScreen(navController, this)
        myInfoUpdateScreen(navController, this)
        myInfoUpdateProfileScreen(navController, this)

    }
}
