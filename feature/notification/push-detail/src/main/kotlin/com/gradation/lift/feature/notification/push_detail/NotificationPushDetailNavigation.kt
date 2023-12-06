package com.gradation.lift.feature.notification.push_detail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router

fun notificationPushDetailScreen(navController: NavController, navGraphBuilder: NavGraphBuilder) {
    navGraphBuilder.composable(Router.NOTIFICATION_PUSH_DETAIL_ROUTER_NAME) {


        NotificationPushDetailRoute(
        )

    }
}