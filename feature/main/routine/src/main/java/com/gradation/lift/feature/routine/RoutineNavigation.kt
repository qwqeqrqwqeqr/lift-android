package com.gradation.lift.feature.routine

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.route.main.RoutineRoute

@RequiresApi(Build.VERSION_CODES.O)
fun routineScreen(navGraphBuilder: NavGraphBuilder) {
    RoutineRoute { route, navigationGraphBuilder ->
        navigationGraphBuilder.composable(route) {
            RoutineRoute()
        }
    }.routineScreen(route = Router.ROUTINE_ROUTER_NAME, navGraphBuilder)
}
