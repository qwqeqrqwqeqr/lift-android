package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.Router.BADGE_SETTING_ROUTER_NAME


fun NavController.navigateBadgeGraphToHomeGraph() {
    this.navigate(Router.HOME_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(this@navigateBadgeGraphToHomeGraph.graph.id) {
            inclusive = true
        }
    }
}

fun NavController.navigateBadgeGraphToMyInfoGraph() {
    this.navigate(Router.MY_INFO_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(this@navigateBadgeGraphToMyInfoGraph.graph.id) {
            inclusive = true
        }
    }
}


fun NavController.navigateBadgeToSetting() {
    this.navigate(Router.BADGE_BADGE_ROUTER_NAME)
}

fun NavController.navigateSettingToBadge() {
    this.navigate(BADGE_SETTING_ROUTER_NAME) {
        this.popUpTo(BADGE_SETTING_ROUTER_NAME)
    }
}
