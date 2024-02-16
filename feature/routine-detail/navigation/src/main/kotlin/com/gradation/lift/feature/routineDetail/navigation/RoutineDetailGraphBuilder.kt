package com.gradation.lift.feature.routineDetail.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.routineDetail.routine.navigation.routineScreen
import com.gradation.lift.feature.routineDetail.routineList.navigation.routineListScreen
import com.gradation.lift.navigation.Route

fun NavGraphBuilder.routineDetailGraphBuilder(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    navigation(
        route = Route.ROUTINE_DETAIL_GRAPH_NAME,
        startDestination = Route.ROUTINE_DETAIL_ROUTINE_LIST_ROUTER_NAME,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        popEnterTransition = { fadeIn() },
        popExitTransition = { fadeOut() }
    ) {
        routineScreen(modifier, navController, this)
        routineListScreen(modifier, navController, this)
    }
}