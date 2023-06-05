package com.gradation.lift.feature.create_routine.routile_set

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.gradation.lift.feature.create_routine.routile_set.CreateRoutineRoutineSetRoute
import com.gradation.lift.navigation.routor.CREATE_ROUTINE_GRAPH_ROUTER_NAME
import com.gradation.lift.navigation.routor.CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME


fun NavGraphBuilder.createRoutineGraph(
    nestedGraphs: NavGraphBuilder.() -> Unit,
) {
    navigation(
        route = CREATE_ROUTINE_GRAPH_ROUTER_NAME,
        startDestination = CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME,
    ) {
        composable(route = CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME) {
            CreateRoutineRoutineSetRoute()
        }
        nestedGraphs()
    }
}
