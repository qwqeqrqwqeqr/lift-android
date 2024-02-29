package com.gradation.lift.feature.badge.badge.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gradation.lift.navigation.Route.BADGE_BADGE_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateBadgeGraphToHomeGraph
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.Badge.BADGE_PAGE_KEY

fun badgeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {

    val navigateBadgeGraphToHomeGraph: () -> Unit =
        { navController.navigateBadgeGraphToHomeGraph() }


    navGraphBuilder.composable(
        route = "${BADGE_BADGE_ROUTER_NAME}?$BADGE_PAGE_KEY={$BADGE_PAGE_KEY}",
        arguments = listOf(
            navArgument(BADGE_PAGE_KEY) {
                type = NavType.IntType
            },
        ),
    ) { it ->



        BadgeBadgeRoute(
            modifier, navigateBadgeGraphToHomeGraph,
        )
    }

}
