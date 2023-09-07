package com.gradation.lift.feature.update_routine.routine_selection

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateRoutineSelectionToRoutine
import com.gradation.lift.navigation.navigation.navigateUpdateRoutineGraphToHomeGraph

@RequiresApi(Build.VERSION_CODES.O)
fun updateRoutineRoutineSelectionScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(Router.UPDATE_ROUTINE_ROUTINE_SELECTION_ROUTER_NAME) {

        val navigateRoutineSelectionToRoutine: () -> Unit =
            { navController.navigateRoutineSelectionToRoutine() }


        val navigateUpdateRoutineGraphToHomeGraph: () -> Unit =
            { navController.navigateUpdateRoutineGraphToHomeGraph() }

        UpdateRoutineRoutineSelectionRoute(
            navController,
            navigateRoutineSelectionToRoutine,
            navigateUpdateRoutineGraphToHomeGraph
        )
    }
}



