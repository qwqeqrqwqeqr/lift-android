package com.gradation.lift.feature.work.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.work.complete.navigation.completeScreen
import com.gradation.lift.feature.work.routineSelection.navigation.routineSelectionScreen
import com.gradation.lift.feature.work.work.navigation.workScreen
import com.gradation.lift.navigation.Route.WORK_GRAPH_NAME
import com.gradation.lift.navigation.Route.WORK_ROUTINE_SELECTION_ROUTER_NAME


fun workGraphBuilder(
    modifier:Modifier=Modifier,
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.navigation(
        route = WORK_GRAPH_NAME,
        startDestination = WORK_ROUTINE_SELECTION_ROUTER_NAME,
        popEnterTransition = null,
        popExitTransition = null,
        enterTransition = null,
        exitTransition = null,
    ) {
        routineSelectionScreen(modifier,navController, this)
        workScreen(modifier,navController, this)
        completeScreen(modifier,navController, this)
    }
}