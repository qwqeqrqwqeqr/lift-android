package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.Router.READY_WORK_GRAPH_NAME
import com.gradation.lift.navigation.Router.WORK_COMPLETE_ROUTER_NAME
import com.gradation.lift.navigation.Router.WORK_WORK_ROUTER_NAME

fun NavController.navigateReadyWorkToWorkGraph() {
    this.navigate(Router.WORK_GRAPH_NAME){
        popUpTo(READY_WORK_GRAPH_NAME){
            inclusive=true
        }
    }
}
fun NavController.navigateWorkWorkToComplete() {
    this.navigate(WORK_COMPLETE_ROUTER_NAME){
        popUpTo(WORK_COMPLETE_ROUTER_NAME){
            inclusive=true
        }
    }
}