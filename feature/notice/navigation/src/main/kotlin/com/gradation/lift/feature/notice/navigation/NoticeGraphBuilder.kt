package com.gradation.lift.feature.notice.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.notice.notice.navigation.noticeScreen
import com.gradation.lift.feature.notice.noticeDetail.navigation.noticeDetailScreen
import com.gradation.lift.navigation.Route


fun NavGraphBuilder.noticeGraphBuilder(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    navigation(
        route = Route.NOTICE_GRAPH_NAME,
        startDestination = Route.NOTICE_NOTICE_ROUTER_NAME,
        popEnterTransition = null,
        popExitTransition = null,
        enterTransition = null,
        exitTransition = null,
    ) {
        noticeScreen(modifier, navController)
        noticeDetailScreen(modifier, navController)
    }
}