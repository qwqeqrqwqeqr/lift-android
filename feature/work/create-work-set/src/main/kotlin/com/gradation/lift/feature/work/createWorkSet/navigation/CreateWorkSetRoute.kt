package com.gradation.lift.feature.work.createWorkSet.navigation

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.feature.work.common.data.WorkSharedViewModel
import com.gradation.lift.feature.work.common.data.WorkState
import com.gradation.lift.feature.work.createWorkSet.data.CreateWorkSetViewModel
import com.gradation.lift.feature.work.createWorkSet.data.state.RoutineScreenState
import com.gradation.lift.feature.work.createWorkSet.data.state.WorkCategoryUiState
import com.gradation.lift.feature.work.createWorkSet.data.state.WorkSetState
import com.gradation.lift.feature.work.createWorkSet.data.state.rememberRoutineScreenState
import com.gradation.lift.feature.work.createWorkSet.ui.CreateWorkSetScreen
import com.gradation.lift.navigation.Route
import com.gradation.lift.navigation.Route.WORK_GRAPH_NAME

@Composable
@SuppressLint("UnrememberedGetBackStackEntry")
internal fun CreateWorkSetRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateCreateWorkSetToFindWorkCategoryInWorkGraph: () -> Unit,
    navigateCreateWorkSetToWorkInWorkGraph: () -> Unit,
    viewModel: CreateWorkSetViewModel = hiltViewModel(),
    sharedViewModel: WorkSharedViewModel = hiltViewModel(
        remember { navController.getBackStackEntry(WORK_GRAPH_NAME) }),
    routineScreenState: RoutineScreenState = rememberRoutineScreenState(),
) {


    val workCategoryUiState: WorkCategoryUiState by viewModel.workCategoryUiState.collectAsStateWithLifecycle()
    val workSetState: WorkSetState = viewModel.workSetState
    val workRoutineState: WorkState = sharedViewModel.workState

    BackHandler(onBack = navigateCreateWorkSetToFindWorkCategoryInWorkGraph)

    CreateWorkSetScreen(
        modifier,
        workCategoryUiState,
        workSetState,
        workRoutineState,
        navigateCreateWorkSetToFindWorkCategoryInWorkGraph,
        navigateCreateWorkSetToWorkInWorkGraph,
        routineScreenState
    )


}