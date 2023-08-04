package com.gradation.lift.feature.create_routine.routine_set

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateCreateRoutineRoutineSetToFindWorkCategory
import com.gradation.lift.navigation.navigation.navigateCreateRoutineRoutineSetToProfile
import com.gradation.lift.navigation.navigation.navigateCreateRoutineToMain


@RequiresApi(Build.VERSION_CODES.O)
fun createRoutineScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    val navigateCreateRoutineRoutineSetToFindWorkCategory =
        { navController.navigateCreateRoutineRoutineSetToFindWorkCategory() }
    val navigateCreateRoutineRoutineSetToProfile = { navController.navigateCreateRoutineRoutineSetToProfile() }
    val navigateCreateRoutineToMain = { navController.navigateCreateRoutineToMain() }

    navGraphBuilder.composable(CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME) {
        CreateRoutineRoutineSetRoute(
            navController = navController,
            navigateCreateRoutineRoutineSetToFindWorkCategory=navigateCreateRoutineRoutineSetToFindWorkCategory,
            navigateCreateRoutineRoutineSetToProfile=navigateCreateRoutineRoutineSetToProfile,
            navigateCreateRoutineToMain=navigateCreateRoutineToMain
        )
    }
}

