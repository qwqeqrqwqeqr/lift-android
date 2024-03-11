package com.gradation.lift.feature.createRoutine.createWorkSet.navigation

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.feature.createRoutine.common.data.CreateRoutineSharedViewModel
import com.gradation.lift.feature.createRoutine.common.data.state.CurrentRoutineSetRoutineState
import com.gradation.lift.feature.createRoutine.createWorkSet.data.CreateWorkSetViewModel
import com.gradation.lift.feature.createRoutine.createWorkSet.data.state.RoutineScreenState
import com.gradation.lift.feature.createRoutine.createWorkSet.data.state.WorkCategoryUiState
import com.gradation.lift.feature.createRoutine.createWorkSet.data.state.WorkSetState
import com.gradation.lift.feature.createRoutine.createWorkSet.data.state.rememberRoutineScreenState
import com.gradation.lift.feature.createRoutine.createWorkSet.ui.CreateWorkSetScreen
import com.gradation.lift.navigation.Route

@Composable
@SuppressLint("UnrememberedGetBackStackEntry")
internal fun CreateWorkSetRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateCreateWorkSetToFindWorkCategoryInCreateRoutineGraph: () -> Unit,
    viewModel: CreateWorkSetViewModel = hiltViewModel(),
    sharedViewModel: CreateRoutineSharedViewModel = hiltViewModel(
        remember { navController.getBackStackEntry(Route.CREATE_ROUTINE_GRAPH_NAME) }),
    routineScreenState: RoutineScreenState = rememberRoutineScreenState(),
) {
    val workCategoryFavoriteFlag: Boolean by viewModel.workCategoryFavoriteFlag.collectAsStateWithLifecycle()
    val updateWorkCategoryFavorite: () -> Unit = viewModel.updateWorkCategoryFavorite


    val workCategoryUiState: WorkCategoryUiState by viewModel.workCategoryUiState.collectAsStateWithLifecycle()
    val workSetState: WorkSetState = viewModel.workSetState
    val currentRoutineSetRoutineState: CurrentRoutineSetRoutineState =
        sharedViewModel.currentRoutineSetRoutineState


    BackHandler(onBack = navigateCreateWorkSetToFindWorkCategoryInCreateRoutineGraph)

    CreateWorkSetScreen(
        modifier,
        workCategoryFavoriteFlag,
        updateWorkCategoryFavorite,
        workCategoryUiState,
        workSetState,
        currentRoutineSetRoutineState,
        navigateCreateWorkSetToFindWorkCategoryInCreateRoutineGraph,
        routineScreenState
    )


}