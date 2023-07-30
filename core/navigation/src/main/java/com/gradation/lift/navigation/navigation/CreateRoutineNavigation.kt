package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_FIND_WORKPART_ROUTER_NAME
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_GRAPH_ROUTER_NAME
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_PROFILE_ROUTER_NAME
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_ROUTINE_ROUTER_NAME


fun NavController.navigateToCreateRoutineFindWorkCategory() {
    this.navigate(CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME)
}

fun NavController.navigateToCreateRoutineFindWorkpart() {
    this.navigate(CREATE_ROUTINE_FIND_WORKPART_ROUTER_NAME)
}

fun NavController.navigateToCreateRoutineRoutineDetail() {
    this.navigate(CREATE_ROUTINE_PROFILE_ROUTER_NAME)
}


fun NavController.navigateToCreateRoutineRoutine() {
    this.navigate(CREATE_ROUTINE_ROUTINE_ROUTER_NAME)
}
fun NavController.navigateHomeToCreateRoutineGraph() {
    this.navigate(CREATE_ROUTINE_GRAPH_ROUTER_NAME)
}
