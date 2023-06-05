package com.gradation.lift.navigation.routor

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.gradation.lift.create_routine.routine_detail.CreateRoutineRoutineDetailRoute

const val CREATE_ROUTINE_ROUTINE_DETAIL_ROUTER_NAME = "create_routine_routine_detail"



fun NavController.navigateToCreateRoutineRoutineDetail(navOptions: NavOptions? = null) {
    this.navigate(CREATE_ROUTINE_ROUTINE_DETAIL_ROUTER_NAME, navOptions)
}


