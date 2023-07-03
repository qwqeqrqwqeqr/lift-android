package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Router


fun NavController.navigateToRegisterDetailComplete() {
    this.navigate(Router.REGISTER_DETAIL_COMPLETE_ROUTER_NAME)
}
fun NavController.navigateToRegisterDetailGender() {
    this.navigate(Router.REGISTER_DETAIL_GENDER_ROUTER_NAME)
}
fun NavController.navigateToRegisterDetailHwTw() {
    this.navigate(Router.REGISTER_DETAIL_HT_WT_ROUTER_NAME)
}
fun NavController.navigateToRegisterDetailName() {
    this.navigate(Router.REGISTER_DETAIL_NAME_ROUTER_NAME)
}
fun NavController.navigateToRegisterDetailUnitOfWeight() {
    this.navigate(Router.REGISTER_DETAIL_UNIT_OF_WEIGHT_ROUTER_NAME)
}
