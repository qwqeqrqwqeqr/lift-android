package com.gradation.lift.feature.routineDetail.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.routineDetail.routine.navigation.routineScreen
import com.gradation.lift.feature.routineDetail.routineList.navigation.routineListScreen
import com.gradation.lift.navigation.Route

fun routineDetailGraphBuilder(
    modifier: Modifier = Modifier,
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.navigation(
        route = Route.ROUTINE_DETAIL_GRAPH_NAME,
        startDestination = Route.ROUTINE_DETAIL_ROUTINE_LIST_ROUTER_NAME,
        popEnterTransition = null,
        popExitTransition = null,
        enterTransition = null,
        exitTransition = null,
    ) {
        routineScreen(modifier, navController, this)
        routineListScreen(modifier, navController, this)
    }
}