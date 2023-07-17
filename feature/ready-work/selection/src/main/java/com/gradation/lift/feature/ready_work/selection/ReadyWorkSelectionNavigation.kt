package com.gradation.lift.feature.ready_work.selection

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.READY_WORK_SELECTION_ROUTER_NAME
import com.gradation.lift.navigation.route.ready_work.ReadyWorkSelectionRoute


fun readyWorkSelectionScreen(
    navController : NavController,
    navGraphBuilder: NavGraphBuilder,
) {

    ReadyWorkSelectionRoute { route ->
        navGraphBuilder.composable(route) {
            ReadyWorkSelectionRoute(navController = navController)
        }
    }.readyWorkSelectionScreen(route = READY_WORK_SELECTION_ROUTER_NAME)
}
