package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.Router.HISTORY_ROUTER_NAME
import com.gradation.lift.navigation.Router.HOME_ROUTER_NAME
import com.gradation.lift.navigation.Router.MY_INFO_ROUTER_NAME



fun NavController.navigateLoginToHome() {
    this.navigate(Router.MAIN_GRAPH_ROUTER_NAME){
        launchSingleTop=true
        popUpTo(Router.LOGIN_GRAPH_ROUTER_NAME){
            inclusive=true
        }
    }
}

fun NavController.navigateRegisterDetailToHome() {
    this.navigate(Router.MAIN_GRAPH_ROUTER_NAME){
        launchSingleTop=true
        popUpTo(Router.REGISTER_DETAIL_NAME_ROUTER_NAME){
            inclusive=true
        }
    }
}

fun NavController.navigateReadyWorkToMain() {
    this.navigate(Router.MAIN_GRAPH_ROUTER_NAME){
        launchSingleTop=true
        popUpTo(Router.READY_WORK_GRAPH_ROUTER_NAME){
            inclusive=true
            saveState=true
        }
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






