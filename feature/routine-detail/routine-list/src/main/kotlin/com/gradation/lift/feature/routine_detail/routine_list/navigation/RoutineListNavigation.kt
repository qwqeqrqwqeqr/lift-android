package com.gradation.lift.feature.routine_detail.routine_list.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.ROUTINE_DETAIL_ROUTINE_LIST_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateRoutineDetailGraphToCreateRoutineGraph
import com.gradation.lift.navigation.navigation.navigateRoutineDetailGraphToHomeGraph
import com.gradation.lift.navigation.navigation.navigateRoutineListToRoutineInRoutineDetailGraph


fun routineListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    val navigateRoutineDetailGraphToHomeGraph: () -> Unit =
        { navController.navigateRoutineDetailGraphToHomeGraph() }

    val navigateRoutineDetailGraphToCreateRoutineGraph : () -> Unit =
        {navController.navigateRoutineDetailGraphToCreateRoutineGraph()}

    val navigateRoutineListToRoutineInRoutineDetailGraph: (Int) -> Unit =
        { navController.navigateRoutineListToRoutineInRoutineDetailGraph(it) }



    navGraphBuilder.composable(ROUTINE_DETAIL_ROUTINE_LIST_ROUTER_NAME) {
        RoutineListRoute(
            modifier,
            navigateRoutineDetailGraphToHomeGraph,
            navigateRoutineDetailGraphToCreateRoutineGraph,
            navigateRoutineListToRoutineInRoutineDetailGraph
        )
    }
}


