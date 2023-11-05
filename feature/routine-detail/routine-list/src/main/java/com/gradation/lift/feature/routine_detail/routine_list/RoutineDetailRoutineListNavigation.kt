package com.gradation.lift.feature.routine_detail.routine_list

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.ROUTINE_DETAIL_ROUTINE_LIST_ROUTER_NAME


fun routineDetailRoutineListScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(ROUTINE_DETAIL_ROUTINE_LIST_ROUTER_NAME) {

        RoutineDetailRoutineListRoute()
    }
}


