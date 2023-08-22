package com.gradation.lift.feature.work.work

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.work.work.component.dialog.AutoCompleteDialog
import com.gradation.lift.feature.work.work.component.dialog.CompleteDialog
import com.gradation.lift.feature.work.work.component.work_list.WorkListScreen
import com.gradation.lift.feature.work.work.component.work_rest.WorkRestScreen
import com.gradation.lift.feature.work.work.component.work_work.WorkWorkScreen
import com.gradation.lift.feature.work.work.component.dialog.SuspendDialog
import com.gradation.lift.feature.work.work.data.model.WorkRestTime
import com.gradation.lift.feature.work.work.data.model.WorkRoutineSelection
import com.gradation.lift.feature.work.work.data.model.WorkSetSelection
import com.gradation.lift.feature.work.work.data.state.WorkDialogUiState
import com.gradation.lift.feature.work.work.data.state.WorkScreenUiState
import com.gradation.lift.feature.work.work.data.state.WorkState.Companion.MAX_PROGRESS
import com.gradation.lift.feature.work.work.data.viewmodel.WorkSharedViewModel
import com.gradation.lift.feature.work.work.data.viewmodel.WorkWorkViewModel
import com.gradation.lift.navigation.Router

@SuppressLint("UnrememberedGetBackStackEntry")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkWorkRoute(
    navController: NavController,
    navigateWorkToComplete: () -> Unit,
    navigateWorkGraphToHomeGraph: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: WorkWorkViewModel = hiltViewModel(),

    ) {

    val workBackStackEntry: NavBackStackEntry =
        remember { navController.getBackStackEntry(Router.WORK_GRAPH_NAME) }
    val sharedViewModel: WorkSharedViewModel = hiltViewModel(workBackStackEntry)


    val workScreenUiState: WorkScreenUiState by viewModel.workScreenUiState.collectAsStateWithLifecycle()
    val workDialogUiState: WorkDialogUiState by viewModel.workDialogUiState.collectAsStateWithLifecycle()
    val autoCompleteState: Boolean by viewModel.autoCompleteState.collectAsStateWithLifecycle()

    val updateScreenState: (WorkScreenUiState) -> Unit = viewModel.updateWorkScreenState()
    val updateDialogState: (WorkDialogUiState) -> Unit = viewModel.updateWorkDialogState()
    val updateWorkState: (Boolean) -> Unit = sharedViewModel.workState.updateWorkState()
    val offAutoCompleteState: () -> Unit = viewModel.offAutoCompleteState()


    val workList: List<WorkRoutineSelection> by sharedViewModel.workState.workList.collectAsStateWithLifecycle()

    val currentWork: WorkRoutineSelection by sharedViewModel.workState.currentWork.collectAsStateWithLifecycle()
    val previousWork: WorkRoutineSelection? by sharedViewModel.workState.previousWork.collectAsStateWithLifecycle()
    val nextWork: WorkRoutineSelection? by sharedViewModel.workState.nextWork.collectAsStateWithLifecycle()

    val workTime: WorkRestTime by sharedViewModel.workState.workRestTime.collectAsStateWithLifecycle()
    val workProgress: Int by sharedViewModel.workState.workProgress.collectAsStateWithLifecycle()

    val updateOpenedWorkRoutine: (WorkRoutineSelection) -> Unit =
        sharedViewModel.workState.updateOpenedWorkRoutine()
    val updateCheckedWorkSet: (Pair<Int, Int>, Boolean) -> Unit =
        sharedViewModel.workState.updateCheckedWorkSet()
    val updateWorkIndexToPreviousIndex: () -> Unit =
        sharedViewModel.workState.updateWorkIndexToPreviousIndex()
    val updateWorkIndexToNextIndex: () -> Unit =
        sharedViewModel.workState.updateWorkIndexToNextIndex()

    val isAllCheckedWorkSet: (List<WorkSetSelection>) -> (Boolean) =
        sharedViewModel.isAllCheckedWorkSet()


    BackHandler(enabled = true, onBack = { updateDialogState(WorkDialogUiState.SuspendDialogUi) })
    LaunchedEffect(true) {
        sharedViewModel.workState.startTimer()
    }

    if (workProgress == MAX_PROGRESS && autoCompleteState) {
        offAutoCompleteState()
        updateDialogState(WorkDialogUiState.CompleteDialogUi)
    }
    Box{
        when (workDialogUiState) {
            WorkDialogUiState.AutoCompleteDialogUi -> {
                Surface(
                    color = LiftTheme.colorScheme.no4.copy(alpha = 0.7f), modifier = modifier.fillMaxSize()
                ) {
                    AutoCompleteDialog(
                        onClickDialogCompleteButton = navigateWorkToComplete,
                        onClickDialogDismissButton = { updateDialogState(WorkDialogUiState.None) },
                    )
                }
            }

            WorkDialogUiState.SuspendDialogUi -> {
                Surface(
                    color = LiftTheme.colorScheme.no4.copy(alpha = 0.7f), modifier = modifier.fillMaxSize()
                ) {
                    SuspendDialog(
                        onClickDialogSuspendButton = navigateWorkGraphToHomeGraph,
                        onClickDialogDismissButton = { updateDialogState(WorkDialogUiState.None) },
                    )
                }
            }
            WorkDialogUiState.CompleteDialogUi -> {
                Surface(
                    color = LiftTheme.colorScheme.no4.copy(alpha = 0.7f),  modifier = modifier.fillMaxSize()
                ) {
                    CompleteDialog(
                        completeState = workProgress == MAX_PROGRESS,
                        onClickDialogCompleteButton = navigateWorkToComplete,
                        onClickDialogDismissButton = { updateDialogState(WorkDialogUiState.None) },
                    )
                }
            }
            WorkDialogUiState.None -> {}
        }
        when (workScreenUiState) {
            is WorkScreenUiState.ListScreenUi -> {
                WorkListScreen(
                    modifier = modifier,
                    onCloseClickTopBar = { updateScreenState(WorkScreenUiState.WorkScreenUi) },
                    workTime = workTime,
                    workProgress = workProgress,
                    workList = workList,
                    updateOpenedWorkRoutine = updateOpenedWorkRoutine,
                    updateCheckedWorkSet = updateCheckedWorkSet,
                    isAllCheckedWorkSet = isAllCheckedWorkSet
                )
            }
            WorkScreenUiState.RestScreenUi -> {
                WorkRestScreen(
                    modifier = modifier,
                    onCloseClickTopBar = { updateDialogState(WorkDialogUiState.SuspendDialogUi) },
                    onListClickTopBar = { updateScreenState(WorkScreenUiState.ListScreenUi(false)) },
                    onClickWorkCompleteButton = { updateDialogState(WorkDialogUiState.CompleteDialogUi) },
                    onClickWorkButton = { updateScreenState(WorkScreenUiState.WorkScreenUi) },
                    updateWorkState = { updateWorkState(true) },
                    workTime = workTime,
                    workProgress = workProgress,
                    currentWork = currentWork
                )
            }
            WorkScreenUiState.WorkScreenUi -> {
                WorkWorkScreen(
                    modifier = modifier,
                    onCloseClickTopBar = { updateDialogState(WorkDialogUiState.SuspendDialogUi) },
                    onListClickTopBar = { updateScreenState(WorkScreenUiState.ListScreenUi(true)) },
                    onClickWorkCompleteButton = { updateDialogState(WorkDialogUiState.CompleteDialogUi) },
                    onClickRestButton = { updateScreenState(WorkScreenUiState.RestScreenUi) },
                    updateWorkState = { updateWorkState(false) },
                    updateCheckedWorkSet = updateCheckedWorkSet,
                    updateWorkIndexToPreviousIndex = updateWorkIndexToPreviousIndex,
                    updateWorkIndexToNextIndex = updateWorkIndexToNextIndex,

                    workTime = workTime,
                    workProgress = workProgress,
                    currentWork = currentWork,
                    previousWork = previousWork,
                    nextWork = nextWork,
                )
            }
        }
    }
}


