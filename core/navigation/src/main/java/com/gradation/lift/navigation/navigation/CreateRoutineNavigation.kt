package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_PROFILE_PICTURE_ROUTER_NAME
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_ROUTINE_ROUTER_NAME
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME
import com.gradation.lift.navigation.Router.HOME_GRAPH_NAME

fun NavController.navigateCreateRoutineGraphToHomeGraph() {
    this.navigate(HOME_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(this@navigateCreateRoutineGraphToHomeGraph.graph.id) {
            inclusive = true
        }
    }
}


fun NavController.navigateRoutineSetToFindWorkCategoryInCreateRoutineGraph() {
    this.navigate(CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME)
}

fun NavController.navigateFindWorkCategoryToRoutineSetInCreateRoutineGraph() {
    this.navigate(CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME) {
        this.popUpTo(CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME) {
            inclusive = true
        }
    }
}

fun NavController.navigateFindWorkCategoryToRoutineInCreateRoutineGraph() {
    this.navigate(CREATE_ROUTINE_ROUTINE_ROUTER_NAME)
}

fun NavController.navigateRoutineToFindWorkCategoryInCreateRoutineGraph() {
    this.navigate(CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME) {
        this.popUpTo(CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME) {
            inclusive = true
        }
    }
}

fun NavController.navigateRoutineToRoutineSetInCreateRoutineGraph() {
    this.navigate(CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME) {
        this.popUpTo(CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME) {
            inclusive = true
        }
    }
}

fun NavController.navigateRoutineSetToProfilePictureInCreateRoutineGraph() {
    this.navigate(CREATE_ROUTINE_PROFILE_PICTURE_ROUTER_NAME)
}

fun NavController.navigateProfilePictureToRoutineSetInCreateRoutineGraph() {
    this.navigate(CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME) {
        this.popUpTo(CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME) {
            inclusive = true
        }
    }
}












