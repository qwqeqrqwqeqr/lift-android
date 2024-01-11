package com.gradation.lift.feature.notice.noticeDetail

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route

fun NavGraphBuilder.noticeDetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    composable(Route.NOTICE_NOTICE_DETAIL_ROUTER_NAME) {



        NoticeDetailRoute(
            navController,
        )

    }
}