package com.gradation.lift.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.gradation.lift.navigation.routor.createRoutineGraph
import com.gradation.lift.navigation.routor.historyScreen
import com.gradation.lift.navigation.routor.HOME_ROUTER_NAME
import com.gradation.lift.navigation.routor.homeScreen
import com.gradation.lift.navigation.routor.myInfoScreen
import com.gradation.lift.navigation.routor.routineScreen

@RequiresApi(Build.VERSION_CODES.O)
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
        createRoutineGraph(
            nestedGraphs = {},
        )
    }
}


