package com.gradation.lift.create_routine.routine

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.gradation.lift.create_routine.routine.CreateRoutineRoutineRoute
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_ROUTINE_ROUTER_NAME


fun NavGraphBuilder.createRoutineRoutineScreen() {
    composable(route = CREATE_ROUTINE_ROUTINE_ROUTER_NAME) {
        CreateRoutineRoutineRoute()
    }
}