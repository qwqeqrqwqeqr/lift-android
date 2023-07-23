package com.gradation.lift.feature.register_detail.profile_picture

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router

fun registerDetailProfilePicturetScreen(
    navController : NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    RegisterDetailProfilePictureRoute {route ->
        navGraphBuilder.composable(route) {
            RegisterDetailProfilePictureRoute(navController)
        }
    }.registerDetailProfilePicturetScreen(route = Router.REGISTER_DETAIL_UNIT_OF_WEIGHT_ROUTER_NAME)
}



