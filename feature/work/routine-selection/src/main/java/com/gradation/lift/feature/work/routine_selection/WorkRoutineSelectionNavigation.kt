package com.gradation.lift.feature.work.routine_selection

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.model.routine.RoutineSetRoutine
import com.gradation.lift.navigation.Router.WORK_ROUTINE_SELECTION_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateSelectionRoutineToWork
import com.gradation.lift.navigation.navigation.navigateWorkToMain
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import com.gradation.lift.navigation.saved_state.getValueSavedStateHandle


@RequiresApi(Build.VERSION_CODES.O)
fun workRoutineSelectionScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
        navGraphBuilder.composable(WORK_ROUTINE_SELECTION_ROUTER_NAME) {
            val selectedRoutineSetId =
                navController.getValueSavedStateHandle<Int>(SavedStateHandleKey.WorkKey.ROUTINE_SET_ROUTINE_KEY)

            val navigateSelectionRoutineToWork = {navController.navigateSelectionRoutineToWork() }
            val navigateWorkToMain = {navController.navigateWorkToMain()}
            WorkRoutineSelectionRoute(
                navController = navController,
                navigateSelectionRoutineToWork=navigateSelectionRoutineToWork,
                navigateWorkToMain=navigateWorkToMain,
                selectedRoutineSetId = selectedRoutineSetId
            )
        }

}
