package com.gradation.lift.feature.update_routine.routine

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.UPDATE_ROUTINE_ROUTINE_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateRoutineToRoutineSelection

fun updateRoutineRoutineScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(UPDATE_ROUTINE_ROUTINE_ROUTER_NAME) {
        val navigateRoutineToRoutineSelection: () -> Unit =
            { navController.navigateRoutineToRoutineSelection() }




        UpdateRoutineRoutineRoute(
            navController,
            navigateRoutineToRoutineSelection
        )
    }
}


