package com.gradation.lift.feature.work.change_order

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.WORK_CHANGE_ORDER_ROUTER_NAME


fun workChangeOrderScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(WORK_CHANGE_ORDER_ROUTER_NAME) {



        WorkChangeOrderRoute(
            navController = navController,

        )
    }

}
