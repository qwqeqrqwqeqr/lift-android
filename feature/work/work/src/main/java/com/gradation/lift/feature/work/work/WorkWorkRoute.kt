package com.gradation.lift.feature.work.work

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.work.work.component.dialog.AutoCompleteDialog
import com.gradation.lift.feature.work.work.component.dialog.CompleteDialog
import com.gradation.lift.feature.work.work.component.work_list.WorkListScreen
import com.gradation.lift.feature.work.work.component.work_rest.WorkRestScreen
import com.gradation.lift.feature.work.work.component.work_work.WorkWorkScreen
import com.gradation.lift.feature.work.work.component.dialog.SuspendDialog
import com.gradation.lift.feature.work.work.data.state.WorkDialogState
import com.gradation.lift.feature.work.work.data.state.WorkScreenState
import com.gradation.lift.feature.work.work.data.viewmodel.WorkSharedViewModel
import com.gradation.lift.feature.work.work.data.viewmodel.WorkSharedViewModel.Companion.MAX_PROGRESS
import com.gradation.lift.feature.work.work.data.viewmodel.WorkWorkViewModel
import com.gradation.lift.navigation.Router

@SuppressLint("UnrememberedGetBackStackEntry")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkWorkRoute(
    navController: NavController,
    navigateWorkToComplete: () -> Unit,
    navigateWorkGraphToMainGraph: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: WorkWorkViewModel = hiltViewModel(),

    ) {

    val workBackStackEntry =
        remember { navController.getBackStackEntry(Router.WORK_GRAPH_NAME) }
    val sharedViewModel: WorkSharedViewModel = hiltViewModel(workBackStackEntry)


    val workScreenState: WorkScreenState by viewModel.workScreenState.collectAsStateWithLifecycle()
    val workDialogState: WorkDialogState by viewModel.workDialogState.collectAsStateWithLifecycle()
    val autoCompleteState: Boolean by viewModel.autoCompleteState.collectAsStateWithLifecycle()

    val updateScreenState = viewModel.updateWorkScreenState()
    val updateDialogState = viewModel.updateWorkDialogState()
    val updateWorkState = sharedViewModel.updateWorkState()
    val offAutoCompleteState = viewModel.offAutoCompleteState()


    val workList by sharedViewModel.workList.collectAsStateWithLifecycle()

    val currentWork by sharedViewModel.currentWork.collectAsStateWithLifecycle()
    val previousWork by sharedViewModel.previousWork.collectAsStateWithLifecycle()
    val nextWork by sharedViewModel.nextWork.collectAsStateWithLifecycle()

    val workTime by sharedViewModel.workRestTime.collectAsStateWithLifecycle()
    val workProgress by sharedViewModel.workProgress.collectAsStateWithLifecycle()

    val updateOpenedWorkRoutine = sharedViewModel.updateOpenedWorkRoutine()
    val updateCheckedWorkSet = sharedViewModel.updateCheckedWorkSet()
    val updateWorkIndexToPreviousIndex = sharedViewModel.updateWorkIndexToPreviousIndex()
    val updateWorkIndexToNextIndex = sharedViewModel.updateWorkIndexToNextIndex()

    val isAllCheckedWorkSet = sharedViewModel.isAllCheckedWorkSet()


    BackHandler(enabled = true, onBack = { updateDialogState(WorkDialogState.SuspendDialog) })
    LaunchedEffect(true) {
        sharedViewModel.startTimer()
    }

    if (workProgress == MAX_PROGRESS && autoCompleteState) {
        offAutoCompleteState()
        updateDialogState(WorkDialogState.CompleteDialog)
    }

    when (workDialogState) {
        WorkDialogState.AutoCompleteDialog -> {
            Surface(
                color = LiftTheme.colorScheme.no23, modifier = modifier.fillMaxSize()
            ) {
                AutoCompleteDialog(
                    onClickDialogCompleteButton = navigateWorkToComplete,
                    onClickDialogDismissButton = { updateDialogState(WorkDialogState.None) },
                )
            }
        }

        WorkDialogState.SuspendDialog -> {
            Surface(
                color = LiftTheme.colorScheme.no23, modifier = modifier.fillMaxSize()
            ) {
                SuspendDialog(
                    onClickDialogSuspendButton = navigateWorkGraphToMainGraph,
                    onClickDialogDismissButton = { updateDialogState(WorkDialogState.None) },
                )
            }
        }
        WorkDialogState.CompleteDialog -> {
            Surface(
                color = LiftTheme.colorScheme.no23, modifier = modifier.fillMaxSize()
            ) {
                CompleteDialog(
                    completeState = workProgress == MAX_PROGRESS,
                    onClickDialogCompleteButton = navigateWorkToComplete,
                    onClickDialogDismissButton = { updateDialogState(WorkDialogState.None) },
                )
            }
        }
        WorkDialogState.None -> {
            when (workScreenState) {
                is WorkScreenState.ListScreen -> {
                    WorkListScreen(
                        modifier = modifier,
                        onCloseClickTopBar = { updateScreenState(WorkScreenState.WorkScreen) },
                        workTime = workTime,
                        workProgress = workProgress,
                        workList = workList,
                        updateOpenedWorkRoutine = updateOpenedWorkRoutine,
                        updateCheckedWorkSet = updateCheckedWorkSet,
                        isAllCheckedWorkSet = isAllCheckedWorkSet
                    )
                }
                WorkScreenState.RestScreen -> {
                    WorkRestScreen(
                        modifier = modifier,
                        onCloseClickTopBar = { updateDialogState(WorkDialogState.SuspendDialog) },
                        onListClickTopBar = { updateScreenState(WorkScreenState.ListScreen(false)) },
                        onClickWorkCompleteButton = { updateDialogState(WorkDialogState.CompleteDialog) },
                        onClickWorkButton = { updateScreenState(WorkScreenState.WorkScreen) },
                        updateWorkState = { updateWorkState(true) },
                        workTime = workTime,
                        workProgress = workProgress,
                        currentWork = currentWork
                    )
                }
                WorkScreenState.WorkScreen -> {
                    WorkWorkScreen(
                        modifier = modifier,
                        onCloseClickTopBar = { updateDialogState(WorkDialogState.SuspendDialog) },
                        onListClickTopBar = { updateScreenState(WorkScreenState.ListScreen(true)) },
                        onClickWorkCompleteButton = { updateDialogState(WorkDialogState.CompleteDialog) },
                        onClickRestButton = { updateScreenState(WorkScreenState.RestScreen) },
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


}


