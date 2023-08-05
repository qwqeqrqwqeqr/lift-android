package com.gradation.lift.feature.work.change_order

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.WORK_CHANGE_ORDER_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateChangeOrderToWork
import com.gradation.lift.navigation.navigation.navigateWorkChangeOrderToRoutineSelection
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import com.gradation.lift.navigation.saved_state.getValueSavedStateHandle


fun workChangeOrderScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(WORK_CHANGE_ORDER_ROUTER_NAME) {
        val selectedRoutineSetIdList =
            navController.getValueSavedStateHandle<Set<Int>>(SavedStateHandleKey.WorkKey.SELECTED_ROUTINE_SET_KEY)

        val navigateWorkChangeOrderToRoutineSelection = { navController.navigateWorkChangeOrderToRoutineSelection() }
        val navigateChangeOrderToWork = { navController.navigateChangeOrderToWork() }

        WorkChangeOrderRoute(
            navController = navController,
            navigateWorkChangeOrderToRoutineSelection = { navigateWorkChangeOrderToRoutineSelection() },
            navigateChangeOrderToWork = navigateChangeOrderToWork,
            selectedRoutineSetIdList = selectedRoutineSetIdList
        )
    }

}
