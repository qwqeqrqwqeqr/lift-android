package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey


fun NavController.navigateHomeToReadyWorkGraph() {
    this.navigate(Router.READY_WORK_GRAPH_ROUTER_NAME) {
    }
}


fun NavController.navigateToReadyWorkSelectionToChangeOrder() {
    this.navigate(Router.READY_WORK_CHANGE_ORDER_ROUTER_NAME) {
        restoreState = true
    }
}


fun NavController.navigateToReadyWorkChangeOrderToSelection() {
    this.popBackStack(
        route = Router.READY_WORK_SELECTION_ROUTER_NAME,
        inclusive = false,
        saveState = true
    )
}

