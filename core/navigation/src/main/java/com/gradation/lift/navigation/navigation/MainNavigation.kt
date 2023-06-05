package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.gradation.lift.navigation.routor.Router.HISTORY_ROUTER_NAME
import com.gradation.lift.navigation.routor.Router.HOME_ROUTER_NAME
import com.gradation.lift.navigation.routor.Router.MY_INFO_ROUTER_NAME
import com.gradation.lift.navigation.routor.Router.ROUTINE_ROUTER_NAME



fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(HOME_ROUTER_NAME, navOptions)
}

fun NavController.navigateToRoutine(navOptions: NavOptions? = null) {
    this.navigate(ROUTINE_ROUTER_NAME, navOptions)
}

fun NavController.navigateToHistory(navOptions: NavOptions? = null) {
    this.navigate(HISTORY_ROUTER_NAME, navOptions)
}

fun NavController.navigateToMyInfo(navOptions: NavOptions? = null) {
    this.navigate(MY_INFO_ROUTER_NAME, navOptions)
}


