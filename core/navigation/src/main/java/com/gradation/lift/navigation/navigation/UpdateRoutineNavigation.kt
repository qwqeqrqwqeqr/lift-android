package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Router.HOME_GRAPH_NAME
import com.gradation.lift.navigation.Router.UPDATE_ROUTINE_PROFILE_PICTURE_ROUTER_NAME
import com.gradation.lift.navigation.Router.UPDATE_ROUTINE_ROUTINE_SELECTION_ROUTER_NAME
import com.gradation.lift.navigation.Router.UPDATE_ROUTINE_ROUTINE_SET_ROUTER_NAME

fun NavController.navigateUpdateRoutineGraphToHomeGraph() {
    this.navigate(HOME_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(this@navigateUpdateRoutineGraphToHomeGraph.graph.id) {
            inclusive = true
        }
    }
}


fun NavController.navigateRoutineSelectionToRoutineSet() {
    this.navigate(UPDATE_ROUTINE_ROUTINE_SET_ROUTER_NAME)
}

fun NavController.navigateRoutineSetToRoutineSelection() {
    this.navigate(UPDATE_ROUTINE_ROUTINE_SELECTION_ROUTER_NAME) {
        this.popUpTo(UPDATE_ROUTINE_ROUTINE_SET_ROUTER_NAME) {
            inclusive = true
        }
    }
}

fun NavController.navigateRoutineSetToProfilePicture() {
    this.navigate(UPDATE_ROUTINE_PROFILE_PICTURE_ROUTER_NAME)
}


fun NavController.navigateProfilePictureToRoutineSet() {
    this.navigate(UPDATE_ROUTINE_ROUTINE_SET_ROUTER_NAME) {
        this.popUpTo(UPDATE_ROUTINE_PROFILE_PICTURE_ROUTER_NAME) {
            inclusive = true
        }
    }
}





