package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Router.HOME_GRAPH_NAME
import com.gradation.lift.navigation.Router.UPDATE_ROUTINE_ROUTINE_ROUTER_NAME
import com.gradation.lift.navigation.Router.UPDATE_ROUTINE_ROUTINE_SELECTION_ROUTER_NAME

fun NavController.navigateUpdateRoutineGraphToHomeGraph() {
    this.navigate(HOME_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(this@navigateUpdateRoutineGraphToHomeGraph.graph.id) {
            inclusive = true
        }
    }
}


fun NavController.navigateRoutineSelectionToRoutine() {
    this.navigate(UPDATE_ROUTINE_ROUTINE_ROUTER_NAME)
}

fun NavController.navigateRoutineToRoutineSelection() {
    this.navigate(UPDATE_ROUTINE_ROUTINE_SELECTION_ROUTER_NAME) {
        this.popUpTo(UPDATE_ROUTINE_ROUTINE_ROUTER_NAME) {
            inclusive = true
        }
    }
}













