package com.gradation.lift.notification.feature.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.notification.notice_detail.notificationNoticeDetailScreen
import com.gradation.lift.feature.notification.notification.notificationNotificationScreen
import com.gradation.lift.feature.notification.push_detail.notificationPushDetailScreen
import com.gradation.lift.navigation.Route


fun notificationGraphBuilder(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.navigation(
        route = Route.NOTIFICATION_GRAPH_NAME,
        startDestination = Route.NOTIFICATION_NOTIFICATION_ROUTER_NAME,
    ) {
        notificationNotificationScreen(navController, this)
        notificationPushDetailScreen(navController, this)
        notificationNoticeDetailScreen(navController, this)
    }
}