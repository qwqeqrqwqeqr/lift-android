package com.gradation.lift.feature.workReady.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.feature.workReady.ready.navigation.readyScreen
import com.gradation.lift.feature.workReady.createWorkSet.navigation.createWorkSetScreen
import com.gradation.lift.feature.workReady.findWorkCategory.navigation.findWorkCategoryScreen
import com.gradation.lift.feature.workReady.routineSelection.navigation.routineSelectionScreen
import com.gradation.lift.navigation.Route.WORK_READY_GRAPH_NAME
import com.gradation.lift.navigation.Route.WORK_READY_ROUTINE_SELECTION_ROUTER_NAME


fun NavGraphBuilder.workReadyGraphBuilder(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    navigation(
        route = WORK_READY_GRAPH_NAME,
        startDestination = WORK_READY_ROUTINE_SELECTION_ROUTER_NAME,
        popEnterTransition = { fadeIn() },
        popExitTransition = { fadeOut() },
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
    ) {
        routineSelectionScreen(modifier, navController)
        readyScreen(modifier, navController)
        findWorkCategoryScreen(modifier, navController)
        createWorkSetScreen(modifier, navController)
    }
}