package com.gradation.lift.feature.routine

import androidx.navigation.NavController
import androidx.navigation.NavOptions

const val ROUTINE_ROUTER_NAME = "my-info"



fun NavController.navigateToRoutine(navOptions: NavOptions? = null) {
    this.navigate(ROUTINE_ROUTER_NAME, navOptions)
}