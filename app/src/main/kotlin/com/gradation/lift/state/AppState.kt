package com.gradation.lift.state

import androidx.compose.animation.animateColorAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.tracing.trace
import com.google.accompanist.systemuicontroller.SystemUiController
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.navigation.Route
import com.gradation.lift.navigation.TopLevelNavDestination
import com.gradation.lift.navigation.navigation.navigateHistoryGraph
import com.gradation.lift.navigation.navigation.navigateHomeGraph
import com.gradation.lift.navigation.navigation.navigateMyInfoGraph

class AppState(
    val navController: NavHostController,
    val currentDestination: NavDestination?,
) {
    val isShowBottomBar: Boolean
        @Composable get() = currentDestination?.let { destination ->
            (destination.route in topLevelDestinations.map { it.route })
        } ?: false

    val topLevelDestinations: List<TopLevelNavDestination> =
        TopLevelNavDestination.entries


    fun navigateToTopLevelDestination(): (TopLevelNavDestination) -> Unit = { topLevelDestination ->
        trace("Navigation: ${topLevelDestination.name}") {
            when (topLevelDestination) {
                TopLevelNavDestination.Home -> navController.navigateHomeGraph()
                TopLevelNavDestination.MyInfo -> navController.navigateMyInfoGraph()
                TopLevelNavDestination.Analytics -> {}
                TopLevelNavDestination.DailyLog -> navController.navigateHistoryGraph()
            }
        }
    }
}


@Composable
fun rememberAppState(
    navController: NavHostController,
    systemUiController: SystemUiController,
): AppState {

    val currentDestination: NavDestination? =
        navController.currentBackStackEntryAsState().value?.destination

    val statusBarColor: Color by animateColorAsState(
        targetValue = when (currentDestination?.route) {
            Route.HOME_HOME_ROUTER_NAME -> LiftTheme.colorScheme.no31
            else -> LiftTheme.colorScheme.no5
        }, label = "statusBarColor"
    )

    val navigationBarColor: Color by animateColorAsState(
        targetValue = when (currentDestination?.route) {
            Route.HOME_HOME_ROUTER_NAME -> LiftTheme.colorScheme.no5
            else -> LiftTheme.colorScheme.no5
        }, label = "navigationBarColor"
    )


    systemUiController.setStatusBarColor(color = statusBarColor)
    systemUiController.setNavigationBarColor(color = navigationBarColor)

    return remember(navController, currentDestination) {
        AppState(
            navController,
            currentDestination,
        )
    }
}
