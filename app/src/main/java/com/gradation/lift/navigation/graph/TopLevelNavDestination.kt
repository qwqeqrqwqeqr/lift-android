package com.gradation.lift.navigation.graph

import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.gradation.lift.R
import com.gradation.lift.designsystem.resource.LiftIcon

enum class TopLevelNavDestination(
    val displayName: Int,
    val selectedIcon: Int,
    val unselectedIcon: Int
) {
    Home(
        displayName = R.string.top_level_nav_destination_item_home,
        selectedIcon = LiftIcon.HomeSelected,
        unselectedIcon = LiftIcon.HomeUnSelected
    ),
    History(
        displayName = R.string.top_level_nav_destination_item_history,
        selectedIcon = LiftIcon.HistorySelected,
        unselectedIcon = LiftIcon.HistoryUnSelected
    ),
    MyInfo(
        displayName = R.string.top_level_nav_destination_item_my_info,
        selectedIcon = LiftIcon.MyInfoSelected,
        unselectedIcon = LiftIcon.MyInfoUnSelected
    )
}


fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelNavDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false

