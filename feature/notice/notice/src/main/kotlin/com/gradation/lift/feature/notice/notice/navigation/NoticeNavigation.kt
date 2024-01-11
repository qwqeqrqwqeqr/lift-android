package com.gradation.lift.feature.notice.notice.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route

fun NavGraphBuilder.noticeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    composable(Route.NOTICE_NOTICE_ROUTER_NAME) {


        NoticeRoute(
            modifier = modifier,
            navController = navController,
        )

    }
}