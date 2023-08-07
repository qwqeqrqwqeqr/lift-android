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
import com.gradation.lift.feature.work.work.component.dialog.CompleteDialog
import com.gradation.lift.feature.work.work.component.work_list.WorkListScreen
import com.gradation.lift.feature.work.work.component.work_rest.WorkRestScreen
import com.gradation.lift.feature.work.work.component.work_work.WorkWorkScreen
import com.gradation.lift.feature.work.work.component.dialog.SuspendDialog
import com.gradation.lift.feature.work.work.data.state.WorkDialogState
import com.gradation.lift.feature.work.work.data.state.WorkScreenState
import com.gradation.lift.feature.work.work.data.viewmodel.WorkSharedViewModel
import com.gradation.lift.feature.work.work.data.viewmodel.WorkWorkViewModel
import com.gradation.lift.navigation.Router

@SuppressLint("UnrememberedGetBackStackEntry")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkWorkRoute(
    navController: NavController,
    navigateWorkWorkToComplete: () -> Unit,
    navigateWorkToMain: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: WorkWorkViewModel = hiltViewModel(),

    ) {

    val workBackStackEntry =
        remember { navController.getBackStackEntry(Router.WORK_GRAPH_NAME) }
    val sharedViewModel: WorkSharedViewModel = hiltViewModel(workBackStackEntry)


    val workScreenState: WorkScreenState by viewModel.workScreenState.collectAsStateWithLifecycle()
    val workDialogState: WorkDialogState by viewModel.workDialogState.collectAsStateWithLifecycle()

    val updateScreenState = viewModel.updateWorkScreenState()
    val updateDialogState = viewModel.updateWorkDialogState()
    val updateWorkState = viewModel.updateWorkState()


    val workList by sharedViewModel.workList.collectAsStateWithLifecycle()

    val currentWork by sharedViewModel.currentWork.collectAsStateWithLifecycle()
    val previousWork by sharedViewModel.previousWork.collectAsStateWithLifecycle()
    val nextWork by sharedViewModel.nextWork.collectAsStateWithLifecycle()

    val workTime by viewModel.workRestTime.collectAsStateWithLifecycle()
    val workProgress by sharedViewModel.workProgress.collectAsStateWithLifecycle()

    val addOpenedWorkRoutineId = sharedViewModel.addOpenedWorkRoutineId()
    val removeOpenedWorkRoutineId = sharedViewModel.removeOpenedWorkRoutineId()
    val checkWorkSet = sharedViewModel.checkWorkSet()
    val uncheckWorkSet = sharedViewModel.uncheckWorkSet()
    val updateWorkIndexToPreviousIndex = sharedViewModel.updateWorkIndexToPreviousIndex()
    val updateWorkIndexToNextIndex = sharedViewModel.updateWorkIndexToNextIndex()


    BackHandler(enabled = true, onBack = { updateDialogState(WorkDialogState.SuspendDialog) })
    LaunchedEffect(true) {
        viewModel.startTimer()
    }

    when (workDialogState) {
        WorkDialogState.SuspendDialog -> {
            Surface(
                color = LiftTheme.colorScheme.no23, modifier = modifier.fillMaxSize()
            ) {
                SuspendDialog(
                    onClickDialogSuspendButton = navigateWorkToMain,
                    onClickDialogDismissButton = { updateDialogState(WorkDialogState.None) },
                )
            }
        }
        WorkDialogState.CompleteDialog -> {
            Surface(
                color = LiftTheme.colorScheme.no23, modifier = modifier.fillMaxSize()
            ) {
                CompleteDialog(
                    onClickDialogCompleteButton = navigateWorkWorkToComplete,
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
                    )
                }
                WorkScreenState.RestScreen -> {
                    WorkRestScreen(
                        modifier = modifier,
                        onCloseClickTopBar = { updateDialogState(WorkDialogState.SuspendDialog) },
                        onListClickTopBar = { updateScreenState(WorkScreenState.ListScreen(false)) },
                        onClickWorkCompleteButton = { updateDialogState(WorkDialogState.CompleteDialog) },
                        updateWorkState = { updateWorkState(true) },
                        workTime = workTime,
                    )
                }
                WorkScreenState.WorkScreen -> {
                    WorkWorkScreen(
                        modifier = modifier,
                        onCloseClickTopBar = { updateDialogState(WorkDialogState.SuspendDialog) },
                        onListClickTopBar = { updateScreenState(WorkScreenState.ListScreen(true)) },
                        onClickWorkCompleteButton = { updateDialogState(WorkDialogState.CompleteDialog) },
                        updateWorkState = { updateWorkState(false) },
                        checkWorkSet=checkWorkSet,
                        uncheckWorkSet=uncheckWorkSet,
                        updateWorkIndexToPreviousIndex=updateWorkIndexToPreviousIndex,
                        updateWorkIndexToNextIndex=updateWorkIndexToNextIndex,

                        workTime = workTime,
                        workProgress=workProgress,
                        currentWork=currentWork,
                        previousWork=previousWork,
                        nextWork=nextWork,
                    )
                }
            }
        }
    }


}


