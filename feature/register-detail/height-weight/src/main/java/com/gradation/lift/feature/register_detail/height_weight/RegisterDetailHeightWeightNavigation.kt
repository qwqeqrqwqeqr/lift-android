package com.gradation.lift.feature.register_detail.height_weight

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.route.register_detail.RegisterDetailHeightWeightRoute

fun registerDetailHeightWeightScreen(
    navController : NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    RegisterDetailHeightWeightRoute {route ->
        navGraphBuilder.composable(route) {
            RegisterHeightWeightNameRoute(navController)
        }
    }.registerDetailHeightWeightScreen(route = Router.REGISTER_DETAIL_HEIGHT_WEIGHT_ROUTER_NAME)
}



