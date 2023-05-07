package com.gradation.lift.feature.home

import androidx.navigation.NavController
import androidx.navigation.NavOptions

const val HOME_ROUTER_NAME = "home"



fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(HOME_ROUTER_NAME, navOptions)
}