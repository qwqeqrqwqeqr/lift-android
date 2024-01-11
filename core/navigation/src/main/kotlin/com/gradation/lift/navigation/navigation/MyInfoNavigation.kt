package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Route


fun NavController.navigateMyInfoGraphToLoginGraph() {
    this.navigate(Route.LOGIN_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(this@navigateMyInfoGraphToLoginGraph.graph.id) {
            inclusive = true
        }
    }
}


fun NavController.navigateMyInfoGraphToNotificationGraph() {
    this.navigate(Route.NOTICE_GRAPH_NAME)
}

fun NavController.navigateMyInfoGraphToBadgeGraph() {
    this.navigate(Route.BADGE_GRAPH_NAME)
}


fun NavController.navigateMyInfoToUpdateProfileInMyInfoGraph() {
    this.navigate(Route.MY_INFO_UPDATE_PROFILE_ROUTER_NAME)
}

fun NavController.navigateMyInfoToUpdateInMyInfoGraph() {
    this.navigate(Route.MY_INFO_UPDATE_ROUTER_NAME)
}


fun NavController.navigateUpdateProfileToMyInfoInMyInfoGraph() {
    this.navigate(Route.MY_INFO_MY_INFO_ROUTER_NAME) {
        this.popUpTo(Route.MY_INFO_MY_INFO_ROUTER_NAME) {
            inclusive = true
        }
    }
}

fun NavController.navigateUpdateToMyInfoInMyInfoGraph() {
    this.navigate(Route.MY_INFO_MY_INFO_ROUTER_NAME) {
        this.popUpTo(Route.MY_INFO_MY_INFO_ROUTER_NAME) {
            inclusive = true
        }
    }
}


