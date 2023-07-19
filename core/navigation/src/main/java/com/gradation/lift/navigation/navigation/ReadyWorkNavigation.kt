package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey


fun NavController.navigateHomeToReadyWorkGraph(routineSetId: Int?) {
    this.currentBackStackEntry
    this.navigate("${Router.READY_WORK_GRAPH_ROUTER_NAME}?${SavedStateHandleKey.WorkKey.ROUTINE_SET_ID_KEY}=${routineSetId}"){
        launchSingleTop=true
    }
}


fun NavController.navigateToReadyWorkChangeOrder() {
    this.navigate(Router.READY_WORK_CHANGE_ORDER_ROUTER_NAME){
        restoreState =true
    }
}

fun NavController.navigateToReadyWorkSelection() {
    this.navigate(Router.READY_WORK_SELECTION_ROUTER_NAME){
        popUpTo(Router.READY_WORK_CHANGE_ORDER_ROUTER_NAME){
            this.inclusive=true
        }
    }
}