package com.gradation.lift.feature.notification.notification

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route
import com.gradation.lift.navigation.navigation.navigateNotificationGraphToPreGraph

fun notificationNotificationScreen(navController: NavController, navGraphBuilder: NavGraphBuilder) {
    navGraphBuilder.composable(Route.NOTIFICATION_NOTIFICATION_ROUTER_NAME) {


        val navigateNotificationGraphToPreGraph: () -> Unit =
            { navController.navigateNotificationGraphToPreGraph() }

        NotificationNotificationRoute(
            navController = navController,
            navigateNotificationGraphToPreGraph = navigateNotificationGraphToPreGraph
        )

    }
}