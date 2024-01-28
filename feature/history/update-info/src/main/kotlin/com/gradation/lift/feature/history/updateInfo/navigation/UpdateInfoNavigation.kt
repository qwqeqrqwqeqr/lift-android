package com.gradation.lift.feature.history.updateInfo.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gradation.lift.navigation.Route.HISTORY_UPDATE_INFO_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateUpdateInfoToHistoryInHistoryGraph
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.History.HISTORY_COMMENT_KEY
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.History.HISTORY_SCORE_KEY


fun NavGraphBuilder.updateInfoScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val navigateUpdateInfoToHistoryInHistoryGraph: () -> Unit =
        { navController.navigateUpdateInfoToHistoryInHistoryGraph() }

    composable(
        route = "${HISTORY_UPDATE_INFO_ROUTER_NAME}?$HISTORY_COMMENT_KEY={${HISTORY_COMMENT_KEY}}&$HISTORY_SCORE_KEY={${HISTORY_SCORE_KEY}}",
        arguments = listOf(
            navArgument(HISTORY_COMMENT_KEY) {
                type = NavType.StringType
                defaultValue = ""
            },
            navArgument(HISTORY_SCORE_KEY) {
                type = NavType.IntType
                defaultValue = 0
            },
        ),
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        popEnterTransition = { fadeIn() },
        popExitTransition = { fadeOut() }
    ) {
        UpdateInfoRoute(modifier)
    }
}

