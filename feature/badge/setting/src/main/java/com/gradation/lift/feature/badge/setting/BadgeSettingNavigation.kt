package com.gradation.lift.feature.badge.setting

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateSettingToBadgeInBadgeGraph

fun badgeSettingScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    val navigateSettingToBadgeInBadgeGraph: () -> Unit = { navController.navigateSettingToBadgeInBadgeGraph() }

    navGraphBuilder.composable(Router.BADGE_SETTING_ROUTER_NAME) {

        BadgeSettingRoute(
            navigateSettingToBadgeInBadgeGraph=navigateSettingToBadgeInBadgeGraph,
        )
    }

}
