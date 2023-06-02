package com.gradation.lift.feature.create_routine.find_work_category

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME = "create_routine_find_work_category"



fun NavController.navigateToCreateRoutineFindWorkCategory(navOptions: NavOptions? = null) {
    this.navigate(CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME, navOptions)
}


fun NavGraphBuilder.createRoutineFindWorkCategoryScreen() {
    composable(route = CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME) {
        CreateRoutineFindWorkCategoryRoute()
    }
}