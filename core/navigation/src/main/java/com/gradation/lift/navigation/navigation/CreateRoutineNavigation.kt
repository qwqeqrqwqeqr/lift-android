package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.gradation.lift.navigation.routor.Router.CREATE_ROUTINE_FIND_WORKPART_ROUTER_NAME
import com.gradation.lift.navigation.routor.Router.CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME
import com.gradation.lift.navigation.routor.Router.CREATE_ROUTINE_GRAPH_ROUTER_NAME
import com.gradation.lift.navigation.routor.Router.CREATE_ROUTINE_ROUTINE_DETAIL_ROUTER_NAME
import com.gradation.lift.navigation.routor.Router.CREATE_ROUTINE_ROUTINE_ROUTER_NAME
import com.gradation.lift.navigation.routor.Router.CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME


fun NavController.navigateToCreateRoutineFindWorkCategory(navOptions: NavOptions? = null) {
    this.navigate(CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME, navOptions)
}

fun NavController.navigateToCreateRoutineFindWorkpart(navOptions: NavOptions? = null) {
    this.navigate(CREATE_ROUTINE_FIND_WORKPART_ROUTER_NAME, navOptions)
}

fun NavController.navigateToCreateRoutineRoutineDetail(navOptions: NavOptions? = null) {
    this.navigate(CREATE_ROUTINE_ROUTINE_DETAIL_ROUTER_NAME, navOptions)
}

fun NavController.navigateToCreateRoutineRoutineSet(navOptions: NavOptions? = null) {
    this.navigate(CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME, navOptions)
}

fun NavController.navigateToCreateRoutineRoutine(navOptions: NavOptions? = null) {
    this.navigate(CREATE_ROUTINE_ROUTINE_ROUTER_NAME, navOptions)
}
fun NavController.navigateToCreateRoutineGraph(navOptions: NavOptions? = null) {
    this.navigate(CREATE_ROUTINE_GRAPH_ROUTER_NAME, navOptions)
}
