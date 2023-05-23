package com.gradation.lift.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.gradation.lift.feature.history.historyScreen
import com.gradation.lift.feature.home.HOME_ROUTER_NAME
import com.gradation.lift.feature.home.homeScreen
import com.gradation.lift.feature.my_info.myInfoScreen
import com.gradation.lift.feature.routine.routineScreen

@Composable
fun LiftNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = HOME_ROUTER_NAME,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        homeScreen()
        routineScreen()
        historyScreen()
        myInfoScreen()

    }
}