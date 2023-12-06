package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Router.UPDATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME
import com.gradation.lift.navigation.Router.UPDATE_ROUTINE_PROFILE_PICTURE_ROUTER_NAME
import com.gradation.lift.navigation.Router.UPDATE_ROUTINE_ROUTINE_ROUTER_NAME
import com.gradation.lift.navigation.Router.UPDATE_ROUTINE_ROUTINE_SET_ROUTER_NAME


fun NavController.navigateRoutineSetToProfilePictureInUpdateRoutineGraph() {
    this.navigate(UPDATE_ROUTINE_PROFILE_PICTURE_ROUTER_NAME)
}


fun NavController.navigateProfilePictureToRoutineSetInUpdateRoutineGraph() {
    this.popBackStack()
}

fun NavController.navigateRoutineSetToFindWorkCategoryInUpdateRoutineGraph() {
    this.navigate(UPDATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME)
}


fun NavController.navigateFindWorkCategoryToRoutineSetInUpdateRoutineGraph() {
    this.popBackStack()
}


fun NavController.navigateFindWorkCategoryToRoutineInUpdateRoutineGraph() {
    this.navigate(UPDATE_ROUTINE_ROUTINE_ROUTER_NAME)
}

fun NavController.navigateRoutineToFindWorkCategoryInUpdateRoutineGraph() {
    this.popBackStack()
}


fun NavController.navigateRoutineToRoutineSetInUpdateRoutineGraph() {
    this.navigate(UPDATE_ROUTINE_ROUTINE_SET_ROUTER_NAME) {
        this.popUpTo(UPDATE_ROUTINE_ROUTINE_SET_ROUTER_NAME) {
            inclusive = true
        }
    }
}


