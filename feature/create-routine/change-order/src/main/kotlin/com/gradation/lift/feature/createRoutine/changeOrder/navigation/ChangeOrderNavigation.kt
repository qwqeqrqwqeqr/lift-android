package com.gradation.lift.feature.createRoutine.changeOrder.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavController
import com.gradation.lift.navigation.Route.CREATE_ROUTINE_CHANGE_ORDER_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateChangeOrderToRoutineSetInCreateRoutineGraph


fun NavGraphBuilder.changeOrderScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val navigateChangeOrderToRoutineSetInCreateRoutineGraph: () -> Unit =
        { navController.navigateChangeOrderToRoutineSetInCreateRoutineGraph() }


    composable(CREATE_ROUTINE_CHANGE_ORDER_ROUTER_NAME) {
        ChangeOrderRoute(
            modifier,
            navController,
            navigateChangeOrderToRoutineSetInCreateRoutineGraph,
        )
    }

}
