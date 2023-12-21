package com.gradation.lift

import AppState
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import com.gradation.lift.navigation.*
import com.gradation.lift.navigation.Router.HOME_GRAPH_NAME
import com.gradation.lift.navigation.Router.LOGIN_GRAPH_NAME
import com.gradation.lift.navigation.Router.REGISTER_DETAIL_GRAPH_NAME
import com.gradation.lift.state.SplashState


@Composable
fun LiftApp(
    splashState: SplashState,
    windowSizeClass: WindowSizeClass,
    appState: AppState,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
) {
    Scaffold(
        bottomBar = {
            if (appState.currentTopLevelDestination != null) {
                BottomBar(
                    modifier=modifier,
                    destinations = appState.topLevelDestinations,
                    onNavigateToDestination = appState::navigateToTopLevelDestination,
                    currentDestination = appState.currentDestination,
                )
            }
        },
    ) {
        Surface(
            modifier = modifier.padding(it)
        ) {
            when (splashState) {
                SplashState.Loading -> {}
                SplashState.Login -> LiftNavHost(
                    navController = appState.navController,
                    startDestination = LOGIN_GRAPH_NAME,
                    naverOAuthConnectState = appState.naverOAuthConnectState,
                    kakaoOAuthConnectState = appState.kakaoOAuthConnectState,
                    connectOAuthFromNaver = { appState.connectOAuthFromNaver() },
                    connectOAuthFromKakao = { appState.connectOAuthFromKakao() }
                )
                SplashState.Main -> LiftNavHost(
                    navController = appState.navController,
                    startDestination = HOME_GRAPH_NAME,
                    naverOAuthConnectState = appState.naverOAuthConnectState,
                    kakaoOAuthConnectState = appState.kakaoOAuthConnectState,
                    connectOAuthFromNaver = { appState.connectOAuthFromNaver() },
                    connectOAuthFromKakao = { appState.connectOAuthFromKakao() }
                )
                SplashState.RegisterDetail -> LiftNavHost(
                    navController = appState.navController,
                    startDestination = REGISTER_DETAIL_GRAPH_NAME,
                    naverOAuthConnectState = appState.naverOAuthConnectState,
                    kakaoOAuthConnectState = appState.kakaoOAuthConnectState,
                    connectOAuthFromNaver = { appState.connectOAuthFromNaver() },
                    connectOAuthFromKakao = { appState.connectOAuthFromKakao() }
                )
            }
        }
    }
}








