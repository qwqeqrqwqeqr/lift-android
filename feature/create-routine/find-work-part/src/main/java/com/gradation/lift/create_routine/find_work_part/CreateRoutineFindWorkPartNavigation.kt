package com.gradation.lift.create_routine.find_work_part

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router


fun createRoutineFindWorkPartScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(Router.CREATE_ROUTINE_FIND_WORKPART_ROUTER_NAME) {
        CreateRoutineFindWorkPartRoute(navController = navController)
    }

}
