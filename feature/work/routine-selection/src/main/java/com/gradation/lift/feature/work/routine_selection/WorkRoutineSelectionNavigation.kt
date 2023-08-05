package com.gradation.lift.feature.work.routine_selection

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.WORK_ROUTINE_SELECTION_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateWorkRoutineSelectionToChangeOrder
import com.gradation.lift.navigation.navigation.navigateWorkToMain
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import com.gradation.lift.navigation.saved_state.getValueSavedStateHandle


@RequiresApi(Build.VERSION_CODES.O)
fun workRoutineSelectionScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
        navGraphBuilder.composable(WORK_ROUTINE_SELECTION_ROUTER_NAME) {
            val previousRoutineSetId =
                navController.getValueSavedStateHandle<Int>(SavedStateHandleKey.WorkKey.ROUTINE_SET_ID_KEY)

            val navigateWorkRoutineSelectionToChangeOrder = {navController.navigateWorkRoutineSelectionToChangeOrder() }
            val navigateWorkToMain = {navController.navigateWorkToMain()}
            WorkRoutineSelectionRoute(
                navController = navController,
                navigateWorkRoutineSelectionToChangeOrder=navigateWorkRoutineSelectionToChangeOrder,
                navigateWorkToMain=navigateWorkToMain,
                previousRoutineSetId = previousRoutineSetId
            )
        }

}
