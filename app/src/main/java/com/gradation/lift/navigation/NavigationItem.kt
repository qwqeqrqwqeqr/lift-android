package com.gradation.lift.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gradation.lift.Child1
import com.gradation.lift.Screen1
import com.gradation.lift.Screen2
import com.gradation.lift.designsystem.resource.Icon
import com.gradation.lift.designsystem.resource.LiftIcon

enum class TopLevelNavDestination(
    val displayName: String,
    val route: String,
    val selectedIcon: Icon,
    val unselectedIcon: Icon
) {
    Home(
        displayName = "홈",
        route = "home",
        selectedIcon = Icon.DrawableResourceIcon(LiftIcon.HomeSelected),
        unselectedIcon = Icon.DrawableResourceIcon(LiftIcon.HomeUnSelected)
    ),
    Routine(
        displayName = "루틴",
        route = "routine",
        selectedIcon = Icon.DrawableResourceIcon(LiftIcon.MyInfoSelected),
        unselectedIcon = Icon.DrawableResourceIcon(LiftIcon.MyInfoUnSelected)
    ),
    History(
        displayName = "기록",
        route = "history",
        selectedIcon = Icon.DrawableResourceIcon(LiftIcon.MyInfoSelected),
        unselectedIcon = Icon.DrawableResourceIcon(LiftIcon.MyInfoUnSelected)
    ),
    MyInfo(
        displayName = "내정보",
        route = "my-info",
        selectedIcon = Icon.DrawableResourceIcon(LiftIcon.MyInfoSelected),
        unselectedIcon = Icon.DrawableResourceIcon(LiftIcon.MyInfoUnSelected)
    )
}


fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelNavDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false

