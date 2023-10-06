package com.gradation.lift.feature.notification.notice_detail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateNoticeDetailToNotificationInNotificationGraph

fun notificationNoticeDetailScreen(navController: NavController, navGraphBuilder: NavGraphBuilder) {
    navGraphBuilder.composable(Router.NOTIFICATION_NOTICE_DETAIL_ROUTER_NAME) {

        val navigateNoticeDetailToNotificationInNotificationGraph: () -> Unit =
            { navController.navigateNoticeDetailToNotificationInNotificationGraph() }

        NotificationNoticeDetailRoute(
            navController,navigateNoticeDetailToNotificationInNotificationGraph
        )

    }
}