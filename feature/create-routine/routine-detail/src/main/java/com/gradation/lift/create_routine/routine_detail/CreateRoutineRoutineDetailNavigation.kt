package com.gradation.lift.create_routine.routine_detail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val CREATE_ROUTINE_ROUTINE_DETAIL_ROUTER_NAME = "create_routine_routine_detail"



fun NavController.navigateToCreateRoutineRoutineDetail(navOptions: NavOptions? = null) {
    this.navigate(CREATE_ROUTINE_ROUTINE_DETAIL_ROUTER_NAME, navOptions)
}


fun NavGraphBuilder.createRoutineRoutineDetailScreen() {
    composable(route = CREATE_ROUTINE_ROUTINE_DETAIL_ROUTER_NAME) {
        CreateRoutineRoutineDetailRoute()
    }
}