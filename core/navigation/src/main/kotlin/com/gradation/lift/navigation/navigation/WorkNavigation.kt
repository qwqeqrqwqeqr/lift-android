package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Route
import com.gradation.lift.navigation.Route.WORK_COMPLETE_DETAIL_ROUTER_NAME
import com.gradation.lift.navigation.Route.WORK_COMPLETE_ROUTER_NAME


fun NavController.navigateWorkGraphToHomeGraph() {
    this.navigate(Route.HOME_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(this@navigateWorkGraphToHomeGraph.graph.id) {
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

fun NavController.navigateCompleteToCompleteDetailInWorkGraph() {
    this.navigate(WORK_COMPLETE_DETAIL_ROUTER_NAME) {
        popUpTo(WORK_COMPLETE_DETAIL_ROUTER_NAME) {
            inclusive = true
        }
    }
}





