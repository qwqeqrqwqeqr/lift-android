package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Route
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey


fun NavController.navigateAnalyticsGraphToWorkReadyReadyRouter() {
    val emptyString = ""
    this.navigate("${Route.WORK_READY_READY_ROUTER_NAME}?${SavedStateHandleKey.Work.WORK_ROUTINE_SET_ID_LIST_KEY}=$emptyString")
}

fun NavController.navigateAnalyticsGraphToWorkReadyRoutineSelectionRouter() {
    this.navigate(Route.WORK_READY_ROUTINE_SELECTION_ROUTER_NAME)
}
