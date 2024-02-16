package com.gradation.lift.feature.workReady.createWorkSet.navigation

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.feature.workReady.common.WorkReadySharedViewModel
import com.gradation.lift.feature.workReady.common.WorkRoutineState
import com.gradation.lift.feature.workReady.createWorkSet.data.CreateWorkSetViewModel
import com.gradation.lift.feature.workReady.createWorkSet.data.state.RoutineScreenState
import com.gradation.lift.feature.workReady.createWorkSet.data.state.WorkCategoryUiState
import com.gradation.lift.feature.workReady.createWorkSet.data.state.WorkSetState
import com.gradation.lift.feature.workReady.createWorkSet.data.state.rememberRoutineScreenState
import com.gradation.lift.feature.workReady.createWorkSet.ui.CreateWorkSetScreen
import com.gradation.lift.navigation.Route

@Composable
@SuppressLint("UnrememberedGetBackStackEntry")
internal fun CreateWorkSetRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateCreateWorkSetToFindWorkCategoryInWorkReadyGraph: () -> Unit,
    viewModel: CreateWorkSetViewModel = hiltViewModel(),
    sharedViewModel: WorkReadySharedViewModel = hiltViewModel(
        remember { navController.getBackStackEntry(Route.WORK_READY_GRAPH_NAME) }),
    routineScreenState: RoutineScreenState = rememberRoutineScreenState(),
) {


    val workCategoryUiState: WorkCategoryUiState by viewModel.workCategoryUiState.collectAsStateWithLifecycle()
    val workSetState: WorkSetState = viewModel.workSetState
    val workRoutineState: WorkRoutineState = sharedViewModel.workRoutineState

    BackHandler(onBack = navigateCreateWorkSetToFindWorkCategoryInWorkReadyGraph)

    CreateWorkSetScreen(
        modifier,
        workCategoryUiState,
        workSetState,
        workRoutineState,
        navigateCreateWorkSetToFindWorkCategoryInWorkReadyGraph,
        routineScreenState
    )


}