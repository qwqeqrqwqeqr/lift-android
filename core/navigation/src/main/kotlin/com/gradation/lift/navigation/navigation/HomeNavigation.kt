package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.Router.HISTORY_GRAPH_NAME
import com.gradation.lift.navigation.Router.HOME_GRAPH_NAME
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.RoutineSet.DETAIL_ROUTINE_SET_ID_KEY
import com.gradation.lift.navigation.saved_state.setValueSavedStateHandle


fun NavController.navigateHomeGraphToWorkGraph() {
    this.navigate(Router.WORK_GRAPH_NAME)
}

fun NavController.navigateHomeGraphToCreateRoutineGraph() {
    this.navigate(Router.CREATE_ROUTINE_GRAPH_NAME)
}

fun NavController.navigateHomeGraphToRoutineDetailGraph() {
    this.navigate(Router.ROUTINE_DETAIL_GRAPH_NAME)
}
fun NavController.navigateHomeGraphToRoutineDetailRoutineRouter(routineSetId: Int) {
    this.setValueSavedStateHandle(
        DETAIL_ROUTINE_SET_ID_KEY,
        routineSetId
    )
    this.navigate(Router.ROUTINE_DETAIL_ROUTINE_ROUTER_NAME)
}

fun NavController.navigateHomeGraphToBadgeGraph() {
    this.navigate(Router.BADGE_GRAPH_NAME)
}

fun NavController.navigateHomeGraphToBadgeSettingRouter() {
    this.navigate(Router.BADGE_SETTING_ROUTER_NAME)
}

fun NavController.navigateHomeGraphToNewBadgeGraph() {
    this.navigate(Router.NEW_BADGE_GRAPH_NAME)
}

fun NavController.navigateHomeGraphToNotificationGraph() {
    this.navigate(Router.NOTIFICATION_GRAPH_NAME)
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
    this.navigate(Router.MY_INFO_GRAPH_NAME) {
        launchSingleTop = true
    }
}

fun NavHostController.navigateHistoryGraph() {
    this.navigate(HISTORY_GRAPH_NAME) {
        launchSingleTop = true
    }
}





