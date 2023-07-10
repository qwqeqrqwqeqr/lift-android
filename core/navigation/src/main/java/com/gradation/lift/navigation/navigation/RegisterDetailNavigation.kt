package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Router



fun NavController.navigateToRegisterDetailGender() {
    this.navigate(Router.REGISTER_DETAIL_GENDER_ROUTER_NAME){
        this.restoreState=true
    }
}
fun NavController.navigateToRegisterDetailHeightWeight() {
    this.navigate(Router.REGISTER_DETAIL_HEIGHT_WEIGHT_ROUTER_NAME){
        this.restoreState=true
    }
}
fun NavController.navigateToRegisterDetailName() {
    this.navigate(Router.REGISTER_DETAIL_NAME_ROUTER_NAME){
        this.restoreState=true
    }
}
fun NavController.navigateToRegisterDetailUnitOfWeight() {
    this.navigate(Router.REGISTER_DETAIL_UNIT_OF_WEIGHT_ROUTER_NAME){
        this.restoreState=true
    }
}
fun NavController.navigateToRegisterDetailGraph() {
    this.navigate(Router.REGISTER_DETAIL_GRAPH_ROUTER_NAME) {
    }
}


fun NavController.navigateLoginToRegisterDetail() {
    this.navigate(Router.REGISTER_DETAIL_GRAPH_ROUTER_NAME){
        popUpTo(Router.LOGIN_GRAPH_ROUTER_NAME){
            inclusive=true
        }
    }
}
