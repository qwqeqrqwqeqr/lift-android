package com.gradation.lift.feature.create_routine.routine_set

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateRoutineSetToFindWorkCategory
import com.gradation.lift.navigation.navigation.navigateRoutineSetToProfile
import com.gradation.lift.navigation.navigation.navigateCreateRoutineGraphToMainGraph


@RequiresApi(Build.VERSION_CODES.O)
fun createRoutineScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    val navigateRoutineSetToFindWorkCategory =
        { navController.navigateRoutineSetToFindWorkCategory() }
    val navigateRoutineSetToProfile = { navController.navigateRoutineSetToProfile() }
    val navigateCreateRoutineGraphToMainGraph = { navController.navigateCreateRoutineGraphToMainGraph() }

    navGraphBuilder.composable(CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME) {
        CreateRoutineRoutineSetRoute(
            navController = navController,
            navigateRoutineSetToFindWorkCategory=navigateRoutineSetToFindWorkCategory,
            navigateRoutineSetToProfile=navigateRoutineSetToProfile,
            navigateCreateRoutineGraphToMainGraph=navigateCreateRoutineGraphToMainGraph
        )
    }
}

