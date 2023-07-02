package com.gradation.lift.feature.my_info

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.route.main.MyInfoRoute

fun myInfoScreen(
    navController : NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    MyInfoRoute { route ->
        navGraphBuilder.composable(route) {
            MyInfoRoute(navController = navController)

        }
    }.myInfoScreen(route = Router.MY_INFO_ROUTER_NAME)
}
