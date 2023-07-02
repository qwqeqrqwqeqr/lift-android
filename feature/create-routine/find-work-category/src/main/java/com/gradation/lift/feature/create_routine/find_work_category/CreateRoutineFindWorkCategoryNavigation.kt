package com.gradation.lift.feature.create_routine.find_work_category

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME
import androidx.navigation.NavController
import com.gradation.lift.navigation.route.create_routine.CreateRoutineFindWorkCategoryRoute


fun createRoutineFindWorkpartScreen(
    navController : NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    CreateRoutineFindWorkCategoryRoute { route ->
        navGraphBuilder.composable(route) {
            CreateRoutineFindWorkCategoryRoute(navController = navController)
        }
    }.createRoutineFindWorkCategoryScreen(route = CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME)
}
