package com.gradation.lift

import LiftNavHost
import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.navigation.*
import com.gradation.lift.navigation.Router.HISTORY_ROUTER_NAME
import com.gradation.lift.navigation.Router.HOME_ROUTER_NAME
import com.gradation.lift.navigation.Router.LOGIN_GRAPH_NAME
import com.gradation.lift.navigation.Router.MAIN_GRAPH_NAME
import com.gradation.lift.navigation.Router.MY_INFO_ROUTER_NAME
import com.gradation.lift.navigation.Router.REGISTER_DETAIL_GRAPH_NAME

import com.gradation.lift.navigation.graph.TopLevelNavDestination
import com.gradation.lift.navigation.graph.isTopLevelDestinationInHierarchy
import com.gradation.lift.navigation.navigation.navigateToHistory
import com.gradation.lift.navigation.navigation.navigateToHome
import com.gradation.lift.navigation.navigation.navigateToMyInfo
import com.gradation.lift.oauth.state.OAuthSignInState
import com.gradation.lift.state.SplashUiState

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@OptIn(
    ExperimentalMaterial3Api::class,
)
@Composable
fun LiftApp(
    splashUiState: SplashUiState,
    windowSizeClass: WindowSizeClass,
    appState: AppState = rememberAppState(
        windowSizeClass = windowSizeClass
    ),
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    naverOAuthSignInState: OAuthSignInState,
    kakaoOauthSignInState: OAuthSignInState,
    signInNaver: ()->Unit,
    signInKakao: ()->Unit,
) {
    Surface(
        modifier = modifier
            .fillMaxSize()
            .background(LiftTheme.colorScheme.no17)
    ) {
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
            Box(
                modifier = modifier.padding(it)
            ) {
                when (splashUiState) {
                    SplashUiState.Loading -> {}
                    SplashUiState.Login -> LiftNavHost(
                        navController = appState.navController,
                        startDestination = LOGIN_GRAPH_NAME,
                        naverOAuthSignInState =naverOAuthSignInState,
                        kakaoOauthSignInState =kakaoOauthSignInState,
                        signInNaver = { signInNaver() },
                        signInKakao = { signInKakao() }
                    )
                    SplashUiState.Main -> LiftNavHost(
                        navController = appState.navController,
                        startDestination = MAIN_GRAPH_NAME,
                        naverOAuthSignInState =naverOAuthSignInState,
                        kakaoOauthSignInState =kakaoOauthSignInState,
                        signInNaver = { signInNaver() },
                        signInKakao = { signInKakao() }
                    )
                    SplashUiState.RegisterDetail -> LiftNavHost(
                        navController = appState.navController,
                        startDestination = REGISTER_DETAIL_GRAPH_NAME,
                        naverOAuthSignInState =naverOAuthSignInState,
                        kakaoOauthSignInState =kakaoOauthSignInState,
                        signInNaver = { signInNaver() },
                        signInKakao = { signInKakao() }
                    )
                }
            }
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

