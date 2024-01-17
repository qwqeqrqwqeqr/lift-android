package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Route.HOME_GRAPH_NAME
import com.gradation.lift.navigation.Route.LOGIN_GRAPH_NAME
import com.gradation.lift.navigation.Route.REGISTER_DETAIL_GENDER_ROUTER_NAME
import com.gradation.lift.navigation.Route.REGISTER_DETAIL_HEIGHT_WEIGHT_ROUTER_NAME
import com.gradation.lift.navigation.Route.REGISTER_DETAIL_NAME_ROUTER_NAME
import com.gradation.lift.navigation.Route.REGISTER_DETAIL_PROFILE_PICTURE_ROUTER_NAME


fun NavController.navigateRegisterDetailGraphToHomeGraph() {
    this.navigate(HOME_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(this@navigateRegisterDetailGraphToHomeGraph.graph.id) {
            inclusive = true
        }
    }
}



fun NavController.navigateResisterDetailGraphToLoginGraph(){
    this.navigate(LOGIN_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(this@navigateResisterDetailGraphToLoginGraph.graph.id) {
            inclusive = true
        }
    }
}



fun NavController.navigateToNameInRegisterDetailGraph() {
    navigate(REGISTER_DETAIL_NAME_ROUTER_NAME){
        launchSingleTop=true
        popUpTo(REGISTER_DETAIL_NAME_ROUTER_NAME) {
            inclusive = true
        }
    }
}

fun NavController.navigateToGenderInRegisterDetailGraph() {
    navigate(REGISTER_DETAIL_GENDER_ROUTER_NAME){
        launchSingleTop=true
        popUpTo(REGISTER_DETAIL_GENDER_ROUTER_NAME) {
            inclusive = true
        }
    }
}



fun NavController.navigateToHeightWeightInRegisterDetailGraph() {
    navigate(REGISTER_DETAIL_HEIGHT_WEIGHT_ROUTER_NAME){
        launchSingleTop=true
        popUpTo(REGISTER_DETAIL_HEIGHT_WEIGHT_ROUTER_NAME) {
            inclusive = true
        }
    }
}


fun NavController.navigateToProfilePictureInRegisterDetailGraph() {
    navigate(REGISTER_DETAIL_PROFILE_PICTURE_ROUTER_NAME){
        launchSingleTop=true
        popUpTo(REGISTER_DETAIL_PROFILE_PICTURE_ROUTER_NAME) {
            inclusive = true
        }
    }
}





