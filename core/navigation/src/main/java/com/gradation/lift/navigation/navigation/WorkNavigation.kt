package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Router

fun NavController.navigateReadyWorkToWorkGraph() {
    this.navigate(Router.WORK_GRAPH_NAME){
        launchSingleTop=true
        popUpTo(Router.READY_WORK_GRAPH_NAME){
            inclusive=true
        }
    }
}