package com.gradation.lift

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.*
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.tracing.trace
import com.gradation.lift.designsystem.resource.Icon
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.feature.history.HISTORY_ROUTER_NAME
import com.gradation.lift.feature.history.navigateToHistory
import com.gradation.lift.feature.home.HOME_ROUTER_NAME
import com.gradation.lift.feature.home.navigateToHome
import com.gradation.lift.feature.my_info.MY_INFO_ROUTER_NAME
import com.gradation.lift.feature.my_info.navigateToMyInfo
import com.gradation.lift.feature.routine.ROUTINE_ROUTER_NAME
import com.gradation.lift.feature.routine.navigateToRoutine
import com.gradation.lift.navigation.*
import kotlinx.coroutines.CoroutineScope

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(
    ExperimentalMaterial3Api::class,
)
@Composable
fun LiftApp(
    appState: AppState = rememberAppState()
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            bottomBar = {
                if(appState.currentTopLevelDestination!=null){
                    BottomBar(
                        destinations = appState.topLevelDestinations,
                        onNavigateToDestination = appState::navigateToTopLevelDestination,
                        currentDestination = appState.currentDestination,
                    )
                }
            },
        ) {
            NavHost(appState.navController)
        }
    }
}


@Composable
fun rememberAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): AppState {
    return remember(navController, coroutineScope) {
        AppState(navController, coroutineScope)
    }
}


@Stable
class AppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
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
            val topLevelNavOptions = navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }

            when (topLevelDestination) {
                TopLevelNavDestination.Home -> navController.navigateToHome(topLevelNavOptions)
                TopLevelNavDestination.Routine -> navController.navigateToRoutine(topLevelNavOptions)
                TopLevelNavDestination.History -> navController.navigateToHistory(topLevelNavOptions)
                TopLevelNavDestination.MyInfo -> navController.navigateToMyInfo(topLevelNavOptions)
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
    NavigationBar(
        modifier = modifier,
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            NavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    val icon = if (selected) {
                        destination.selectedIcon
                    } else {
                        destination.unselectedIcon
                    }
                    when (icon) {
                        is Icon.ImageVectorIcon -> Icon(
                            imageVector = icon.imageVector,
                            contentDescription = null,
                        )

                        is Icon.DrawableResourceIcon -> Icon(
                            painter = painterResource(id = icon.id),
                            contentDescription = null,
                        )
                    }
                },
                label = { Text(stringResource(destination.displayName)) },
            )
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen1(navController: NavController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Screen 1") }) },
        content = {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Button(onClick = { navController.navigate("child1") }) {
                    Text(text = "Go to Child of Screen 1")
                }
            }
        })
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Child1(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Screen 1") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = LiftIcon.ArrowBack, contentDescription = null)
                    }
                })
        },
        content = {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("This is the Child of Screen 1")
            }
        })
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen2(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Screen 2") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = LiftIcon.ArrowBack, contentDescription = null)
                    }
                })
        },
        content = {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Screen 2")
            }
        })
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen3(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Screen 3") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = LiftIcon.ArrowBack, contentDescription = null)
                    }
                })
        },
        content = {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Screen 3")
            }
        })
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen4(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Screen 4") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = LiftIcon.ArrowBack, contentDescription = null)
                    }
                })
        },
        content = {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Screen 4")
            }
        })
}
