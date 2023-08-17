package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.Router.WORK_COMPLETE_ROUTER_NAME
import com.gradation.lift.navigation.Router.WORK_ROUTINE_SELECTION_ROUTER_NAME
import com.gradation.lift.navigation.Router.WORK_WORK_ROUTER_NAME


fun NavController.navigateWorkGraphToMainGraph() {
    this.navigate(Router.MAIN_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(this@navigateWorkGraphToMainGraph.graph.id) {
            inclusive = true
        }
    }
}

fun NavController.navigateSelectionRoutineToWork() {
    this.navigate(WORK_WORK_ROUTER_NAME) {
        this.popUpTo(WORK_ROUTINE_SELECTION_ROUTER_NAME) {
            inclusive = true
        }
    }
}

fun NavController.navigateWorkToComplete() {
    this.navigate(WORK_COMPLETE_ROUTER_NAME) {
        popUpTo(WORK_COMPLETE_ROUTER_NAME) {
            inclusive = true
        }
    }
}









