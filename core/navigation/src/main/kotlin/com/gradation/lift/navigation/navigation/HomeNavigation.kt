package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.gradation.lift.navigation.Route
import com.gradation.lift.navigation.Route.HISTORY_GRAPH_NAME
import com.gradation.lift.navigation.Route.HOME_GRAPH_NAME
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.RoutineSet.DETAIL_ROUTINE_SET_ID_KEY
import com.gradation.lift.navigation.saved_state.setValueSavedStateHandle


fun NavController.navigateHomeGraphToWorkGraph() {
    this.navigate(Route.WORK_GRAPH_NAME)
}

fun NavController.navigateHomeGraphToCreateRoutineGraph() {
    this.navigate(Route.CREATE_ROUTINE_GRAPH_NAME)
}

fun NavController.navigateHomeGraphToRoutineDetailGraph() {
    this.navigate(Route.ROUTINE_DETAIL_GRAPH_NAME)
}
fun NavController.navigateHomeGraphToRoutineDetailRoutineRouter(routineSetId: Int) {
    this.setValueSavedStateHandle(
        DETAIL_ROUTINE_SET_ID_KEY,
        routineSetId
    )
    this.navigate(Route.ROUTINE_DETAIL_ROUTINE_ROUTER_NAME)
}

fun NavController.navigateHomeGraphToBadgeGraph() {
    this.navigate(Route.BADGE_GRAPH_NAME)
}

fun NavController.navigateHomeGraphToBadgeSettingRouter() {
    this.navigate(Route.BADGE_SETTING_ROUTER_NAME)
}

fun NavController.navigateHomeGraphToNewBadgeGraph() {
    this.navigate(Route.NEW_BADGE_GRAPH_NAME)
}

fun NavController.navigateHomeGraphToNotificationGraph() {
    this.navigate(Route.NOTIFICATION_GRAPH_NAME)
}



fun NavHostController.navigateHomeGraph() {
    this.navigate(HOME_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(currentDestination!!.id) {
            saveState = true
            inclusive = true
        }
    }
}


fun NavController.navigateMyInfoGraph() {
    this.navigate(Route.MY_INFO_GRAPH_NAME) {
        launchSingleTop = true
    }
}

fun NavHostController.navigateHistoryGraph() {
    this.navigate(HISTORY_GRAPH_NAME) {
        launchSingleTop = true
    }
}





