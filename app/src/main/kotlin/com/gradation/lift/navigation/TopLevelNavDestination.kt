package com.gradation.lift.navigation

import com.gradation.lift.designsystem.resource.LiftIcon

enum class TopLevelNavDestination(
    val displayName: String,
    val icon: Int,
    val route: String,
) {
    Home(
        displayName = "홈",
        icon = LiftIcon.Home,
        route = Route.HOME_HOME_ROUTER_NAME
    ),
    DailyLog(
        displayName = "기록",
        icon = LiftIcon.History,
        route = Route.HISTORY_HISTORY_ROUTER_NAME
    ),
    Analytics(
        displayName = "분석",
        icon = LiftIcon.Analytics,
        route = Route.ANALYTICS_ANALYTICS_ROUTER_NAME
    ),
    MyInfo(
        displayName = "내정보",
        icon = LiftIcon.MyInfo,
        route = Route.MY_INFO_MY_INFO_ROUTER_NAME
    )
}

