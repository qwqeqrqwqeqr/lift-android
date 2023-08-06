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
import com.gradation.lift.feature.work.work.component.WorkListScreen
import com.gradation.lift.feature.work.work.component.work_rest.WorkRestScreen
import com.gradation.lift.feature.work.work.component.work_work.WorkWorkScreen
import com.gradation.lift.feature.work.work.component.dialog.SuspendDialog
import com.gradation.lift.feature.work.work.data.WorkScreenState
import com.gradation.lift.feature.work.work.data.WorkSharedViewModel
import com.gradation.lift.feature.work.work.data.WorkWorkViewModel
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


    val onVisibleSuspendDialog by viewModel.onVisibleSuspendDialog.collectAsStateWithLifecycle()
    val onVisibleCompleteDialog by viewModel.onVisibleCompleteDialog.collectAsStateWithLifecycle()
    val workScreenState: WorkScreenState by viewModel.workScreenState.collectAsStateWithLifecycle()


    val updateScreenState = viewModel.updateWorkScreenState()

    val visibleSuspendDialog = viewModel.visibleSuspendDialog()
    val invisibleSuspendDialog = viewModel.invisibleSuspendDialog()

    val visibleCompleteDialog = viewModel.visibleCompleteDialog()
    val invisibleCompleteDialog = viewModel.invisibleCompleteDialog()


    BackHandler(enabled = true, onBack = visibleSuspendDialog)

    if (onVisibleSuspendDialog) {
        Surface(
            color = LiftTheme.colorScheme.no23, modifier = modifier.fillMaxSize()
        ) {
            SuspendDialog(
                onClickDialogSuspendButton = navigateWorkToMain,
                onClickDialogDismissButton = invisibleSuspendDialog,
            )
        }
    } else {
        when (workScreenState) {
            is WorkScreenState.ListScreen -> {
                WorkListScreen(
                    modifier = modifier,
                    onCloseClickTopBar = updateScreenState,
                )
            }
            WorkScreenState.RestScreen -> {
                WorkRestScreen(
                    modifier = modifier,
                    onCloseClickTopBar = visibleSuspendDialog,
                    onListClickTopBar = updateScreenState,
                    onClickWorkCompleteButton = visibleCompleteDialog
                )
            }
            WorkScreenState.WorkScreen -> {
                WorkWorkScreen(
                    modifier = modifier,
                    onCloseClickTopBar = visibleSuspendDialog,
                    onListClickTopBar = updateScreenState,
                    onClickWorkCompleteButton = visibleCompleteDialog
                )
            }
        }
    }


}


