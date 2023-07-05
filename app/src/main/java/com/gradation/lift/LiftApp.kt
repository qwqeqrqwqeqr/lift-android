package com.gradation.lift

import LiftNavHost
import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.*
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.tracing.trace
import com.gradation.lift.designsystem.component.LiftNavigationBar
import com.gradation.lift.designsystem.component.LiftNavigationBarItem
import com.gradation.lift.navigation.*
import com.gradation.lift.navigation.Router.HISTORY_ROUTER_NAME
import com.gradation.lift.navigation.Router.HOME_ROUTER_NAME
import com.gradation.lift.navigation.Router.LOGIN_GRAPH_ROUTER_NAME
import com.gradation.lift.navigation.Router.MY_INFO_ROUTER_NAME
import com.gradation.lift.navigation.Router.REGISTER_DETAIL_GRAPH_ROUTER_NAME

import com.gradation.lift.navigation.Router.ROUTINE_ROUTER_NAME
import com.gradation.lift.navigation.graph.TopLevelNavDestination
import com.gradation.lift.navigation.graph.isTopLevelDestinationInHierarchy
import com.gradation.lift.navigation.navigation.navigateToHistory
import com.gradation.lift.navigation.navigation.navigateToHome
import com.gradation.lift.navigation.navigation.navigateToMyInfo
import com.gradation.lift.navigation.navigation.navigateToRoutine
import com.gradation.lift.navigation.route.register_detail.RegisterDetailGraph

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@OptIn(
    ExperimentalMaterial3Api::class,
)
@Composable
fun LiftApp(
    isSinged: Boolean,
    windowSizeClass: WindowSizeClass,
    appState: AppState = rememberAppState(
        windowSizeClass = windowSizeClass
    ),
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        if(isSinged){
            Scaffold(
                bottomBar = {
                    if (appState.currentTopLevelDestination != null) {
                        BottomBar(
                            destinations = appState.topLevelDestinations,
                            onNavigateToDestination = appState::navigateToTopLevelDestination,
                            currentDestination = appState.currentDestination,
                        )
                    }
                },
            ) {
                LiftNavHost(
                    navController = appState.navController,
                    startDestination = REGISTER_DETAIL_GRAPH_ROUTER_NAME
                )
            }
        }
        else{
            LiftNavHost(
                navController = appState.navController,
                startDestination = LOGIN_GRAPH_ROUTER_NAME
            )
        }

    }
}


@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    windowSizeClass: WindowSizeClass,

    ): AppState {
    return remember(navController) {
        AppState(navController, windowSizeClass)
    }
}


@Stable
class AppState(
    val navController: NavHostController,
    val windowSizeClass: WindowSizeClass,
) {

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination


    val currentTopLevelDestination
        @Composable get() = when (currentDestination?.route) {
            HOME_ROUTER_NAME -> TopLevelNavDestination.Home
            ROUTINE_ROUTER_NAME -> TopLevelNavDestination.Routine
            HISTORY_ROUTER_NAME -> TopLevelNavDestination.History
            MY_INFO_ROUTER_NAME -> TopLevelNavDestination.MyInfo
            else -> null
        }


    val topLevelDestinations: List<TopLevelNavDestination> =
        TopLevelNavDestination.values().asList()


    fun navigateToTopLevelDestination(topLevelDestination: TopLevelNavDestination) {
        trace("Navigation: ${topLevelDestination.name}") {
            when (topLevelDestination) {
                TopLevelNavDestination.Home -> navController.navigateToHome()
                TopLevelNavDestination.Routine -> navController.navigateToRoutine()
                TopLevelNavDestination.History -> navController.navigateToHistory()
                TopLevelNavDestination.MyInfo -> navController.navigateToMyInfo()
            }
        }
    }
}


@Composable
private fun BottomBar(
    destinations: List<TopLevelNavDestination>,
    onNavigateToDestination: (TopLevelNavDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    LiftNavigationBar(
        modifier = modifier,
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

