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

    val selectedWorkList by sharedViewModel.selectedWorkList.collectAsStateWithLifecycle()

    val workTime by viewModel.workRestTime.collectAsStateWithLifecycle()

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
                    onClickDialogCompleteButton = navigateWorkToMain,
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
                        workTime = workTime,
                        updateWorkState = updateWorkState
                    )
                }
                WorkScreenState.WorkScreen -> {
                    WorkWorkScreen(
                        modifier = modifier,
                        onCloseClickTopBar = { updateDialogState(WorkDialogState.SuspendDialog) },
                        onListClickTopBar = { updateScreenState(WorkScreenState.ListScreen(true)) },
                        onClickWorkCompleteButton = { updateDialogState(WorkDialogState.CompleteDialog) },
                        workTime = workTime,
                        updateWorkState = updateWorkState
                    )
                }
            }
        }
    }


}


