package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Route.HOME_GRAPH_NAME
import com.gradation.lift.navigation.Route.ROUTINE_DETAIL_ROUTINE_LIST_ROUTER_NAME
import com.gradation.lift.navigation.Route.ROUTINE_DETAIL_ROUTINE_ROUTER_NAME
import com.gradation.lift.navigation.Route.UPDATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME
import com.gradation.lift.navigation.Route.UPDATE_ROUTINE_PROFILE_PICTURE_ROUTER_NAME
import com.gradation.lift.navigation.Route.UPDATE_ROUTINE_CREATE_WORK_SET_ROUTER_NAME
import com.gradation.lift.navigation.Route.UPDATE_ROUTINE_ROUTINE_SET_ROUTER_NAME
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import com.gradation.lift.navigation.saved_state.setValueSavedStateHandle


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


fun NavController.navigateFindWorkCategoryToCreateWorkSetInUpdateRoutineGraph(workCategoryId: Int) {
    this.navigate("${UPDATE_ROUTINE_CREATE_WORK_SET_ROUTER_NAME}/${workCategoryId}")
}

fun NavController.navigateCreateWorkSetRouteToFindWorkCategoryInUpdateRoutineGraph() {
    this.popBackStack()
}


fun NavController.navigateCreateWorkSetToRoutineSetInUpdateRoutineGraph() {
    this.navigate(UPDATE_ROUTINE_ROUTINE_SET_ROUTER_NAME) {
        this.popUpTo(UPDATE_ROUTINE_ROUTINE_SET_ROUTER_NAME) {
            inclusive = true
        }
    }
}

fun NavController.navigateUpdateRoutineGraphToRoutineDetailGraph() {

    if (this.currentBackStack.value.any { it.destination.route == ROUTINE_DETAIL_ROUTINE_LIST_ROUTER_NAME }) {
        this.navigate(ROUTINE_DETAIL_ROUTINE_LIST_ROUTER_NAME) {
            this.popUpTo(ROUTINE_DETAIL_ROUTINE_LIST_ROUTER_NAME) {
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

fun NavController.navigateUpdateRoutineRoutineSetRouterToRoutineDetailRoutineRouter(routineSetId: Int) {
    this.setValueSavedStateHandle(
        SavedStateHandleKey.RoutineSet.DETAIL_ROUTINE_SET_ID_KEY,
        routineSetId
    )
    this.navigate(ROUTINE_DETAIL_ROUTINE_ROUTER_NAME) {
        this.popUpTo(ROUTINE_DETAIL_ROUTINE_ROUTER_NAME) {
            inclusive = true
        }
    }
}

