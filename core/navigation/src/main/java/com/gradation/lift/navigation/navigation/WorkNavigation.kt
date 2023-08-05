package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Router.WORK_CHANGE_ORDER_ROUTER_NAME
import com.gradation.lift.navigation.Router.WORK_COMPLETE_ROUTER_NAME
import com.gradation.lift.navigation.Router.WORK_GRAPH_NAME
import com.gradation.lift.navigation.Router.WORK_ROUTINE_SELECTION_ROUTER_NAME
import com.gradation.lift.navigation.Router.WORK_WORK_ROUTER_NAME


/**
 * Home  <->  Routine Selection
 */
fun NavController.navigateHomeToWorkGraph() {
    this.navigate(WORK_GRAPH_NAME)
}


/**
 * Routine Selection -> Work
 */
fun NavController.navigateSelectionRoutineToWork() {
    this.navigate(WORK_WORK_ROUTER_NAME) {
        this.popUpTo(WORK_ROUTINE_SELECTION_ROUTER_NAME) {
            inclusive = true
        }
    }
}



/**
 * Work -> Complete
 */
fun NavController.navigateWorkWorkToComplete() {
    this.navigate(WORK_COMPLETE_ROUTER_NAME) {
        popUpTo(WORK_COMPLETE_ROUTER_NAME) {
            inclusive = true
        }
    }
}









