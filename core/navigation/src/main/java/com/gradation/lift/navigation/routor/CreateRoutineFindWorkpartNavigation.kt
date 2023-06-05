package com.gradation.lift.navigation.routor

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.gradation.lift.create_routine.find_workpart.CreateRoutineFindWorkpartRoute

const val CREATE_ROUTINE_FIND_WORKPART_ROUTER_NAME = "create_routine_find_workpart"



fun NavController.navigateToCreateRoutineFindWorkpart(navOptions: NavOptions? = null) {
    this.navigate(CREATE_ROUTINE_FIND_WORKPART_ROUTER_NAME, navOptions)
}


