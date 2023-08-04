package com.gradation.lift.feature.work.complete

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.WORK_COMPLETE_ROUTER_NAME


fun workCompleteScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(WORK_COMPLETE_ROUTER_NAME) {
        WorkCompleteRoute(
            navController = navController,
        )
    }

}
