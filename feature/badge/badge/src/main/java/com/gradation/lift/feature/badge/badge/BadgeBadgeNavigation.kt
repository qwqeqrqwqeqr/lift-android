package com.gradation.lift.feature.badge.badge

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateBadgeGraphToHomeGraph
import com.gradation.lift.navigation.navigation.navigateBadgeGraphToMyInfoGraph
import com.gradation.lift.navigation.navigation.navigateBadgeToSettingInBadgeGraph

fun badgeBadgeScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    val navigateBadgeGraphToPreGraph: () -> Unit = {
        when (navController.previousBackStackEntry?.destination?.route.toString()) {
            Router.MY_INFO_MY_INFO_ROUTER_NAME -> navController.navigateBadgeGraphToMyInfoGraph()
            else -> navController.navigateBadgeGraphToHomeGraph()
        }
    }
    val navigateBadgeToSettingInBadgeGraph: () -> Unit =
        { navController.navigateBadgeToSettingInBadgeGraph() }




    navGraphBuilder.composable(Router.BADGE_BADGE_ROUTER_NAME) {

        BadgeBadgeRoute(
            navigateBadgeGraphToPreGraph=navigateBadgeGraphToPreGraph,
            navigateBadgeToSettingInBadgeGraph=navigateBadgeToSettingInBadgeGraph
        )
    }

}
