package com.gradation.lift.create_routine.routine_detail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.gradation.lift.create_routine.routine_detail.CreateRoutineRoutineDetailRoute
import com.gradation.lift.navigation.routor.CREATE_ROUTINE_ROUTINE_DETAIL_ROUTER_NAME


fun NavGraphBuilder.createRoutineRoutineDetailScreen() {
    composable(route = CREATE_ROUTINE_ROUTINE_DETAIL_ROUTER_NAME) {
        CreateRoutineRoutineDetailRoute()
    }
}