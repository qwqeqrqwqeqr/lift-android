package com.gradation.ift.feature.updateRoutine.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.updateRoutine.changeOrder.navigation.changeOrderScreen
import com.gradation.lift.feature.updateRoutine.createWorkSet.navigation.createWorkSetScreen
import com.gradation.lift.feature.updateRoutine.findWorkCategory.navigation.findWorkCategoryScreen
import com.gradation.lift.feature.updateRoutine.routineSet.navigation.routineSetScreen
import com.gradation.lift.feature.updateRoutine.profilePicture.navigation.profilePictureScreen
import com.gradation.lift.feature.updateRoutine.updateWorkSet.navigation.updateWorkSetScreen
import com.gradation.lift.navigation.Route.UPDATE_ROUTINE_GRAPH_NAME
import com.gradation.lift.navigation.Route.UPDATE_ROUTINE_ROUTINE_SET_ROUTER_NAME


fun updateRoutineGraphBuilder(
    modifier: Modifier = Modifier,
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.navigation(
        route = UPDATE_ROUTINE_GRAPH_NAME,
        startDestination = UPDATE_ROUTINE_ROUTINE_SET_ROUTER_NAME,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        popEnterTransition = { fadeIn() },
        popExitTransition = { fadeOut() }
    ) {
        routineSetScreen(modifier, navController)
        profilePictureScreen(modifier, navController, this)
        findWorkCategoryScreen(modifier, navController, this)
        createWorkSetScreen(modifier, navController, this)
        updateWorkSetScreen(modifier, navController)
        changeOrderScreen(modifier, navController)
    }
}




