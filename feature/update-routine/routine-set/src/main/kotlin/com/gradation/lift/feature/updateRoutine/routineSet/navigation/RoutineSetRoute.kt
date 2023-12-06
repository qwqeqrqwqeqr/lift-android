package com.gradation.lift.feature.updateRoutine.routineSet.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.feature.updateRoutine.common.data.state.RoutineUiState
import com.gradation.lift.feature.updateRoutine.common.data.UpdateRoutineSharedViewModel
import com.gradation.lift.feature.updateRoutine.routineSet.data.state.RoutineSetScreenState
import com.gradation.lift.feature.updateRoutine.routineSet.data.RoutineSetViewModel
import com.gradation.lift.feature.updateRoutine.routineSet.data.state.UpdateRoutineState
import com.gradation.lift.feature.updateRoutine.routineSet.data.state.rememberRoutineSetScreenState
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.navigation.Router.UPDATE_ROUTINE_GRAPH_NAME
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import com.gradation.lift.navigation.saved_state.getValueSavedStateHandle

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
internal fun RoutineSetRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    popBackStack: () -> Unit,
    navigateRoutineSetToFindWorkCategoryInUpdateRoutineGraph: () -> Unit,
    navigateRoutineSetToProfilePictureInUpdateRoutineGraph: () -> Unit,
    viewModel: RoutineSetViewModel = hiltViewModel(),
    sharedViewModel: UpdateRoutineSharedViewModel = hiltViewModel(remember {
        navController.getBackStackEntry(
            UPDATE_ROUTINE_GRAPH_NAME
        )
    }),
    routineSetScreenState: RoutineSetScreenState = rememberRoutineSetScreenState()
) {
    with(navController.getValueSavedStateHandle<Int>(SavedStateHandleKey.RoutineSet.DETAIL_ROUTINE_SET_ID_KEY)) {
        sharedViewModel.setRoutineSetId(this)
    }

    val currentRoutineSetRoutine: RoutineSetRoutine by sharedViewModel.currentRoutineSetRoutineState.currentRoutineSetRoutine.collectAsStateWithLifecycle()
    val routineUiState: RoutineUiState by sharedViewModel.routineUiState.collectAsStateWithLifecycle()
    val updateRoutineState: UpdateRoutineState by viewModel.updateRoutineState.collectAsStateWithLifecycle()

    val updateUpdateRoutineState: (UpdateRoutineState) -> Unit  = viewModel.updateUpdateRoutineState()
    val deleteRoutineSetRoutine: (Int) -> Unit  = viewModel.deleteRoutineSetRoutine()
    val updateRoutineSetRoutine: (RoutineSetRoutine) -> Unit  = viewModel.updateRoutineSetRoutine()

    when (val result = updateRoutineState) {
        is UpdateRoutineState.Fail -> {
            LaunchedEffect(true) {
                routineSetScreenState.snackbarHostState.showSnackbar(
                    message = result.message, duration = SnackbarDuration.Short
                )
            }
        }

        UpdateRoutineState.None -> {}
        is UpdateRoutineState.Success -> {
            LaunchedEffect(true) {}
        }
    }
}