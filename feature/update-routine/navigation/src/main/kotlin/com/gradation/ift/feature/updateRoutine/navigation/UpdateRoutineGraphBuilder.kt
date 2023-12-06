package com.gradation.ift.feature.updateRoutine.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.update_routine.find_work_category.findWorkCategoryScreen
import com.gradation.lift.feature.update_routine.profile_picture.profilePictureScreen
import com.gradation.lift.feature.update_routine.routine.routineScreen
import com.gradation.lift.feature.update_routine.routine_set.routineSetScreen
import com.gradation.lift.navigation.Router.UPDATE_ROUTINE_GRAPH_NAME
import com.gradation.lift.navigation.Router.UPDATE_ROUTINE_ROUTINE_SET_ROUTER_NAME


fun updateRoutineGraphBuilder(
    modifier:Modifier=Modifier,
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.navigation(
        route = UPDATE_ROUTINE_GRAPH_NAME,
        startDestination = UPDATE_ROUTINE_ROUTINE_SET_ROUTER_NAME,
    ) {
        routineSetScreen(modifier,navController, this)
        profilePictureScreen(modifier,navController, this)
        findWorkCategoryScreen(modifier,navController, this)
        routineScreen(modifier,navController,this)
    }
}




