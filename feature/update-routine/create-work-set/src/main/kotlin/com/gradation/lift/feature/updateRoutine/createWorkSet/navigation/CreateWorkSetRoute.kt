package com.gradation.lift.feature.updateRoutine.createWorkSet.navigation

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
import com.gradation.lift.feature.updateRoutine.common.data.UpdateRoutineSharedViewModel
import com.gradation.lift.feature.updateRoutine.common.data.state.CurrentRoutineSetRoutineState
import com.gradation.lift.feature.updateRoutine.common.data.state.RoutineUiState
import com.gradation.lift.feature.updateRoutine.createWorkSet.data.CreateWorkSetViewModel
import com.gradation.lift.feature.updateRoutine.createWorkSet.data.state.KeypadState
import com.gradation.lift.feature.updateRoutine.createWorkSet.data.state.KeypadWorkSetState
import com.gradation.lift.feature.updateRoutine.createWorkSet.data.state.RoutineScreenState
import com.gradation.lift.feature.updateRoutine.createWorkSet.data.state.WorkCategoryUiState
import com.gradation.lift.feature.updateRoutine.createWorkSet.data.state.WorkSetState
import com.gradation.lift.feature.updateRoutine.createWorkSet.data.state.rememberRoutineScreenState
import com.gradation.lift.feature.updateRoutine.createWorkSet.ui.CreateWorkSetScreen
import com.gradation.lift.navigation.Route

@Composable
@SuppressLint("UnrememberedGetBackStackEntry")
internal fun CreateWorkSetRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    workCategoryId: Int?,
    navigateCreateWorkSetRouteToFindWorkCategoryInUpdateRoutineGraph: () -> Unit,
    navigateCreateWorkSetToRoutineSetInUpdateRoutineGraph: () -> Unit,
    viewModel: CreateWorkSetViewModel = hiltViewModel(),
    sharedViewModel: UpdateRoutineSharedViewModel = hiltViewModel(
        remember { navController.getBackStackEntry(Route.UPDATE_ROUTINE_GRAPH_NAME) }),
    routineScreenState: RoutineScreenState = rememberRoutineScreenState(),

    ) {

    LaunchedEffect(Unit) { viewModel.setWorkCategoryId(workCategoryId) }
    val keypadWorkSetState: KeypadWorkSetState by viewModel.keypadState.keypadWorkSetState.collectAsStateWithLifecycle()


    val workCategoryUiState: WorkCategoryUiState by viewModel.workCategoryUiState.collectAsStateWithLifecycle()
    val routineUiState: RoutineUiState by sharedViewModel.routineUiState.collectAsStateWithLifecycle()
    val workSetState: WorkSetState = viewModel.workSetState
    val keypadState: KeypadState = viewModel.keypadState
    val currentRoutineSetRoutineState: CurrentRoutineSetRoutineState =
        sharedViewModel.currentRoutineSetRoutineState

    BackHandler(onBack = navigateCreateWorkSetRouteToFindWorkCategoryInUpdateRoutineGraph)

    CreateWorkSetScreen(
        modifier,
        keypadWorkSetState,
        routineUiState,
        workCategoryUiState,
        workSetState,
        keypadState,
        currentRoutineSetRoutineState,
        navigateCreateWorkSetRouteToFindWorkCategoryInUpdateRoutineGraph,
        navigateCreateWorkSetToRoutineSetInUpdateRoutineGraph,
        routineScreenState
    )


}