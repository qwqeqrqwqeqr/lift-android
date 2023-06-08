package com.gradation.lift.create_routine.routine_detail

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_ROUTINE_DETAIL_ROUTER_NAME


fun NavGraphBuilder.createRoutineRoutineDetailScreen() {
    composable(route = CREATE_ROUTINE_ROUTINE_DETAIL_ROUTER_NAME) {
        CreateRoutineRoutineDetailRoute()
    }
}