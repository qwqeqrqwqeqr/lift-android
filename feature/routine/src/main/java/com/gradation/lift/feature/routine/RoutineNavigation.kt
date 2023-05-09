package com.gradation.lift.feature.routine

import androidx.navigation.NavController
import androidx.navigation.NavOptions

const val ROUTINE_ROUTER_NAME = "routine"



fun NavController.navigateToRoutine(navOptions: NavOptions? = null) {
    this.navigate(ROUTINE_ROUTER_NAME, navOptions)
}