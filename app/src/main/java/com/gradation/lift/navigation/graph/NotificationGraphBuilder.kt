package com.gradation.lift.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.notification.notice_detail.notificationNoticeDetailScreen
import com.gradation.lift.feature.notification.notification.notificationNotificationScreen
import com.gradation.lift.feature.notification.push_detail.notificationPushDetailScreen
import com.gradation.lift.navigation.Router


fun notificationGraphBuilder(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.navigation(
        route = Router.NOTIFICATION_GRAPH_NAME,
        startDestination = Router.NOTIFICATION_NOTIFICATION_ROUTER_NAME,
    ) {
        notificationNotificationScreen(navController, this)
        notificationPushDetailScreen(navController, this)
        notificationNoticeDetailScreen(navController, this)
    }
}