package com.gradation.lift.feature.myInfo.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.myInfo.myInfo.myInfoMyInfoScreen
import com.gradation.lift.feature.my_info.update.myInfoUpdateScreen
import com.gradation.lift.myInfo.updateProfilePicture.myInfoUpdateProfileScreen
import com.gradation.lift.navigation.Route.MY_INFO_GRAPH_NAME
import com.gradation.lift.navigation.Route.MY_INFO_MY_INFO_ROUTER_NAME

fun myInfoGraphBuilder(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.navigation(
        route = MY_INFO_GRAPH_NAME,
        startDestination = MY_INFO_MY_INFO_ROUTER_NAME,
        popEnterTransition = { fadeIn() },
        popExitTransition = { fadeOut() },
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
    ) {
        myInfoMyInfoScreen(navController, this)
        myInfoUpdateScreen(navController, this)
        myInfoUpdateProfileScreen(navController, this)

    }
}
