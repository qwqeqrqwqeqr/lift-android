package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.Router.BADGE_BADGE_ROUTER_NAME
import com.gradation.lift.navigation.Router.BADGE_SETTING_ROUTER_NAME


fun NavController.navigateBadgeGraphToPreGraph(){
    this.popBackStack()
}


fun NavController.navigateBadgeToSettingInBadgeGraph() {
    this.navigate(BADGE_SETTING_ROUTER_NAME)
}

fun NavController.navigateSettingToBadgeInBadgeGraph() {
    this.navigate(BADGE_BADGE_ROUTER_NAME) {
        this.popUpTo(BADGE_BADGE_ROUTER_NAME)
    }
}



fun NavController.navigateNewBadgeGraphToHomeGraph() {
    this.navigate(Router.HOME_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(this@navigateNewBadgeGraphToHomeGraph.graph.id) {
            inclusive = true
        }
    }
}
