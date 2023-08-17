package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Router.LOGIN_GRAPH_NAME
import com.gradation.lift.navigation.Router.MAIN_GRAPH_NAME
import com.gradation.lift.navigation.Router.REGISTER_DETAIL_GENDER_ROUTER_NAME
import com.gradation.lift.navigation.Router.REGISTER_DETAIL_GRAPH_NAME
import com.gradation.lift.navigation.Router.REGISTER_DETAIL_HEIGHT_WEIGHT_ROUTER_NAME
import com.gradation.lift.navigation.Router.REGISTER_DETAIL_NAME_ROUTER_NAME
import com.gradation.lift.navigation.Router.REGISTER_DETAIL_PROFILE_PICTURE_ROUTER_NAME


fun NavController.navigateRegisterDetailGraphToHomeGraph() {
    this.navigate(MAIN_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(this@navigateRegisterDetailGraphToHomeGraph.graph.id) {
            inclusive = true
        }
    }
}

fun NavController.navigateLoginGraphToRegisterDetailGraph() {
    this.navigate(REGISTER_DETAIL_GRAPH_NAME) {
        popUpTo(LOGIN_GRAPH_NAME) {
            inclusive = true
        }
    }
}

fun NavController.navigateNameToGender() {
    this.navigate(REGISTER_DETAIL_GENDER_ROUTER_NAME)
}

fun NavController.navigateGenderToName() {
    this.navigate(REGISTER_DETAIL_NAME_ROUTER_NAME) {
        this.popUpTo(REGISTER_DETAIL_NAME_ROUTER_NAME) {
            inclusive = true
        }
    }
}

fun NavController.navigateGenderToHeightWeight() {
    this.navigate(REGISTER_DETAIL_HEIGHT_WEIGHT_ROUTER_NAME)
}

fun NavController.navigateHeightWeightToGender() {
    this.navigate(REGISTER_DETAIL_GENDER_ROUTER_NAME) {
        this.popUpTo(REGISTER_DETAIL_GENDER_ROUTER_NAME) {
            inclusive = true
        }
    }
}

fun NavController.navigateHeightWeightToProfilePicture() {
    this.navigate(REGISTER_DETAIL_PROFILE_PICTURE_ROUTER_NAME)
}

fun NavController.navigateProfilePictureToHeightWeight() {
    this.navigate(REGISTER_DETAIL_HEIGHT_WEIGHT_ROUTER_NAME) {
        this.popUpTo(REGISTER_DETAIL_HEIGHT_WEIGHT_ROUTER_NAME) {
            inclusive = true
        }
    }
}





