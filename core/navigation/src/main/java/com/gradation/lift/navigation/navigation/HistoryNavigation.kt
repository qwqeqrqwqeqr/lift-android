package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Router


fun NavController.navigateHistoryGraphToWorkGraph() {
    this.navigate(Router.WORK_GRAPH_NAME)
}
