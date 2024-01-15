package com.gradation.lift.feature.notice.notice.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route
import com.gradation.lift.navigation.navigation.navigateNoticeGraphToMyInfoGraph
import com.gradation.lift.navigation.navigation.navigateNoticeToNoticeDetailInNoticeGraph

fun NavGraphBuilder.noticeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val navigateNoticeGraphToMyInfoGraph:()-> Unit = { navController.navigateNoticeGraphToMyInfoGraph() }
    val navigateNoticeToNoticeDetailInNoticeGraph: (Int) -> Unit = {navController.navigateNoticeToNoticeDetailInNoticeGraph(it)}


    composable(Route.NOTICE_NOTICE_ROUTER_NAME) {
        NoticeRoute(
            modifier,
            navigateNoticeGraphToMyInfoGraph,
            navigateNoticeToNoticeDetailInNoticeGraph
        )

    }
}