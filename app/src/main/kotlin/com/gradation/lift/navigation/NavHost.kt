package com.gradation.lift.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.gradation.ift.feature.updateRoutine.navigation.updateRoutineGraphBuilder
import com.gradation.lift.feature.analytics.navigation.analyticsGraphBuilder
import com.gradation.lift.feature.badge.navigation.badgeGraphBuilder
import com.gradation.lift.feature.createRoutine.navigation.createRoutineGraphBuilder
import com.gradation.lift.feature.history.navigation.historyGraphBuilder
import com.gradation.lift.feature.home.navigation.homeGraphBuilder
import com.gradation.lift.feature.login.navigation.loginGraphBuilder
import com.gradation.lift.feature.myInfo.navigation.myInfoGraphBuilder
import com.gradation.lift.feature.notice.navigation.noticeGraphBuilder
import com.gradation.lift.feature.registerDetail.navigation.registerDetailGraphBuilder
import com.gradation.lift.feature.routineDetail.navigation.routineDetailGraphBuilder
import com.gradation.lift.feature.work.navigation.workGraphBuilder
import com.gradation.lift.feature.workReady.navigation.workReadyGraphBuilder


@Composable
fun LiftNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String,
    navigateToOssScreen: () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None },
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
    ) {
        badgeGraphBuilder(modifier, navController)
        createRoutineGraphBuilder(modifier, navController)
        homeGraphBuilder(modifier, navController)
        loginGraphBuilder(modifier, navController)
        myInfoGraphBuilder(modifier, navController, navigateToOssScreen)
        noticeGraphBuilder(modifier, navController)
        registerDetailGraphBuilder(modifier, navController)
        updateRoutineGraphBuilder(modifier, navController)
        workGraphBuilder(modifier, navController)
        workReadyGraphBuilder(modifier, navController)
        routineDetailGraphBuilder(modifier, navController)
        historyGraphBuilder(modifier, navController)
        analyticsGraphBuilder(modifier, navController)
    }
}






