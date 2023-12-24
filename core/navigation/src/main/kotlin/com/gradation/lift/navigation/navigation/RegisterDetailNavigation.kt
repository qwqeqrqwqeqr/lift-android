package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Router.HOME_GRAPH_NAME
import com.gradation.lift.navigation.Router.REGISTER_DETAIL_GENDER_ROUTER_NAME
import com.gradation.lift.navigation.Router.REGISTER_DETAIL_HEIGHT_WEIGHT_ROUTER_NAME
import com.gradation.lift.navigation.Router.REGISTER_DETAIL_NAME_ROUTER_NAME
import com.gradation.lift.navigation.Router.REGISTER_DETAIL_PROFILE_PICTURE_ROUTER_NAME


fun NavController.navigateRegisterDetailGraphToHomeGraph() {
    this.navigate(HOME_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(this@navigateRegisterDetailGraphToHomeGraph.graph.id) {
            inclusive = true
        }
    }
}



fun NavController.navigateNameToGenderInRegisterDetailGraph() {
    this.navigate(REGISTER_DETAIL_GENDER_ROUTER_NAME)
}

fun NavController.navigateGenderToNameInRegisterDetailGraph() {
    this.navigate(REGISTER_DETAIL_NAME_ROUTER_NAME) {
        this.popUpTo(REGISTER_DETAIL_NAME_ROUTER_NAME) {
            inclusive = true
        }
    }
}

fun NavController.navigateGenderToHeightWeightInRegisterDetailGraph() {
    this.navigate(REGISTER_DETAIL_HEIGHT_WEIGHT_ROUTER_NAME)
}

fun NavController.navigateHeightWeightToGenderInRegisterDetailGraph() {
    this.navigate(REGISTER_DETAIL_GENDER_ROUTER_NAME) {
        this.popUpTo(REGISTER_DETAIL_GENDER_ROUTER_NAME) {
            inclusive = true
        }
    }
}

fun NavController.navigateHeightWeightToProfilePictureInRegisterDetailGraph() {
    this.navigate(REGISTER_DETAIL_PROFILE_PICTURE_ROUTER_NAME)
}

fun NavController.navigateProfilePictureToHeightWeightInRegisterDetailGraph() {
    this.navigate(REGISTER_DETAIL_HEIGHT_WEIGHT_ROUTER_NAME) {
        this.popUpTo(REGISTER_DETAIL_HEIGHT_WEIGHT_ROUTER_NAME) {
            inclusive = true
        }
    }
}





