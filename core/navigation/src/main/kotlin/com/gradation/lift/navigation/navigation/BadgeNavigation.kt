package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Route
import com.gradation.lift.navigation.Route.BADGE_BADGE_ROUTER_NAME
import com.gradation.lift.navigation.Route.BADGE_SETTING_ROUTER_NAME


fun NavController.navigateBadgeGraphToHomeGraph(){
    this.navigate(Route.HOME_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(this@navigateBadgeGraphToHomeGraph.graph.id) {
            inclusive = true
        }
    }
}

fun NavController.navigateBadgeGraphToMyInfoGraph(){
    this.navigate(Route.MY_INFO_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(this@navigateBadgeGraphToMyInfoGraph.graph.id) {
            inclusive = true
        }
    }
}


fun NavController.navigateBadgeToSettingInBadgeGraph() {
    this.navigate(BADGE_SETTING_ROUTER_NAME)
}

fun NavController.navigateSettingToBadgeInBadgeGraph() {
    this.navigate(BADGE_BADGE_ROUTER_NAME) {
        this.popUpTo(BADGE_BADGE_ROUTER_NAME)
    }
}



fun NavController.navigateNewBadgeGraphToHomeGraph() {
    this.navigate(Route.HOME_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(this@navigateNewBadgeGraphToHomeGraph.graph.id) {
            inclusive = true
        }
    }
}
