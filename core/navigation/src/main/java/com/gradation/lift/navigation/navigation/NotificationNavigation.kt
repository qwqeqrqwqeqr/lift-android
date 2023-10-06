package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Router

fun NavController.navigateNotificationGraphToPreGraph() {
    this.popBackStack()
}


fun NavController.navigatePreGraphToNotificationGraph() {
    this.navigate(Router.NOTIFICATION_NOTIFICATION_ROUTER_NAME)
}

fun NavController.navigateNotificationToNoticeDetailInNotificationGraph() {
    this.navigate(Router.NOTIFICATION_NOTICE_DETAIL_ROUTER_NAME)
}

fun NavController.navigateNotificationToPushDetailInNotificationGraph() {
    this.navigate(Router.NOTIFICATION_PUSH_DETAIL_ROUTER_NAME)
}


fun NavController.navigateNoticeDetailToNotificationInNotificationGraph() {
    this.popBackStack()
}

fun NavController.navigatePushDetailToNotificationInNotificationGraph() {
    this.popBackStack()
}
