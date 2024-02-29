package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Route


fun NavController.navigateBadgeGraphToHomeGraph(){
    this.navigate(Route.HOME_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(this@navigateBadgeGraphToHomeGraph.graph.id) {
            inclusive = true
        }
    }
}



