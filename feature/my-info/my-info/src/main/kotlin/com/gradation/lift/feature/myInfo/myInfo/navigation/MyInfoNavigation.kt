package com.gradation.lift.feature.myInfo.myInfo.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route
import com.gradation.lift.navigation.navigation.navigateMyInfoGraphToNoticeGraph
import com.gradation.lift.navigation.navigation.navigateMyInfoToProfileInMyInfoGraph
import com.gradation.lift.navigation.navigation.navigateMyInfoToTermsPolicyInMyInfoGraph

fun NavGraphBuilder.myInfoScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateToOssScreen: () -> Unit,
) {
    val navigateMyInfoGraphToNoticeGraph: () -> Unit =
        { navController.navigateMyInfoGraphToNoticeGraph() }

    val navigateMyInfoToProfileInMyInfoGraph: () -> Unit =
        { navController.navigateMyInfoToProfileInMyInfoGraph() }

    val navigateMyInfoToTermsPolicyInMyInfoGraph: () -> Unit =
        { navController.navigateMyInfoToTermsPolicyInMyInfoGraph() }

    composable(Route.MY_INFO_MY_INFO_ROUTER_NAME,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        popEnterTransition = { fadeIn() },
        popExitTransition = { fadeOut() }) {
        MyInfoRoute(
            modifier,
            navController,
            navigateMyInfoGraphToNoticeGraph,
            navigateMyInfoToProfileInMyInfoGraph,
            navigateMyInfoToTermsPolicyInMyInfoGraph,
            navigateToOssScreen
        )
    }
}
