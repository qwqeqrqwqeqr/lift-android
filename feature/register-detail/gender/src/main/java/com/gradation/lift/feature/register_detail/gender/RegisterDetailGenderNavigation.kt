package com.gradation.lift.feature.register_detail.gender

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.route.register_detail.RegisterDetailGenderRoute

fun registerDetailGenderScreen(
    navController : NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    RegisterDetailGenderRoute {route ->
        navGraphBuilder.composable(route) {
            RegisterDetailGenderRoute(navController)
        }
    }.registerDetailGenderScreen(route = Router.REGISTER_DETAIL_GENDER_ROUTER_NAME)
}



