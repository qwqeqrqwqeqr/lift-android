package com.gradation.ift.feature.updateRoutine.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.updateRoutine.findWorkCategory.navigation.findWorkCategoryScreen
import com.gradation.lift.feature.updateRoutine.routine.navigation.routineScreen
import com.gradation.lift.feature.updateRoutine.routineSet.navigation.routineSetScreen
import com.gradation.lift.feature.updateRoutine.profilePicture.navigation.profilePictureScreen
import com.gradation.lift.navigation.Route.UPDATE_ROUTINE_GRAPH_NAME
import com.gradation.lift.navigation.Route.UPDATE_ROUTINE_ROUTINE_SET_ROUTER_NAME


fun updateRoutineGraphBuilder(
    modifier:Modifier=Modifier,
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.navigation(
        route = UPDATE_ROUTINE_GRAPH_NAME,
        startDestination = UPDATE_ROUTINE_ROUTINE_SET_ROUTER_NAME,
        popEnterTransition = null,
        popExitTransition = null,
        enterTransition = null,
        exitTransition = null,
    ) {
        routineSetScreen(modifier,navController, this)
        profilePictureScreen(modifier,navController, this)
        findWorkCategoryScreen(modifier,navController, this)
        routineScreen(modifier,navController,this)
    }
}




