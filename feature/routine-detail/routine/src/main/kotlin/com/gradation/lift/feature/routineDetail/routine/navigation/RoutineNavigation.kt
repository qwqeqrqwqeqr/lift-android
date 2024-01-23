package com.gradation.lift.feature.routineDetail.routine.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gradation.lift.navigation.Route.ROUTINE_DETAIL_ROUTINE_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateRoutineDetailGraphToBack
import com.gradation.lift.navigation.navigation.navigateRoutineDetailGraphToUpdateRoutineGraph
import com.gradation.lift.navigation.navigation.navigateRoutineDetailGraphToWorkReadyReadyRoute
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.RoutineSet.DETAIL_ROUTINE_SET_ID_KEY


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

    navGraphBuilder.composable(
        route = "${ROUTINE_DETAIL_ROUTINE_ROUTER_NAME}/{${DETAIL_ROUTINE_SET_ID_KEY}}",
        arguments = listOf(
            navArgument(DETAIL_ROUTINE_SET_ID_KEY) {
                type = NavType.IntType
            },
        ),
    ) {
        RoutineRoute(
            modifier,
            navigateRoutineDetailGraphToBack,
            navigateRoutineDetailGraphToUpdateRoutineGraph,
            navigateRoutineDetailGraphToWorkReadyReadyRoute
        )
    }
}


