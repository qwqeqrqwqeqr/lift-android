package com.gradation.lift.feature.routine

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val ROUTINE_ROUTER_NAME = "routine"



fun NavController.navigateToRoutine(navOptions: NavOptions? = null) {
    this.navigate(ROUTINE_ROUTER_NAME, navOptions)
}


fun NavGraphBuilder.routineScreen() {
    composable(route = ROUTINE_ROUTER_NAME){
        RoutineRoute()
    }
}