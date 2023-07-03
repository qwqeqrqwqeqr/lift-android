package com.gradation.lift.feature.register_detail.complete

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.route.register_detail.RegisterDetailCompleteRoute
import com.gradation.lift.navigation.route.register_detail.RegisterDetailGenderRoute

fun registerDetailCompleteScreen(
    navController : NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    RegisterDetailCompleteRoute {route ->
        navGraphBuilder.composable(route) {
            RegisterDetailCompleteRoute(navController)
        }
    }.registerDetailCompleteScreen(route = Router.REGISTER_DETAIL_COMPLETE_ROUTER_NAME)
}



