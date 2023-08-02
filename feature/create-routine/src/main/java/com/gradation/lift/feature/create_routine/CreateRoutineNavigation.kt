package com.gradation.lift.feature.create_routine

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateCreateRoutineRootToFindWorkCategory
import com.gradation.lift.navigation.navigation.navigateCreateRoutineRootToProfile
import com.gradation.lift.navigation.navigation.navigateCreateRoutineToMain


@RequiresApi(Build.VERSION_CODES.O)
fun createRoutineScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    val navigateCreateRoutineRootToFindWorkCategory =
        { navController.navigateCreateRoutineRootToFindWorkCategory() }
    val navigateCreateRoutineRootToProfile = { navController.navigateCreateRoutineRootToProfile() }
    val navigateCreateRoutineToMain = { navController.navigateCreateRoutineToMain() }

    navGraphBuilder.composable(CREATE_ROUTINE_ROUTER_NAME) {
        CreateRoutineRoute(
            navController = navController,
            navigateCreateRoutineRootToFindWorkCategory=navigateCreateRoutineRootToFindWorkCategory,
            navigateCreateRoutineRootToProfile=navigateCreateRoutineRootToProfile,
            navigateCreateRoutineToMain=navigateCreateRoutineToMain
        )
    }
}
