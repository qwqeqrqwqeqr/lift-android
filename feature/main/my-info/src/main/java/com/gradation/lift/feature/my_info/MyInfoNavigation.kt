package com.gradation.lift.feature.my_info

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.route.main.MyInfoRoute




fun myInfoScreen(navGraphBuilder: NavGraphBuilder) {
    MyInfoRoute {router, navigationGraphBuilder ->
        navigationGraphBuilder.composable(router) {
            MyInfoRoute()
        }
    }.myInfoScreen(router = Router.MY_INFO_ROUTER_NAME, navGraphBuilder)
}
