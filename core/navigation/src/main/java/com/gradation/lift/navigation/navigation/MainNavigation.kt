package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import com.gradation.lift.navigation.Router.HISTORY_ROUTER_NAME
import com.gradation.lift.navigation.Router.HOME_ROUTER_NAME
import com.gradation.lift.navigation.Router.LOGIN_GRAPH_ROUTER_NAME
import com.gradation.lift.navigation.Router.MY_INFO_ROUTER_NAME
import com.gradation.lift.navigation.Router.ROUTINE_ROUTER_NAME




fun NavController.navigateLoginToHome() {
    this.navigate(HOME_ROUTER_NAME){
        popUpTo(LOGIN_GRAPH_ROUTER_NAME)
    }
}


fun NavHostController.navigateToHome() {
    this.navigate(HOME_ROUTER_NAME){
        launchSingleTop=true
        popUpTo(currentDestination!!.id){
            saveState = true
            inclusive = true
        }
    }
}


fun NavHostController.navigateToRoutine() {
    this.navigate(ROUTINE_ROUTER_NAME){
        launchSingleTop=true
        popUpTo(currentDestination!!.id){
            saveState = true
            inclusive = true
        }
    }
}

fun NavHostController.navigateToHistory() {
    this.navigate(HISTORY_ROUTER_NAME){
        launchSingleTop=true
        popUpTo(currentDestination!!.id){
            saveState = true
            inclusive = true
        }
    }
}

fun NavHostController.navigateToMyInfo() {
    this.navigate(MY_INFO_ROUTER_NAME){
        launchSingleTop=true
        popUpTo(currentDestination!!.id){
            saveState = true
            inclusive = true
        }
    }
}






