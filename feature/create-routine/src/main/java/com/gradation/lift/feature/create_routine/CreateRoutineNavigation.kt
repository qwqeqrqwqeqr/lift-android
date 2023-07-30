package com.gradation.lift.feature.create_routine

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_ROUTER_NAME


fun createRoutineScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
    sharedViewModel: CreateRoutineSharedViewModel,
) {
    navGraphBuilder.composable(CREATE_ROUTINE_ROUTER_NAME) {
        CreateRoutineRoute(
            navController = navController,
            sharedViewModel = sharedViewModel
        )
    }
}

