package com.gradation.lift.feature.register_detail.unit_of_weight

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.route.login.LoginVerificationRoute
import com.gradation.lift.navigation.route.register_detail.RegisterDetailUnitOfWeightRoute

fun registerDetailUnitOfWeightScreen(
    navController : NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    RegisterDetailUnitOfWeightRoute {route ->
        navGraphBuilder.composable(route) {
            RegisterDetailUnitOfWeightRoute(navController)
        }
    }.registerDetailUnitOfWeightScreen(route = Router.REGISTER_DETAIL_UNIT_OF_WEIGHT_ROUTER_NAME)
}



