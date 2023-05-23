package com.gradation.lift.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gradation.lift.feature.history.HISTORY_ROUTER_NAME
import com.gradation.lift.feature.history.HistoryScreen
import com.gradation.lift.feature.home.HOME_ROUTER_NAME
import com.gradation.lift.feature.home.homeScreen
import com.gradation.lift.feature.my_info.myInfoScreen
import com.gradation.lift.feature.routine.ROUTINE_ROUTER_NAME
import com.gradation.lift.feature.routine.RoutineScreen

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
        composable(ROUTINE_ROUTER_NAME) {
            RoutineScreen(navController)
        }
        composable(HISTORY_ROUTER_NAME) {
            HistoryScreen(navController)
        }
        myInfoScreen()

    }
}