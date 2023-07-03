package com.gradation.lift.feature.register_detail.ht_wt

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.route.register_detail.RegisterDetailHtWtRoute

fun registerDetailHtWtScreen(
    navController : NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    RegisterDetailHtWtRoute {route ->
        navGraphBuilder.composable(route) {
            RegisterHtWtNameRoute(navController)
        }
    }.registerDetailHtWtScreen(route = Router.REGISTER_DETAIL_HT_WT_ROUTER_NAME)
}



