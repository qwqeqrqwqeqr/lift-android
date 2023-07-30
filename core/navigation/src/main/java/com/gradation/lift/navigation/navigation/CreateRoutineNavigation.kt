package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_GRAPH_NAME
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_PROFILE_ROUTER_NAME
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_ROUTER_NAME
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_ROUTINE_ROUTER_NAME


fun NavController.navigateCreateRoutineRootToFindWorkCategory() {
    this.navigate(CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME)
}

fun NavController.navigateCreateRoutineFindWorkCategoryToRoot() {
    this.navigate(CREATE_ROUTINE_ROUTER_NAME) {
        this.popUpTo(CREATE_ROUTINE_ROUTER_NAME) {
            inclusive = true
        }
    }
}

fun NavController.navigateCreateRoutineFindWorkCategoryToRoutine() {
    this.navigate(CREATE_ROUTINE_ROUTINE_ROUTER_NAME)
}

fun NavController.navigateCreateRoutineRoutineToFindWorkCategory() {
    this.navigate(CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME) {
        this.popUpTo(CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME) {
            inclusive = true
        }
    }
}

fun NavController.navigateCreateRoutineRoutineToRoot() {
    this.navigate(CREATE_ROUTINE_ROUTER_NAME) {
        this.popUpTo(CREATE_ROUTINE_ROUTER_NAME) {
            inclusive = true
        }
    }
}


fun NavController.navigateCreateRoutineRootToProfile() {
    this.navigate(CREATE_ROUTINE_PROFILE_ROUTER_NAME)
}

fun NavController.navigateCreateRoutineProfileToRoot() {
    this.navigate(CREATE_ROUTINE_ROUTER_NAME) {
        this.popUpTo(CREATE_ROUTINE_ROUTER_NAME) {
            inclusive = true
        }
    }
}


fun NavController.navigateHomeToCreateRoutine() {
    this.navigate(CREATE_ROUTINE_GRAPH_NAME)
}







