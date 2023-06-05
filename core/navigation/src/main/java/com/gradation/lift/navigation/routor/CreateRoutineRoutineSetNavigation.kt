package com.gradation.lift.navigation.routor

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.gradation.lift.feature.create_routine.routile_set.CreateRoutineRoutineSetRoute


const val CREATE_ROUTINE_GRAPH_ROUTER_NAME = "create_routine_graph"
const val CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME = "create_routine_routine_set"



fun NavController.navigateToCreateRoutineGraph(navOptions: NavOptions? = null) {
    this.navigate(CREATE_ROUTINE_GRAPH_ROUTER_NAME, navOptions)
}



