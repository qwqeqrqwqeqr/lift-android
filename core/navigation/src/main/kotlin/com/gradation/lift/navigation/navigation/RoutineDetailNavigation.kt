package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Route
import com.gradation.lift.navigation.Route.WORK_READY_READY_ROUTER_NAME
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import com.gradation.lift.navigation.saved_state.setValueSavedStateHandle


fun NavController.navigateRoutineDetailGraphToHomeGraph() {
    this.navigate(Route.HOME_GRAPH_NAME) {
        popUpTo(this@navigateRoutineDetailGraphToHomeGraph.graph.id) {
            inclusive = true
        }
    }
}

fun NavController.navigateRoutineDetailGraphToBack(){
    if (this.currentBackStack.value.any { it.destination.route == Route.ROUTINE_DETAIL_ROUTINE_LIST_ROUTER_NAME }) {
        this.navigate(Route.ROUTINE_DETAIL_ROUTINE_LIST_ROUTER_NAME) {
            this.popUpTo(Route.ROUTINE_DETAIL_ROUTINE_LIST_ROUTER_NAME) {
                inclusive = true
            }
        }
    } else {
        this.navigate(Route.HOME_GRAPH_NAME) {
            this.popUpTo(Route.HOME_GRAPH_NAME) {
                inclusive = true
            }
        }
    }
}

fun NavController.navigateRoutineDetailGraphToCreateRoutineGraph() {
    this.navigate(Route.CREATE_ROUTINE_GRAPH_NAME)
}


fun NavController.navigateRoutineDetailGraphToUpdateRoutineGraph(routineSetId: Int) {
    this.setValueSavedStateHandle(
        SavedStateHandleKey.RoutineSet.UPDATE_ROUTINE_SET_ID_KEY,
        routineSetId
    )
    this.navigate(Route.UPDATE_ROUTINE_GRAPH_NAME)
}

fun NavController.navigateRoutineDetailGraphToWorkReadyReadyRoute(routineSetIdList: String) {
    this.setValueSavedStateHandle(
        SavedStateHandleKey.Work.WORK_ROUTINE_SET_ID_LIST_KEY,
        routineSetIdList
    )
    this.navigate(WORK_READY_READY_ROUTER_NAME)
}


fun NavController.navigateRoutineListToRoutineInRoutineDetailGraph(routineSetId: Int) {
    this.setValueSavedStateHandle(
        SavedStateHandleKey.RoutineSet.DETAIL_ROUTINE_SET_ID_KEY,
        routineSetId
    )
    this.navigate(Route.ROUTINE_DETAIL_ROUTINE_ROUTER_NAME)
}

