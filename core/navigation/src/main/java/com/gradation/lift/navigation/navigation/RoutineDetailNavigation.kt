package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import com.gradation.lift.navigation.saved_state.setValueSavedStateHandle


fun NavController.navigateRoutineDetailGraphToHomeGraph() {
    this.navigate(Router.HOME_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(this@navigateRoutineDetailGraphToHomeGraph.graph.id) {
            inclusive = true
        }
    }
}

fun NavController.navigateRoutineDetailGraphToCreateRoutineGraph() {
    this.navigate(Router.CREATE_ROUTINE_GRAPH_NAME)
}


fun NavController.navigateRoutineDetailGraphToUpdateRoutineGraph(routineSetId: Int) {
    this.setValueSavedStateHandle(
        SavedStateHandleKey.RoutineSet.UPDATE_ROUTINE_SET_ID_KEY,
        routineSetId
    )
    this.navigate(Router.UPDATE_ROUTINE_GRAPH_NAME)
}

fun NavController.navigateRoutineDetailGraphToWorkWorkRouter(routineSetId: Int) {
    this.setValueSavedStateHandle(
        SavedStateHandleKey.RoutineSet.WORK_ROUTINE_SET_ID_KEY,
        routineSetId
    )
    this.navigate(Router.WORK_WORK_ROUTER_NAME)
}


fun NavController.navigateRoutineListToRoutineInRoutineDetailGraph(routineSetId: Int) {
    this.setValueSavedStateHandle(
        SavedStateHandleKey.RoutineSet.DETAIL_ROUTINE_SET_ID_KEY,
        routineSetId
    )
    this.navigate(Router.ROUTINE_DETAIL_ROUTINE_ROUTER_NAME)
}

