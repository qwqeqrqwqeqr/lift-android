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
import com.gradation.lift.createRoutine.routine.data.state.RoutineScreenState
import com.gradation.lift.createRoutine.routine.data.state.WorkCategoryUiState
import com.gradation.lift.createRoutine.routine.data.state.WorkSetState
import com.gradation.lift.createRoutine.routine.data.state.rememberRoutineScreenState
import com.gradation.lift.navigation.Route

@Composable
@SuppressLint("UnrememberedGetBackStackEntry")
internal fun RoutineRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    workCategoryId: Int,
    navigateRoutineToFindWorkCategoryInCreateRoutineGraph: () -> Unit,
    navigateRoutineToRoutineSetInCreateRoutineGraph: () -> Unit,
    viewModel: RoutineViewModel = hiltViewModel(),
    sharedViewModel: CreateRoutineSharedViewModel = hiltViewModel(
        remember { navController.getBackStackEntry(Route.CREATE_ROUTINE_GRAPH_NAME) }),
    routineScreenState: RoutineScreenState = rememberRoutineScreenState(),
) {
    LaunchedEffect(Unit) { viewModel.setWorkCategoryId(workCategoryId) }
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
        navigateRoutineToRoutineSetInCreateRoutineGraph,
        routineScreenState
    )


}