package com.gradation.lift.navigation

import com.gradation.lift.designsystem.resource.LiftIcon

enum class TopLevelNavDestination(
    val displayName: String,
    val selectedIcon: Int,
    val unSelectedIcon: Int,
    val route: String,
) {
    Home(
        displayName = "홈",
        selectedIcon = LiftIcon.SelectedHome,
        unSelectedIcon = LiftIcon.UnselectedHome,
        route = Route.HOME_HOME_ROUTER_NAME
    ),
    DailyLog(
        displayName = "기록",
        selectedIcon = LiftIcon.SelectedHistory,
        unSelectedIcon = LiftIcon.UnselectedHistory,
        route = Route.HISTORY_HISTORY_ROUTER_NAME
    ),
    Analytics(
        displayName = "분석",
        selectedIcon = LiftIcon.SelectedAnalytics,
        unSelectedIcon = LiftIcon.UnselectedAnalytics,
        route = Route.ANALYTICS_ANALYTICS_ROUTER_NAME
    ),
    MyInfo(
        displayName = "내정보",
        selectedIcon = LiftIcon.SelectedMyInfo,
        unSelectedIcon = LiftIcon.UnselectedMyInfo,
        route = Route.MY_INFO_MY_INFO_ROUTER_NAME
    )
}

