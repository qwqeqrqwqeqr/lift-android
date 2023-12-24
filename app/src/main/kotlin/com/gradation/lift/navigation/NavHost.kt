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


@Composable
fun LiftNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        badgeGraphBuilder(navController, this)
        createRoutineGraphBuilder(modifier,navController, this)
        homeGraphBuilder(navController, this)
        historyGraphBuilder(navController, this)
        loginGraphBuilder(modifier,navController, this)
        myInfoGraphBuilder(navController, this)
        newBadgeGraphBuilder(navController, this)
        notificationGraphBuilder(navController, this)
        registerDetailGraphBuilder(navController, this)
        updateRoutineGraphBuilder(modifier,navController, this)
        workGraphBuilder(modifier,navController, this)
        routineDetailGraphBuilder(modifier,navController,this)
    }
}






