package com.gradation.lift.createRoutine.routine.navigation

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.feature.createRoutine.common.data.CreateRoutineSharedViewModel
import com.gradation.lift.createRoutine.routine.data.RoutineViewModel
import com.gradation.lift.createRoutine.routine.ui.RoutineScreen
import com.gradation.lift.feature.createRoutine.common.data.state.CurrentRoutineSetRoutineState
import com.gradation.lift.createRoutine.routine.data.state.KeypadState
import com.gradation.lift.createRoutine.routine.data.state.KeypadWorkSetState
import com.gradation.lift.createRoutine.routine.data.state.WorkCategoryUiState
import com.gradation.lift.createRoutine.routine.data.state.WorkSetState
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import com.gradation.lift.navigation.saved_state.getValueSavedStateHandle

@Composable
@SuppressLint("UnrememberedGetBackStackEntry")
fun RoutineRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateRoutineToFindWorkCategoryInCreateRoutineGraph: () -> Unit,
    navigateRoutineToRoutineSetInCreateRoutineGraph: () -> Unit,
    viewModel: RoutineViewModel = hiltViewModel(),
    sharedViewModel: CreateRoutineSharedViewModel = hiltViewModel(
        remember { navController.getBackStackEntry(Router.CREATE_ROUTINE_GRAPH_NAME) })
) {
    LaunchedEffect(Unit) {
        with(navController.getValueSavedStateHandle<Int>(SavedStateHandleKey.RoutineSet.CREATE_WORK_CATEGORY_ID_KEY)) {
            viewModel.setWorkCategoryId(this)
        }
    }

    val keypadWorkSetState: KeypadWorkSetState by viewModel.keypadState.keypadWorkSetState.collectAsStateWithLifecycle()


    val workCategoryUiState: WorkCategoryUiState by viewModel.workCategoryUiState.collectAsStateWithLifecycle()
    val workSetState: WorkSetState = viewModel.workSetState
    val keypadState: KeypadState = viewModel.keypadState
    val currentRoutineSetRoutineState: CurrentRoutineSetRoutineState =
        sharedViewModel.currentRoutineSetRoutineState

    BackHandler(onBack = navigateRoutineToFindWorkCategoryInCreateRoutineGraph)

    RoutineScreen(
        modifier,
        keypadWorkSetState,
        workCategoryUiState,
        workSetState,
        keypadState,
        currentRoutineSetRoutineState,
        navigateRoutineToFindWorkCategoryInCreateRoutineGraph,
        navigateRoutineToRoutineSetInCreateRoutineGraph
    )


}