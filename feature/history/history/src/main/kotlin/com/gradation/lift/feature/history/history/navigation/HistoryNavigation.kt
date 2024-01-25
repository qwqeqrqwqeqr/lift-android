package com.gradation.lift.feature.history.history.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route.HISTORY_HISTORY_ROUTER_NAME


fun NavGraphBuilder.historyScreen(
    modifier: Modifier = Modifier,
) {
    composable(
        route= HISTORY_HISTORY_ROUTER_NAME,
        enterTransition = { fadeIn()},
        exitTransition = { fadeOut() },
        popEnterTransition = { fadeIn() },
        popExitTransition = { fadeOut() }
    ) {

        HistoryRoute(modifier)
    }
}
