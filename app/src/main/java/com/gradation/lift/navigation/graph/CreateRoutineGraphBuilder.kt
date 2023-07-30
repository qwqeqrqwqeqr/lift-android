package com.gradation.lift.navigation.graph

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.create_routine.createRoutineScreen
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_GRAPH_ROUTER_NAME
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_ROUTER_NAME
import com.gradation.lift.navigation.route.create_routine.CreateRoutineGraph
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

fun createRoutineGraphBuilder(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
    sharedViewModel: CreateRoutineViewModel,
) {
    navGraphBuilder.navigation(
        route = CREATE_ROUTINE_GRAPH_ROUTER_NAME,
        startDestination = CREATE_ROUTINE_ROUTER_NAME,
    ) {
        createRoutineScreen(navController, this, sharedViewModel)
        createRoutineRoutineScreen(navController, this, sharedViewModel)
        createRoutineProfileScreen(navController, this, sharedViewModel)
        createRoutineFindWorkCategoryScreen(navController, this, sharedViewModel)
        createRoutineFindWorkPartScreen(navController, this, sharedViewModel)
    }
}


@HiltViewModel
class CreateRoutineViewModel @Inject constructor(
) : ViewModel() {
}


