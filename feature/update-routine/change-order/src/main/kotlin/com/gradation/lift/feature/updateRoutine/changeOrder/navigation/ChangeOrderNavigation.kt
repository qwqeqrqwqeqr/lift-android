package com.gradation.lift.feature.updateRoutine.changeOrder.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavController
import com.gradation.lift.navigation.Route.UPDATE_ROUTINE_CHANGE_ORDER_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateChangeOrderToRoutineSetInUpdateRoutineGraph


fun NavGraphBuilder.changeOrderScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val navigateChangeOrderToRoutineSetInUpdateRoutineGraph: () -> Unit =
        { navController.navigateChangeOrderToRoutineSetInUpdateRoutineGraph() }


    composable(UPDATE_ROUTINE_CHANGE_ORDER_ROUTER_NAME) {
        ChangeOrderRoute(
            modifier,
            navController,
            navigateChangeOrderToRoutineSetInUpdateRoutineGraph,
        )
    }

}
