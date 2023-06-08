package com.gradation.lift.feature.my_info

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.MY_INFO_ROUTER_NAME


fun NavGraphBuilder.myInfoScreen() {
    composable(route = MY_INFO_ROUTER_NAME) {
        MyInfoRoute()
    }
}