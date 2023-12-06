package com.gradation.lift.feature.createRoutine.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.create_routine.profile_picture.createRoutineProfilePictureScreen
import com.gradation.lift.create_routine.routine.createRoutineRoutineScreen
import com.gradation.lift.feature.create_routine.routine_set.createRoutineScreen
import com.gradation.lift.feature.create_routine.find_work_category.createRoutineFindWorkCategoryScreen
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_GRAPH_NAME
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME


fun createRoutineGraphBuilder(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.navigation(
        route = CREATE_ROUTINE_GRAPH_NAME,
        startDestination = CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME,
    ) {
        createRoutineScreen(navController, this)
        createRoutineRoutineScreen(navController, this)
        createRoutineProfilePictureScreen(navController, this)
        createRoutineFindWorkCategoryScreen(navController, this)
    }
}




