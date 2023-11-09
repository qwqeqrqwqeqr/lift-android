package com.gradation.lift.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.gradation.ift.feature.updateRoutine.navigation.updateRoutineGraphBuilder
import com.gradation.lift.feature.badge.navigation.badgeGraphBuilder
import com.gradation.lift.feature.badge.navigation.newBadgeGraphBuilder
import com.gradation.lift.feature.createRoutine.navigation.createRoutineGraphBuilder
import com.gradation.lift.feature.history.navigation.historyGraphBuilder
import com.gradation.lift.feature.home.navigation.homeGraphBuilder
import com.gradation.lift.feature.login.navigation.loginGraphBuilder
import com.gradation.lift.feature.myInfo.navigation.myInfoGraphBuilder
import com.gradation.lift.feature.registerDetail.navigation.registerDetailGraphBuilder
import com.gradation.lift.feature.routineDetail.navigation.routineDetailGraphBuilder
import com.gradation.lift.feature.work.navigation.workGraphBuilder
import com.gradation.lift.notification.feature.navigation.notificationGraphBuilder
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
        routineDetailGraphBuilder(navController,this)
    }
}






