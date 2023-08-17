package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.Router.HISTORY_ROUTER_NAME
import com.gradation.lift.navigation.Router.HOME_ROUTER_NAME
import com.gradation.lift.navigation.Router.MY_INFO_ROUTER_NAME


fun NavController.navigateMainGraphToWorkGraph() {
    this.navigate(Router.WORK_GRAPH_NAME)
}

fun NavController.navigateMainGraphToCreateRoutineGraph() {
    this.navigate(Router.CREATE_ROUTINE_GRAPH_NAME)
}



fun NavHostController.navigateToHome() {
    this.navigate(HOME_ROUTER_NAME) {
        launchSingleTop = true
        popUpTo(currentDestination!!.id) {
            saveState = true
            inclusive = true
        }
    }
}

fun NavHostController.navigateToHistory() {
    this.navigate(HISTORY_ROUTER_NAME) {
        launchSingleTop = true

        popUpTo(currentDestination!!.id) {
            saveState = true
            inclusive = true
        }
    }
}

fun NavHostController.navigateToMyInfo() {
    this.navigate(MY_INFO_ROUTER_NAME) {
        launchSingleTop = true
        popUpTo(currentDestination!!.id) {
            saveState = true
            inclusive = true
        }
    }
}






