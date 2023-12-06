package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Router


fun NavController.navigateMyInfoGraphToLoginGraph() {
    this.navigate(Router.LOGIN_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(this@navigateMyInfoGraphToLoginGraph.graph.id) {
            inclusive = true
        }
    }
}


fun NavController.navigateMyInfoGraphToNotificationGraph() {
    this.navigate(Router.NOTIFICATION_GRAPH_NAME)
}

fun NavController.navigateMyInfoGraphToBadgeGraph() {
    this.navigate(Router.BADGE_GRAPH_NAME)
}


fun NavController.navigateMyInfoToUpdateProfileInMyInfoGraph() {
    this.navigate(Router.MY_INFO_UPDATE_PROFILE_ROUTER_NAME)
}

fun NavController.navigateMyInfoToUpdateInMyInfoGraph() {
    this.navigate(Router.MY_INFO_UPDATE_ROUTER_NAME)
}


fun NavController.navigateUpdateProfileToMyInfoInMyInfoGraph() {
    this.navigate(Router.MY_INFO_MY_INFO_ROUTER_NAME) {
        this.popUpTo(Router.MY_INFO_MY_INFO_ROUTER_NAME) {
            inclusive = true
        }
    }
}

fun NavController.navigateUpdateToMyInfoInMyInfoGraph() {
    this.navigate(Router.MY_INFO_MY_INFO_ROUTER_NAME) {
        this.popUpTo(Router.MY_INFO_MY_INFO_ROUTER_NAME) {
            inclusive = true
        }
    }
}

