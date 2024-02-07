package com.gradation.lift.feature.myInfo.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.myInfo.myInfo.navigation.myInfoScreen
import com.gradation.lift.feature.myInfo.profile.navigation.profileScreen
import com.gradation.lift.feature.myInfo.updateInfo.navigation.updateInfoScreen
import com.gradation.lift.feature.myInfo.updateName.navigation.updateNameScreen
import com.gradation.lift.myInfo.updateProfilePicture.navigation.updateProfilePicture
import com.gradation.lift.navigation.Route.MY_INFO_GRAPH_NAME
import com.gradation.lift.navigation.Route.MY_INFO_MY_INFO_ROUTER_NAME

fun NavGraphBuilder.myInfoGraphBuilder(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateToOssScreen: () -> Unit,
) {
    navigation(
        route = MY_INFO_GRAPH_NAME,
        startDestination = MY_INFO_MY_INFO_ROUTER_NAME,
        popEnterTransition = { fadeIn() },
        popExitTransition = { fadeOut() },
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
    ) {
        myInfoScreen(modifier, navController, navigateToOssScreen)
        profileScreen(modifier, navController)
        updateInfoScreen(modifier, navController)
        updateNameScreen(modifier, navController)
        updateProfilePicture(modifier, navController)
    }
}
