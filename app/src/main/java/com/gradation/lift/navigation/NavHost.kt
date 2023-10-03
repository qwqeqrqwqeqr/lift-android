package com.gradation.lift.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.gradation.lift.navigation.graph.*
import com.gradation.lift.oauth.common.OAuthConnectState
import kotlinx.coroutines.flow.MutableStateFlow


@Composable
fun LiftNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String,
    naverOAuthConnectState: MutableStateFlow<OAuthConnectState>,
    kakaoOAuthConnectState: MutableStateFlow<OAuthConnectState>,
    connectOAuthFromNaver: () -> Unit,
    connectOAuthFromKakao: () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        badgeGraphBuilder(navController, this)
        createRoutineGraphBuilder(navController, this)
        homeGraphBuilder(navController, this)
        historyGraphBuilder(navController, this)
        loginGraphBuilder(
            navController = navController,
            navGraphBuilder = this,
            naverOAuthConnectState = naverOAuthConnectState,
            kakaoOAuthConnectState = kakaoOAuthConnectState,
            connectOAuthFromNaver = { connectOAuthFromNaver() },
            connectOAuthFromKakao = { connectOAuthFromKakao() }
        )
        myInfoGraphBuilder(navController, this)
        newBadgeGraphBuilder(navController, this)
        notificationGraphBuilder(navController, this)
        registerDetailGraphBuilder(navController, this)
        updateRoutineGraphBuilder(navController, this)
        workGraphBuilder(navController, this)

    }
}






