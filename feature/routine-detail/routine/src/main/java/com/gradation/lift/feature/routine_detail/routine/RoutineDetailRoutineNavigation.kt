package com.gradation.lift.feature.routine_detail.routine

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.ROUTINE_DETAIL_ROUTINE_ROUTER_NAME


fun routineDetailRoutineScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(ROUTINE_DETAIL_ROUTINE_ROUTER_NAME) {

        RoutineDetailRoutineRoute()
    }
}


