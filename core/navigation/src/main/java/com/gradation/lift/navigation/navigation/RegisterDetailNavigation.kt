package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Router


fun NavController.navigateRegisterDetailNameToGender() {
    this.navigate(Router.REGISTER_DETAIL_GENDER_ROUTER_NAME) {
        this.restoreState = true
    }
}
fun NavController.navigateRegisterDetailGenderToName() {
    this.popBackStack(
        route = Router.REGISTER_DETAIL_NAME_ROUTER_NAME,
        inclusive = false,
        saveState = true
    )
}



fun NavController.navigateRegisterDetailGenderToHeightWeight() {
    this.navigate(Router.REGISTER_DETAIL_HEIGHT_WEIGHT_ROUTER_NAME) {
        this.restoreState = true
    }
}
fun NavController.navigateRegisterDetailHeightWeightToGender() {
    this.popBackStack(
        route = Router.REGISTER_DETAIL_GENDER_ROUTER_NAME,
        inclusive = false,
        saveState = true
    )
}


fun NavController.navigateRegisterDetailHeightWeightToUnitOfWeight() {
    this.navigate(Router.REGISTER_DETAIL_UNIT_OF_WEIGHT_ROUTER_NAME) {
        this.restoreState = true
    }
}
fun NavController.navigateRegisterDetailUnitOfWeightToHeightWeight() {
    this.popBackStack(
        route = Router.REGISTER_DETAIL_HEIGHT_WEIGHT_ROUTER_NAME,
        inclusive = false,
        saveState = true
    )
}

fun NavController.navigateRegisterDetailUnitOfWeightToProfilePicture() {
    this.navigate(Router.REGISTER_DETAIL_PROFILE_PICTURE_ROUTER_NAME) {
        this.restoreState = true
    }
}

fun NavController.navigateRegisterDetailProfilePictureToUnitOfWeight() {
    this.popBackStack(
        route = Router.REGISTER_DETAIL_UNIT_OF_WEIGHT_ROUTER_NAME,
        inclusive = false,
        saveState = true
    )
}


fun NavController.navigateLoginToRegisterDetail() {
    this.navigate(Router.REGISTER_DETAIL_GRAPH_ROUTER_NAME) {
        popUpTo(Router.LOGIN_GRAPH_ROUTER_NAME) {
            inclusive = true
        }
    }
}
