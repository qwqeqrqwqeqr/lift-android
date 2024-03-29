package com.gradation.lift.feature.work.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.work.complete.navigation.completeScreen
import com.gradation.lift.feature.work.completedetail.navigation.completeDetailScreen
import com.gradation.lift.feature.work.createWorkSet.navigation.createWorkSetScreen
import com.gradation.lift.feature.work.findWorkCategory.navigation.findWorkCategoryScreen
import com.gradation.lift.feature.work.work.navigation.workScreen
import com.gradation.lift.navigation.Route.WORK_GRAPH_NAME
import com.gradation.lift.navigation.Route.WORK_WORK_ROUTER_NAME


fun NavGraphBuilder.workGraphBuilder(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    navigation(
        route = WORK_GRAPH_NAME,
        startDestination = WORK_WORK_ROUTER_NAME,
        popEnterTransition = { fadeIn() },
        popExitTransition = { fadeOut() },
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
    ) {
        workScreen(modifier, navController)
        completeScreen(modifier, navController)
        completeDetailScreen(modifier, navController)
        findWorkCategoryScreen(modifier, navController)
        createWorkSetScreen(modifier, navController)
    }
}