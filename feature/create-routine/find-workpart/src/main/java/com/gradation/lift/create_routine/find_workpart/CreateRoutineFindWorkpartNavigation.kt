package com.gradation.lift.create_routine.find_workpart

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.gradation.lift.create_routine.find_workpart.CreateRoutineFindWorkpartRoute
import com.gradation.lift.navigation.routor.CREATE_ROUTINE_FIND_WORKPART_ROUTER_NAME


fun NavGraphBuilder.createRoutineFindWorkpartScreen() {
    composable(route = CREATE_ROUTINE_FIND_WORKPART_ROUTER_NAME) {
        CreateRoutineFindWorkpartRoute()
    }
}