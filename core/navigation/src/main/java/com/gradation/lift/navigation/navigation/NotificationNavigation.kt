package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.gradation.lift.navigation.Router

fun NavController.navigateNotificationGraphToPreGraph() {
    this.popBackStack()
}


fun NavHostController.navigatePreGraphToNotificationGraph() {
    this.navigate(Router.NOTIFICATION_NOTIFICATION_ROUTER_NAME)
}

fun NavHostController.navigateNotificationToNoticeDetailInNotificationGraph() {
    this.navigate(Router.NOTIFICATION_NOTICE_DETAIL_ROUTER_NAME)
}

fun NavHostController.navigateNotificationToPushDetailInNotificationGraph() {
    this.navigate(Router.NOTIFICATION_PUSH_DETAIL_ROUTER_NAME)
}


fun NavHostController.navigateNoticeDetailToNotificationInNotificationGraph() {
    this.popBackStack()
}

fun NavHostController.navigatePushDetailToNotificationInNotificationGraph() {
    this.popBackStack()
}
