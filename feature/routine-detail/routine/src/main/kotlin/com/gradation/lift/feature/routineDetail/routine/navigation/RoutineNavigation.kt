package com.gradation.lift.feature.routineDetail.routine.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route.ROUTINE_DETAIL_ROUTINE_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateRoutineDetailGraphToBack
import com.gradation.lift.navigation.navigation.navigateRoutineDetailGraphToUpdateRoutineGraph
import com.gradation.lift.navigation.navigation.navigateRoutineDetailGraphToWorkReadyReadyRoute


fun routineScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {

    val navigateRoutineDetailGraphToUpdateRoutineGraph: (Int) -> Unit =
        { navController.navigateRoutineDetailGraphToUpdateRoutineGraph(it) }
    val navigateRoutineDetailGraphToWorkReadyReadyRoute: (String) -> Unit = {
        navController.navigateRoutineDetailGraphToWorkReadyReadyRoute(it)
    }


    val navigateRoutineDetailGraphToBack: () -> Unit =
        { navController.navigateRoutineDetailGraphToBack() }

    navGraphBuilder.composable(ROUTINE_DETAIL_ROUTINE_ROUTER_NAME) {
        RoutineRoute(
            modifier,
            navController,
            navigateRoutineDetailGraphToBack,
            navigateRoutineDetailGraphToUpdateRoutineGraph,
            navigateRoutineDetailGraphToWorkReadyReadyRoute
        )
    }
}


