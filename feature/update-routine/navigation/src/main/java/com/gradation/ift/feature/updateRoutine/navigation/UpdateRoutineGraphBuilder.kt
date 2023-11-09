package com.gradation.ift.feature.updateRoutine.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.update_routine.find_work_category.updateRoutineFindWorkCategoryScreen
import com.gradation.lift.feature.update_routine.profile_picture.updateRoutineProfilePictureScreen
import com.gradation.lift.feature.update_routine.routine.updateRoutineRoutineScreen
import com.gradation.lift.feature.update_routine.routine_set.updateRoutineRoutineSetScreen
import com.gradation.lift.navigation.Router.UPDATE_ROUTINE_GRAPH_NAME
import com.gradation.lift.navigation.Router.UPDATE_ROUTINE_ROUTINE_SET_ROUTER_NAME


fun updateRoutineGraphBuilder(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.navigation(
        route = UPDATE_ROUTINE_GRAPH_NAME,
        startDestination = UPDATE_ROUTINE_ROUTINE_SET_ROUTER_NAME,
    ) {
        updateRoutineRoutineSetScreen(navController, this)
        updateRoutineProfilePictureScreen(navController, this)
        updateRoutineFindWorkCategoryScreen(navController, this)
        updateRoutineRoutineScreen(navController,this)
    }
}




