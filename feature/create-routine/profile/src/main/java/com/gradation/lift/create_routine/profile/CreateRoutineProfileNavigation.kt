package com.gradation.lift.create_routine.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.feature.create_routine.CreateRoutineSharedViewModel
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_PROFILE_ROUTER_NAME

fun createRoutineProfileScreen(
    navController : NavController,
    navGraphBuilder: NavGraphBuilder,
    sharedViewModel: CreateRoutineSharedViewModel
) {


        navGraphBuilder.composable(CREATE_ROUTINE_PROFILE_ROUTER_NAME) {

            CreateRoutineProfileRoute(
                navController = navController,
                sharedViewModel=sharedViewModel
            )
        }

}


