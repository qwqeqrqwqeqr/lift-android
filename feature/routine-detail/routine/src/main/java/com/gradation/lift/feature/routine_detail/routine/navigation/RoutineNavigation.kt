package com.gradation.lift.feature.routine_detail.routine.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.ROUTINE_DETAIL_ROUTINE_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateRoutineDetailGraphToUpdateRoutineGraph
import com.gradation.lift.navigation.navigation.navigateRoutineDetailGraphToWorkWorkRouter


fun routineScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    val popBackStack: () -> Unit = { navController.popBackStack() }

    val navigateRoutineDetailGraphToUpdateRoutineGraph: (Int) -> Unit =
        { navController.navigateRoutineDetailGraphToUpdateRoutineGraph(it) }
    val navigateRoutineDetailGraphToWorkWorkRouter: (Int) -> Unit =
        { navController.navigateRoutineDetailGraphToWorkWorkRouter(it) }

    navGraphBuilder.composable(ROUTINE_DETAIL_ROUTINE_ROUTER_NAME) {
        RoutineRoute(
            modifier,
            navController,
            popBackStack,
            navigateRoutineDetailGraphToUpdateRoutineGraph,
            navigateRoutineDetailGraphToWorkWorkRouter
        )
    }
}


