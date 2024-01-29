package com.gradation.lift.feature.history.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.history.history.navigation.historyScreen
import com.gradation.lift.feature.history.updateInfo.navigation.updateInfoScreen
import com.gradation.lift.navigation.Route.HISTORY_GRAPH_NAME
import com.gradation.lift.navigation.Route.HISTORY_HISTORY_ROUTER_NAME


fun NavGraphBuilder.historyGraphBuilder(
    modifier: Modifier=Modifier,
    navController: NavController,
) {
    navigation(
        route = HISTORY_GRAPH_NAME,
        startDestination = HISTORY_HISTORY_ROUTER_NAME,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        popEnterTransition = { fadeIn() },
        popExitTransition = { fadeOut() }
    ) {
        historyScreen(modifier, navController)
        updateInfoScreen(modifier, navController)
    }
}



