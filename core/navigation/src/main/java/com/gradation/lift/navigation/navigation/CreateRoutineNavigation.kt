package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_PROFILE_ROUTER_NAME
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_ROUTINE_ROUTER_NAME
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME
import com.gradation.lift.navigation.Router.MAIN_GRAPH_NAME

fun NavController.navigateCreateRoutineGraphToMainGraph() {
    this.navigate(MAIN_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(this@navigateCreateRoutineGraphToMainGraph.graph.id) {
            inclusive = true
        }
    }
}


fun NavController.navigateRoutineSetToFindWorkCategory() {
    this.navigate(CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME)
}

fun NavController.navigateFindWorkCategoryToRoutineSet() {
    this.navigate(CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME) {
        this.popUpTo(CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME) {
            inclusive = true
        }
    }
}

fun NavController.navigateFindWorkCategoryToRoutine() {
    this.navigate(CREATE_ROUTINE_ROUTINE_ROUTER_NAME)
}

fun NavController.navigateRoutineToFindWorkCategory() {
    this.navigate(CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME) {
        this.popUpTo(CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME) {
            inclusive = true
        }
    }
}

fun NavController.navigateRoutineToRoutineSet() {
    this.navigate(CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME) {
        this.popUpTo(CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME) {
            inclusive = true
        }
    }
}

fun NavController.navigateRoutineSetToProfile() {
    this.navigate(CREATE_ROUTINE_PROFILE_ROUTER_NAME)
}

fun NavController.navigateProfileToRoutineSet() {
    this.navigate(CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME) {
        this.popUpTo(CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME) {
            inclusive = true
        }
    }
}












