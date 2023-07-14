package com.gradation.lift.feature.routine

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router

@RequiresApi(Build.VERSION_CODES.O)
fun routineScreen(navController: NavController,navGraphBuilder: NavGraphBuilder) {
    RoutineRoute { route, navigationGraphBuilder ->
        navigationGraphBuilder.composable(route) {
            RoutineRoute(navController = navController)
        }
    }.routineScreen(route = Router.ROUTINE_ROUTER_NAME, navGraphBuilder)
}
