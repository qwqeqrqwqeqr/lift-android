package com.gradation.lift.feature.ready_work.change_order

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.READY_WORK_CHANGE_ORDER_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateReadyWorkToWorkGraph
import com.gradation.lift.navigation.navigation.navigateToReadyWorkSelection
import com.gradation.lift.navigation.route.ready_work.ReadyWorkChangeOrderRoute
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import com.gradation.lift.navigation.saved_state.getValueSavedStateHandle


fun readyWorkChangeOrderScreen(
    navController : NavController,
    navGraphBuilder: NavGraphBuilder,
) {

    ReadyWorkChangeOrderRoute { route ->
        navGraphBuilder.composable(route) {
            val selectedRoutineSetIdList =
                navController.getValueSavedStateHandle<List<Int>>(SavedStateHandleKey.WorkKey.SELECTED_ROUTINE_SET_KEY)

            val navigateToReadyWorkSelection = { navController.navigateToReadyWorkSelection()}
            val navigateReadyWorkToWorkGraph = { navController.navigateReadyWorkToWorkGraph()}

            ReadyWorkChangeOrderRoute(
                navController = navController,
                navigateToReadyWorkSelection=navigateToReadyWorkSelection,
                navigateReadyWorkToWorkGraph=navigateReadyWorkToWorkGraph,
                selectedRoutineSetIdList=selectedRoutineSetIdList
            )
        }
    }.readyWorkChangeOrderScreen(route = READY_WORK_CHANGE_ORDER_ROUTER_NAME)
}
