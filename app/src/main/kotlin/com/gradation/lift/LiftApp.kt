package com.gradation.lift

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.navigation.BottomBar
import com.gradation.lift.navigation.LiftNavHost
import com.gradation.lift.navigation.Route.HOME_GRAPH_NAME
import com.gradation.lift.navigation.Route.LOGIN_GRAPH_NAME
import com.gradation.lift.navigation.Route.REGISTER_DETAIL_GRAPH_NAME
import com.gradation.lift.state.AppState
import com.gradation.lift.state.SplashState


@Composable
fun LiftApp(
    splashState: SplashState,
    windowSizeClass: WindowSizeClass,
    appState: AppState,
    modifier: Modifier = Modifier,
    navigateToOssScreen: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(LiftTheme.colorScheme.no17)
    ) {
        Column(
            modifier = modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            when (splashState) {
                SplashState.Loading -> {}
                SplashState.Login -> LiftNavHost(
                    modifier = modifier,
                    navController = appState.navController,
                    startDestination = LOGIN_GRAPH_NAME,
                    navigateToOssScreen = navigateToOssScreen
                )

                SplashState.Main -> LiftNavHost(
                    modifier = modifier,
                    navController = appState.navController,
                    startDestination = HOME_GRAPH_NAME,
                    navigateToOssScreen = navigateToOssScreen
                )

                SplashState.RegisterDetail -> LiftNavHost(
                    modifier = modifier,
                    navController = appState.navController,
                    startDestination = REGISTER_DETAIL_GRAPH_NAME,
                    navigateToOssScreen = navigateToOssScreen
                )
            }
        }
        AnimatedVisibility(
            visible = appState.isShowBottomBar,
            enter = slideInVertically(
                initialOffsetY = { 300 },
                animationSpec = tween(
                    durationMillis = 200,
                    easing = LinearEasing
                )
            ),
            exit = slideOutVertically(
                targetOffsetY = { 300 },
                animationSpec = tween(
                    durationMillis = 200,
                    easing = LinearEasing
                )
            )
        ) {
            Column(
                modifier = modifier.padding(
                    bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
                )
            ) {
                BottomBar(
                    modifier = modifier,
                    destinations = appState.topLevelDestinations,
                    onNavigateToDestination = appState.navigateToTopLevelDestination(),
                    currentDestination = appState.currentDestination,
                )
            }
        }
    }
}








