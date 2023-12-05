package com.gradation.lift.feature.routine_detail.routine

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.ROUTINE_DETAIL_ROUTINE_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateRoutineDetailGraphToUpdateRoutineGraph
import com.gradation.lift.navigation.navigation.navigateRoutineDetailGraphToWorkWorkRouter
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.RoutineSet.DETAIL_ROUTINE_SET_ID_KEY
import com.gradation.lift.navigation.saved_state.getValueSavedStateHandle


fun routineDetailRoutineScreen(
    modifier: Modifier= Modifier,
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    val currentRoutineSetId: Int? =
        navController.getValueSavedStateHandle(DETAIL_ROUTINE_SET_ID_KEY)

    val popBackStack: () -> Unit = { navController.popBackStack() }

    val navigateRoutineDetailGraphToUpdateRoutineGraph: (Int) -> Unit =
        { navController.navigateRoutineDetailGraphToUpdateRoutineGraph(it) }
    val navigateRoutineDetailGraphToWorkWorkRouter: (Int) -> Unit =
        { navController.navigateRoutineDetailGraphToWorkWorkRouter(it) }

    navGraphBuilder.composable(ROUTINE_DETAIL_ROUTINE_ROUTER_NAME) {
        RoutineDetailRoutineRoute(
            modifier,
            currentRoutineSetId,
            popBackStack,
            navigateRoutineDetailGraphToUpdateRoutineGraph,
            navigateRoutineDetailGraphToWorkWorkRouter
        )
    }
}


