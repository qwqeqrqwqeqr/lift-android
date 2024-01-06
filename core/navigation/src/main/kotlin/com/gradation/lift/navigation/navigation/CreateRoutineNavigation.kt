package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Route
import com.gradation.lift.navigation.Route.CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME
import com.gradation.lift.navigation.Route.CREATE_ROUTINE_PROFILE_PICTURE_ROUTER_NAME
import com.gradation.lift.navigation.Route.CREATE_ROUTINE_ROUTINE_ROUTER_NAME
import com.gradation.lift.navigation.Route.CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME
import com.gradation.lift.navigation.Route.HOME_GRAPH_NAME
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import com.gradation.lift.navigation.saved_state.setValueSavedStateHandle

fun NavController.navigateCreateRoutineGraphToRoutineDetailGraph() {
    if (this.currentBackStack.value.any { it.destination.route == Route.ROUTINE_DETAIL_ROUTINE_LIST_ROUTER_NAME }) {
        this.navigate(Route.ROUTINE_DETAIL_ROUTINE_LIST_ROUTER_NAME) {
            this.popUpTo(Route.ROUTINE_DETAIL_ROUTINE_LIST_ROUTER_NAME) {
                inclusive = true
            }
        }
    } else {
        this.navigate(HOME_GRAPH_NAME) {
            this.popUpTo(HOME_GRAPH_NAME) {
                inclusive = true
            }
        }
    }

}

fun NavController.navigateRoutineSetToFindWorkCategoryInCreateRoutineGraph() {
    this.navigate(CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME)
}

fun NavController.navigateFindWorkCategoryToRoutineSetInCreateRoutineGraph() {
    this.popBackStack()
}

fun NavController.navigateFindWorkCategoryToRoutineInCreateRoutineGraph(workCategoryId: Int) {
    this.setValueSavedStateHandle(
        SavedStateHandleKey.RoutineSet.CREATE_WORK_CATEGORY_ID_KEY,
        workCategoryId
    )
    this.navigate(CREATE_ROUTINE_ROUTINE_ROUTER_NAME)

}

fun NavController.navigateRoutineToFindWorkCategoryInCreateRoutineGraph() {
    this.popBackStack()
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
    this.popBackStack()
}












