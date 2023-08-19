package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.Router.HISTORY_GRAPH_NAME
import com.gradation.lift.navigation.Router.MY_INFO_GRAPH_NAME


fun NavController.navigateHomeGraphToWorkGraph() {
    this.navigate(Router.WORK_GRAPH_NAME)
}

fun NavController.navigateHomeGraphToCreateRoutineGraph() {
    this.navigate(Router.CREATE_ROUTINE_GRAPH_NAME)
}

fun NavController.navigateHomeGraphToBadgeGraph() {
    this.navigate(Router.BADGE_GRAPH_NAME)
}

fun NavController.navigateHomeGraphToNotificationGraph() {
    this.navigate(Router.NOTIFICATION_GRAPH_NAME)
}



fun NavHostController.navigateHomeGraphToHistoryGraph() {
    this.navigate(HISTORY_GRAPH_NAME) {
        launchSingleTop = true

        popUpTo(currentDestination!!.id) {
            saveState = true
            inclusive = true
        }
    }
}

fun NavHostController.navigateHomeGraphToMyInfoGraph() {
    this.navigate(MY_INFO_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(currentDestination!!.id) {
            saveState = true
            inclusive = true
        }
    }
}






