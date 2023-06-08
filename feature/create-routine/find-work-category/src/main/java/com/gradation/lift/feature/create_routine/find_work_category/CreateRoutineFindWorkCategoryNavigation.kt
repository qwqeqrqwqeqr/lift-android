package com.gradation.lift.feature.create_routine.find_work_category

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME


fun NavGraphBuilder.createRoutineFindWorkCategoryScreen() {
    composable(route = CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME) {
        CreateRoutineFindWorkCategoryRoute()
    }
}