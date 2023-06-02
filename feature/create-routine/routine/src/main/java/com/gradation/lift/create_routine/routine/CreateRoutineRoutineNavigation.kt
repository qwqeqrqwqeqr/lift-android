package com.gradation.lift.create_routine.routine

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val CREATE_ROUTINE_ROUTINE_ROUTER_NAME = "create_routine_routine"



fun NavController.navigateToCreateRoutineRoutine(navOptions: NavOptions? = null) {
    this.navigate(CREATE_ROUTINE_ROUTINE_ROUTER_NAME, navOptions)
}


fun NavGraphBuilder.createRoutineRoutineScreen() {
    composable(route = CREATE_ROUTINE_ROUTINE_ROUTER_NAME) {
        CreateRoutineRoutineRoute()
    }
}