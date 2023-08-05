package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.Router.HISTORY_ROUTER_NAME
import com.gradation.lift.navigation.Router.HOME_ROUTER_NAME
import com.gradation.lift.navigation.Router.MY_INFO_ROUTER_NAME


fun NavController.navigateLoginToHome() {
    this.navigate(Router.MAIN_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(this@navigateLoginToHome.graph.id) {
            inclusive = true
        }
    }
}

fun NavController.navigateRegisterDetailToHome() {
    this.navigate(Router.MAIN_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(this@navigateRegisterDetailToHome.graph.id) {
            inclusive = true
        }
    }
}


fun NavController.navigateWorkToMain() {
    this.navigate(Router.MAIN_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(this@navigateWorkToMain.graph.id) {
            inclusive = true
        }
    }
}


fun NavController.navigateCreateRoutineToMain() {
    this.navigate(Router.MAIN_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(this@navigateCreateRoutineToMain.graph.id) {
            inclusive = true
        }
    }
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






