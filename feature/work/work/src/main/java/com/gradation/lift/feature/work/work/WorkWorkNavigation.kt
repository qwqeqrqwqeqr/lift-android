package com.gradation.lift.feature.work.work

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.READY_WORK_CHANGE_ORDER_ROUTER_NAME
import com.gradation.lift.navigation.Router.WORK_WORK_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateReadyWorkToWorkGraph
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import com.gradation.lift.navigation.saved_state.getValueSavedStateHandle


fun workWorkScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(WORK_WORK_ROUTER_NAME) {

        val navigateToReadyWorkSelection = { navController.navigateUp() }
        val navigateReadyWorkToWorkGraph = { navController.navigateReadyWorkToWorkGraph() }

        WorkWorkRoute(
            navController = navController,
        )
    }

}
