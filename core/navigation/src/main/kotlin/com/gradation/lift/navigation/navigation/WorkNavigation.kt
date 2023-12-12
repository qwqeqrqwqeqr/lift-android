package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.Router.WORK_COMPLETE_ROUTER_NAME
import com.gradation.lift.navigation.Router.WORK_WORK_ROUTER_NAME
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import com.gradation.lift.navigation.saved_state.setValueSavedStateHandle


fun NavController.navigateWorkGraphToHomeGraph() {
    this.navigate(Router.HOME_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(this@navigateWorkGraphToHomeGraph.graph.id) {
            inclusive = true
        }
    }
}

fun NavController.navigateRoutineSelectionToWorkInWorkGraph(routineSetIdList: IntArray) {
    this.setValueSavedStateHandle(
        SavedStateHandleKey.RoutineSet.WORK_ROUTINE_SET_LIST_ID_KEY,
        routineSetIdList
    )
    this.navigate(WORK_WORK_ROUTER_NAME) {
        this.popUpTo(WORK_WORK_ROUTER_NAME) {
            inclusive = true
        }
    }
}


fun NavController.navigateWorkToCompleteInWorkGraph() {
    this.navigate(WORK_COMPLETE_ROUTER_NAME) {
        popUpTo(WORK_COMPLETE_ROUTER_NAME) {
            inclusive = true
        }
    }
}









