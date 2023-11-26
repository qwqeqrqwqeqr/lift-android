package com.gradation.lift.feature.routineDetail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.routine_detail.routine.routineDetailRoutineScreen
import com.gradation.lift.feature.routine_detail.routine_list.navigation.routineListScreen
import com.gradation.lift.navigation.Router

fun routineDetailGraphBuilder(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.navigation(
        route = Router.ROUTINE_DETAIL_GRAPH_NAME,
        startDestination = Router.ROUTINE_DETAIL_ROUTINE_LIST_ROUTER_NAME,
    ) {
        routineDetailRoutineScreen(navController, this)
        routineListScreen(navController, this)
    }
}