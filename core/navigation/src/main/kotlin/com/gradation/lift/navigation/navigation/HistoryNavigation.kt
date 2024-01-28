package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Route
import com.gradation.lift.navigation.Route.HISTORY_UPDATE_INFO_ROUTER_NAME
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.History.HISTORY_COMMENT_KEY
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.History.HISTORY_SCORE_KEY


fun NavController.navigateHistoryGraphToWorkReadyReadyRouter() {
    this.navigate(Route.WORK_READY_READY_ROUTER_NAME)
}

fun NavController.navigateHistoryGraphToWorkReadyRoutineSelectionRouter() {
    this.navigate(Route.WORK_READY_ROUTINE_SELECTION_ROUTER_NAME)
}


fun NavController.navigateHistoryToUpdateInfoInHistoryGraph(comment: String, score: Int) {
    this.navigate("${HISTORY_UPDATE_INFO_ROUTER_NAME}?${HISTORY_COMMENT_KEY}=$comment&${HISTORY_SCORE_KEY}=$score")
}

fun NavController.navigateUpdateInfoToHistoryInHistoryGraph() {
    this.navigate(Route.HISTORY_HISTORY_ROUTER_NAME) {
        popUpTo(Route.HISTORY_HISTORY_ROUTER_NAME) {
            inclusive = true
        }
    }
}
