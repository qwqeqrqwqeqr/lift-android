package com.gradation.lift.navigation.graph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.update_routine.routine.updateRoutineRoutineScreen
import com.gradation.lift.feature.update_routine.routine_selection.updateRoutineRoutineSelectionScreen
import com.gradation.lift.navigation.Router.UPDATE_ROUTINE_GRAPH_NAME
import com.gradation.lift.navigation.Router.UPDATE_ROUTINE_ROUTINE_SELECTION_ROUTER_NAME

@RequiresApi(Build.VERSION_CODES.O)
fun updateRoutineGraphBuilder(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.navigation(
        route = UPDATE_ROUTINE_GRAPH_NAME,
        startDestination = UPDATE_ROUTINE_ROUTINE_SELECTION_ROUTER_NAME,
    ) {
        updateRoutineRoutineSelectionScreen(navController, this)
        updateRoutineRoutineScreen(navController, this)
    }
}




