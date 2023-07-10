package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.gradation.lift.navigation.Router.HISTORY_ROUTER_NAME
import com.gradation.lift.navigation.Router.HOME_ROUTER_NAME
import com.gradation.lift.navigation.Router.LOGIN_GRAPH_ROUTER_NAME
import com.gradation.lift.navigation.Router.MY_INFO_ROUTER_NAME
import com.gradation.lift.navigation.Router.REGISTER_DETAIL_GRAPH_ROUTER_NAME
import com.gradation.lift.navigation.Router.ROUTINE_ROUTER_NAME




fun NavController.navigateLoginToHome() {
    this.navigate(HOME_ROUTER_NAME){
        popUpTo(LOGIN_GRAPH_ROUTER_NAME){
            inclusive=true
        }
    }
}


fun NavController.navigateLoginToRegisterDetail() {
    this.navigate(REGISTER_DETAIL_GRAPH_ROUTER_NAME){

        popUpTo(LOGIN_GRAPH_ROUTER_NAME){
            inclusive=true
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






