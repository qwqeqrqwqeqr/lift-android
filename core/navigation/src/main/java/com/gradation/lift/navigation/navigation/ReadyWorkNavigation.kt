package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Router


fun NavController.navigateHomeToReadyWorkGraph() {
    this.navigate(Router.READY_WORK_GRAPH_ROUTER_NAME)
}

fun NavController.navigateToReadyWorkSelection() {
    this.navigate(Router.READY_WORK_SELECTION_ROUTER_NAME)
}

fun NavController.navigateToReadyWorkChangeOrder() {
    this.navigate(Router.READY_WORK_CHANGE_ORDER_ROUTER_NAME)
}