package com.gradation.lift.navigation.graph

import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.gradation.lift.R
import com.gradation.lift.designsystem.resource.Icon
import com.gradation.lift.designsystem.resource.LiftIcon

enum class TopLevelNavDestination(
    val displayName: Int,
    val selectedIcon: Icon,
    val unselectedIcon: Icon
) {
    Home(
        displayName = R.string.top_level_nav_destination_item_home,
        selectedIcon = Icon.DrawableResourceIcon(LiftIcon.HomeSelected),
        unselectedIcon = Icon.DrawableResourceIcon(LiftIcon.HomeUnSelected)
    ),
    Routine(
        displayName = R.string.top_level_nav_destination_item_routine,
        selectedIcon = Icon.DrawableResourceIcon(LiftIcon.RoutineSelected),
        unselectedIcon = Icon.DrawableResourceIcon(LiftIcon.RoutineUnSelected)
    ),
    History(
        displayName = R.string.top_level_nav_destination_item_history,
        selectedIcon = Icon.DrawableResourceIcon(LiftIcon.HistorySelected),
        unselectedIcon = Icon.DrawableResourceIcon(LiftIcon.HistoryUnSelected)
    ),
    MyInfo(
        displayName = R.string.top_level_nav_destination_item_my_info,
        selectedIcon = Icon.DrawableResourceIcon(LiftIcon.MyInfoSelected),
        unselectedIcon = Icon.DrawableResourceIcon(LiftIcon.MyInfoUnSelected)
    )
}


fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelNavDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false

