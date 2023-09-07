package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Router.HOME_GRAPH_NAME
import com.gradation.lift.navigation.Router.UPDATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME
import com.gradation.lift.navigation.Router.UPDATE_ROUTINE_PROFILE_PICTURE_ROUTER_NAME
import com.gradation.lift.navigation.Router.UPDATE_ROUTINE_ROUTINE_ROUTER_NAME
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


fun NavController.navigateRoutineSelectionToRoutineSetInUpdateRoutineGraph() {
    this.navigate(UPDATE_ROUTINE_ROUTINE_SET_ROUTER_NAME)
}

fun NavController.navigateRoutineSetToRoutineSelectionInUpdateRoutineGraph() {
    this.navigate(UPDATE_ROUTINE_ROUTINE_SELECTION_ROUTER_NAME) {
        this.popUpTo(UPDATE_ROUTINE_ROUTINE_SET_ROUTER_NAME) {
            inclusive = true
        }
    }
}

fun NavController.navigateRoutineSetToProfilePictureInUpdateRoutineGraph() {
    this.navigate(UPDATE_ROUTINE_PROFILE_PICTURE_ROUTER_NAME)
}


fun NavController.navigateProfilePictureToRoutineSetInUpdateRoutineGraph() {
    this.navigate(UPDATE_ROUTINE_ROUTINE_SET_ROUTER_NAME) {
        this.popUpTo(UPDATE_ROUTINE_PROFILE_PICTURE_ROUTER_NAME) {
            inclusive = true
        }
    }
}

fun NavController.navigateRoutineSetToFindWorkCategoryInUpdateRoutineGraph() {
    this.navigate(UPDATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME)
}


fun NavController.navigateFindWorkCategoryToRoutineSetInUpdateRoutineGraph() {
    this.navigate(UPDATE_ROUTINE_ROUTINE_SET_ROUTER_NAME) {
        this.popUpTo(UPDATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME) {
            inclusive = true
        }
    }
}


fun NavController.navigateFindWorkCategoryToRoutineInUpdateRoutineGraph() {
    this.navigate(UPDATE_ROUTINE_ROUTINE_ROUTER_NAME)
}

fun NavController.navigateRoutineToFindWorkCategoryInUpdateRoutineGraph() {
    this.navigate(UPDATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME) {
        this.popUpTo(UPDATE_ROUTINE_ROUTINE_ROUTER_NAME) {
            inclusive = true
        }
    }
}


fun NavController.navigateRoutineToRoutineSetInUpdateRoutineGraph() {
    this.navigate(UPDATE_ROUTINE_ROUTINE_SET_ROUTER_NAME) {
        this.popUpTo(UPDATE_ROUTINE_ROUTINE_SET_ROUTER_NAME) {
            inclusive = true
        }
    }
}


