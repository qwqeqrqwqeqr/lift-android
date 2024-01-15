package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Route
import com.gradation.lift.navigation.Route.NOTICE_NOTICE_DETAIL_ROUTER_NAME
import com.gradation.lift.navigation.Route.NOTICE_NOTICE_ROUTER_NAME


fun NavController.navigateNoticeGraphToMyInfoGraph() {
    this.navigate(Route.MY_INFO_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(this@navigateNoticeGraphToMyInfoGraph.graph.id) {
            inclusive = true
        }
    }
}

fun NavController.navigateNoticeToNoticeDetailInNoticeGraph(noticeId: Int) {
    navigate("$NOTICE_NOTICE_DETAIL_ROUTER_NAME/$noticeId")
}

fun NavController.navigateNoticeDetailToNoticeInNoticeGraph() {
    this.navigate(NOTICE_NOTICE_ROUTER_NAME) {
        popUpTo(NOTICE_NOTICE_ROUTER_NAME) {
            inclusive = true
        }
    }
}