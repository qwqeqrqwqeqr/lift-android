package com.gradation.lift.feature.notice.noticeDetail.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gradation.lift.navigation.Route.NOTICE_NOTICE_DETAIL_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateNoticeDetailToNoticeInNoticeGraph
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.Notice.NOTICE_ID_KEY

fun NavGraphBuilder.noticeDetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    composable(
        route = "$NOTICE_NOTICE_DETAIL_ROUTER_NAME/{$NOTICE_ID_KEY}",
        arguments = listOf(
            navArgument(NOTICE_ID_KEY) { type = NavType.IntType },
        )
    ) {
        val navigateNoticeDetailToNoticeInNoticeGraph =
            { navController.navigateNoticeDetailToNoticeInNoticeGraph() }

        NoticeDetailRoute(
            modifier,
            navigateNoticeDetailToNoticeInNoticeGraph
        )
    }
}


