package com.gradation.lift.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.create_routine.profile.createRoutineProfileScreen
import com.gradation.lift.create_routine.routine.createRoutineRoutineScreen
import com.gradation.lift.feature.create_routine.CreateRoutineSharedViewModel
import com.gradation.lift.feature.create_routine.createRoutineScreen
import com.gradation.lift.feature.create_routine.find_work_category.createRoutineFindWorkCategoryScreen
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_GRAPH_NAME
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_ROUTER_NAME

fun createRoutineGraphBuilder(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
    sharedViewModel: CreateRoutineSharedViewModel,
) {
    navGraphBuilder.navigation(
        route = CREATE_ROUTINE_GRAPH_NAME,
        startDestination = CREATE_ROUTINE_ROUTER_NAME,
    ) {
        createRoutineScreen(navController, this, sharedViewModel)
        createRoutineRoutineScreen(navController, this, sharedViewModel)
        createRoutineProfileScreen(navController, this, sharedViewModel)
        createRoutineFindWorkCategoryScreen(navController, this, sharedViewModel)
    }
}




