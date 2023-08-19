package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.gradation.lift.navigation.Router

fun NavController.navigateNotificationGraphToHomeGraph() {
    this.navigate(Router.HOME_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(this@navigateNotificationGraphToHomeGraph.graph.id) {
            inclusive = true
        }
    }
}

fun NavController.navigateNotificationGraphToMyInfoGraph() {
    this.navigate(Router.MY_INFO_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(this@navigateNotificationGraphToMyInfoGraph.graph.id) {
            inclusive = true
        }
    }
}



fun NavHostController.navigateNotificationToNotice() {
    this.navigate(Router.NOTIFICATION_NOTICE_ROUTER_NAME) {
        launchSingleTop = true

        popUpTo(currentDestination!!.id) {
            saveState = true
            inclusive = true
        }
    }
}

fun NavHostController.navigateNoticeToNotification() {
    this.navigate(Router.NOTIFICATION_NOTIFICATION_ROUTER_NAME) {
        launchSingleTop = true

        popUpTo(currentDestination!!.id) {
            saveState = true
            inclusive = true
        }
    }
}

