package com.gradation.lift.navigation.navigation

import android.annotation.SuppressLint
import androidx.navigation.NavController
import com.gradation.lift.navigation.Route
import com.gradation.lift.navigation.Route.ROUTINE_DETAIL_ROUTINE_ROUTER_NAME
import com.gradation.lift.navigation.Route.UPDATE_ROUTINE_GRAPH_NAME
import com.gradation.lift.navigation.Route.WORK_READY_READY_ROUTER_NAME
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.UpdateRoutine.UPDATE_ROUTINE_SET_ID_KEY
import com.gradation.lift.navigation.saved_state.setValueSavedStateHandle


fun NavController.navigateRoutineDetailGraphToHomeGraph() {
    this.navigate(Route.HOME_GRAPH_NAME) {
        popUpTo(this@navigateRoutineDetailGraphToHomeGraph.graph.id) {
            inclusive = true
        }
    }
}

@SuppressLint("RestrictedApi")
fun NavController.navigateRoutineDetailGraphToBack() {
    popBackStack()
}

fun NavController.navigateRoutineDetailGraphToCreateRoutineGraph() {
    this.navigate(Route.CREATE_ROUTINE_GRAPH_NAME)
}


fun NavController.navigateRoutineDetailGraphToUpdateRoutineGraph(routineSetId: Int) {
    this.setValueSavedStateHandle(
        UPDATE_ROUTINE_SET_ID_KEY,
        routineSetId
    )
    this.navigate(UPDATE_ROUTINE_GRAPH_NAME)
}

fun NavController.navigateRoutineDetailGraphToWorkReadyReadyRoute(routineSetIdList: String) {
    this.setValueSavedStateHandle(
        SavedStateHandleKey.Work.WORK_ROUTINE_SET_ID_LIST_KEY,
        routineSetIdList
    )
    this.navigate(WORK_READY_READY_ROUTER_NAME)
}


fun NavController.navigateRoutineListToRoutineInRoutineDetailGraph(routineSetId: Int) {
    this.navigate("$ROUTINE_DETAIL_ROUTINE_ROUTER_NAME/$routineSetId")
}

