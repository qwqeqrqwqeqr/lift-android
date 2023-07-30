package com.gradation.lift.feature.ready_work.selection

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.READY_WORK_SELECTION_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateReadyWorkToMain
import com.gradation.lift.navigation.navigation.navigateToReadyWorkSelectionToChangeOrder
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import com.gradation.lift.navigation.saved_state.getValueSavedStateHandle


@RequiresApi(Build.VERSION_CODES.O)
fun readyWorkSelectionScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
        navGraphBuilder.composable(READY_WORK_SELECTION_ROUTER_NAME) {
            val previousRoutineSetId =
                navController.getValueSavedStateHandle<Int>(SavedStateHandleKey.WorkKey.ROUTINE_SET_ID_KEY)

            val navigateToReadyWorkChangeOrder = {navController.navigateToReadyWorkSelectionToChangeOrder() }
            val navigateReadyWorkToMain = {navController.navigateReadyWorkToMain()}
            ReadyWorkSelectionRoute(
                navController = navController,
                navigateToReadyWorkChangeOrder=navigateToReadyWorkChangeOrder,
                navigateReadyWorkToMain=navigateReadyWorkToMain,
                previousRoutineSetId = previousRoutineSetId
            )
        }

}
