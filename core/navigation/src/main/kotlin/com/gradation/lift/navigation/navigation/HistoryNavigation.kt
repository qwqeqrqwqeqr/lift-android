package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Route


fun NavController.navigateHistoryGraphToWorkGraph() {
    this.navigate(Route.WORK_GRAPH_NAME)
}
