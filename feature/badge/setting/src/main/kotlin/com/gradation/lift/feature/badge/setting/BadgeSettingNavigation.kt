package com.gradation.lift.feature.badge.setting

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateBadgeGraphToHomeGraph
import com.gradation.lift.navigation.navigation.navigateSettingToBadgeInBadgeGraph

fun badgeSettingScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    val navigateBadgeGraphToPreGraph: () -> Unit = {
        when (navController.previousBackStackEntry?.destination?.route.toString()) {
            Router.BADGE_BADGE_ROUTER_NAME -> navController.navigateSettingToBadgeInBadgeGraph()
            else -> navController.navigateBadgeGraphToHomeGraph()
        }
    }

    navGraphBuilder.composable(Router.BADGE_SETTING_ROUTER_NAME) {

        BadgeSettingRoute(
            navigateBadgeGraphToPreGraph=navigateBadgeGraphToPreGraph,
        )
    }

}
