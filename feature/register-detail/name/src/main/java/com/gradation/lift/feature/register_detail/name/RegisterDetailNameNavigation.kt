package com.gradation.lift.feature.register_detail.name

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.route.register_detail.RegisterDetailNameRoute

fun registerDetailNameScreen(
    navController : NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    RegisterDetailNameRoute {route ->
        navGraphBuilder.composable(route) {
            RegisterDetailNameRoute(navController)
        }
    }.registerDetailNameScreen(route = Router.REGISTER_DETAIL_NAME_ROUTER_NAME)
}



