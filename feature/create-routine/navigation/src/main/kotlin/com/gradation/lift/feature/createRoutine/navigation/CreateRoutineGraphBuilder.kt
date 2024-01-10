package com.gradation.lift.feature.createRoutine.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.createRoutine.profilePicture.navigation.profilePictureScreen
import com.gradation.lift.feature.createRotuine.updateWorkSet.navigation.updateWorkSetScreen
import com.gradation.lift.feature.createRoutine.changeOrder.navigation.changeOrderScreen
import com.gradation.lift.feature.createRoutine.createWorkSet.navigation.createWorkSetScreen
import com.gradation.lift.feature.createRoutine.findWorkCategory.navigation.findWorkCategoryScreen
import com.gradation.lift.feature.createRoutine.routineSet.navigation.routineSetScreen
import com.gradation.lift.navigation.Route.CREATE_ROUTINE_GRAPH_NAME
import com.gradation.lift.navigation.Route.CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME


fun createRoutineGraphBuilder(
    modifier: Modifier = Modifier,
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.navigation(
        route = CREATE_ROUTINE_GRAPH_NAME,
        startDestination = CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        popEnterTransition = { fadeIn() },
        popExitTransition = { fadeOut() }
    ) {
        routineSetScreen(modifier, navController, this)
        findWorkCategoryScreen(modifier, navController, this)
        profilePictureScreen(modifier, navController, this)
        createWorkSetScreen(modifier, navController, this)
        updateWorkSetScreen(modifier, navController)
        changeOrderScreen(modifier,navController)
    }
}




