package com.gradation.lift.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import com.gradation.lift.designsystem.component.LiftNavigationBar
import com.gradation.lift.designsystem.component.LiftNavigationBarItem
import com.gradation.lift.navigation.graph.TopLevelNavDestination
import com.gradation.lift.navigation.graph.isTopLevelDestinationInHierarchy


@Composable
fun BottomBar(
    destinations: List<TopLevelNavDestination>,
    onNavigateToDestination: (TopLevelNavDestination) -> Unit,
    currentDestination: NavDestination?,
) {
    LiftNavigationBar(

    ) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            LiftNavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                unSelectedIcon = {
                    Icon(
                        painter = painterResource(id = destination.unselectedIcon),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                },
                selectedIcon = {
                    Icon(
                        painter = painterResource(id = destination.selectedIcon),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                },
                label = { Text(stringResource(destination.displayName)) },
            )
        }
    }
}