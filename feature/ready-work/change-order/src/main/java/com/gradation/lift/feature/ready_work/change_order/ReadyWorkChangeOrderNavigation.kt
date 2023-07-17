package com.gradation.lift.feature.ready_work.change_order

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.READY_WORK_CHANGE_ORDER_ROUTER_NAME
import com.gradation.lift.navigation.route.ready_work.ReadyWorkChangeOrderRoute


fun readyWorkChangeOrderScreen(
    navController : NavController,
    navGraphBuilder: NavGraphBuilder,
) {

    ReadyWorkChangeOrderRoute { route ->
        navGraphBuilder.composable(route) {
            ReadyWorkChangeOrderRoute(navController = navController)
        }
    }.readyWorkChangeOrderScreen(route = READY_WORK_CHANGE_ORDER_ROUTER_NAME)
}
