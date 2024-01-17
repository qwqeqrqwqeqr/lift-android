package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Route
import com.gradation.lift.navigation.Route.CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME
import com.gradation.lift.navigation.Route.CREATE_ROUTINE_PROFILE_PICTURE_ROUTER_NAME
import com.gradation.lift.navigation.Route.CREATE_ROUTINE_CREATE_WORK_SET_ROUTER_NAME
import com.gradation.lift.navigation.Route.CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME
import com.gradation.lift.navigation.Route.CREATE_ROUTINE_UPDATE_WORK_SET_ROUTER_NAME
import com.gradation.lift.navigation.Route.HOME_GRAPH_NAME

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

fun NavController.navigateFindWorkCategoryToCreateWorkSetInCreateRoutineGraph(workCategoryId: Int) {
    this.navigate("$CREATE_ROUTINE_CREATE_WORK_SET_ROUTER_NAME/${workCategoryId}")
}

fun NavController.navigateCreateWorkSetToFindWorkCategoryInCreateRoutineGraph() {
    this.popBackStack()
}

fun NavController.navigateRoutineSetToChangeOrderInCreateRoutineGraph() {
    this.navigate(Route.CREATE_ROUTINE_CHANGE_ORDER_ROUTER_NAME)
}

fun NavController.navigateChangeOrderToRoutineSetInCreateRoutineGraph() {
    this.popBackStack()
}



fun NavController.navigateCreateWorkSetToRoutineSetInCreateRoutineGraph() {
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


fun NavController.navigateRoutineSetToUpdateWorkSetInCreateRoutineGraph(routineIndex: Int) {
    this.navigate("$CREATE_ROUTINE_UPDATE_WORK_SET_ROUTER_NAME/${routineIndex}")
}

fun NavController.navigateUpdateWorkSetToRoutineSetInCreateRoutineGraph() {
    this.popBackStack()
}











