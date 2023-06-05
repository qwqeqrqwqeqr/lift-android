package com.gradation.lift.feature.routine

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.gradation.lift.feature.routine.RoutineRoute
import com.gradation.lift.navigation.routor.ROUTINE_ROUTER_NAME


@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.routineScreen() {
    composable(route = ROUTINE_ROUTER_NAME){
        RoutineRoute()
    }
}