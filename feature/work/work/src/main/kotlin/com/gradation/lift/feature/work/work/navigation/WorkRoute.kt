package com.gradation.lift.feature.work.work.navigation

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
import com.gradation.lift.feature.work.work.data.model.WorkRoutine
import com.gradation.lift.feature.work.work.ui.component.dialog.AutoCompleteDialog
import com.gradation.lift.feature.work.work.ui.component.dialog.CompleteDialog
import com.gradation.lift.feature.work.work.ui.component.dialog.SuspendDialog
import com.gradation.lift.feature.work.work.data.state.WorkDialogUiState
import com.gradation.lift.feature.work.work.data.state.WorkScreenState
import com.gradation.lift.feature.work.work.data.state.WorkScreenUiState
import com.gradation.lift.feature.work.work.data.state.WorkState
import com.gradation.lift.feature.work.work.data.state.WorkState.Companion.MAX_PROGRESS
import com.gradation.lift.feature.work.work.data.state.WorkUiState
import com.gradation.lift.feature.work.work.data.state.rememberWorkScreenState
import com.gradation.lift.feature.work.work.data.viewmodel.WorkViewModel
import com.gradation.lift.feature.work.work.ui.list.ListScreen
import com.gradation.lift.feature.work.work.ui.rest.RestScreen
import com.gradation.lift.feature.work.work.ui.work.WorkScreen
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import com.gradation.lift.navigation.saved_state.getValueSavedStateHandle
import kotlinx.datetime.LocalTime

@SuppressLint("UnrememberedGetBackStackEntry")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateWorkToCompleteInWorkGraph: () -> Unit,
    navigateWorkGraphToHomeGraph: () -> Unit,
    viewModel: WorkViewModel = hiltViewModel(),
    workScreenState: WorkScreenState = rememberWorkScreenState(),
) {

    LaunchedEffect(true) {
        with(
            navController.getValueSavedStateHandle<IntArray>(
                SavedStateHandleKey.RoutineSet.WORK_ROUTINE_SET_LIST_ID_KEY
            )
        ) {
            viewModel.setRoutineSetIdList(this)
        }
    }


    val workScreenUiState: WorkScreenUiState by workScreenState.workScreenUiState.collectAsStateWithLifecycle()
    val workDialogUiState: WorkDialogUiState by workScreenState.workDialogUiState.collectAsStateWithLifecycle()
    val autoCompleteState: Boolean by workScreenState.autoCompleteState.collectAsStateWithLifecycle()

    val workUiState: WorkUiState by viewModel.workUiState.collectAsStateWithLifecycle()
    val workState: WorkState = viewModel.workState
    val workTime: LocalTime by viewModel.workState.workTime.collectAsStateWithLifecycle()
    val restTime: LocalTime by viewModel.workState.restTime.collectAsStateWithLifecycle()
    val currentWork: WorkRoutine? by viewModel.workState.currentWork.collectAsStateWithLifecycle()
    val preWork: WorkRoutine? by viewModel.workState.preWork.collectAsStateWithLifecycle()
    val nextWork: WorkRoutine? by viewModel.workState.nextWork.collectAsStateWithLifecycle()
    val workProgress: Int by viewModel.workState.workProgress.collectAsStateWithLifecycle()



    BackHandler(
        enabled = true,
        onBack = { workScreenState.updateWorkDialogState(WorkDialogUiState.SuspendDialogUi) })


    if (workProgress == MAX_PROGRESS && autoCompleteState) {
        workScreenState.offAutoCompleteState()
        workScreenState.updateWorkDialogState(WorkDialogUiState.CompleteDialogUi)
    }
    Box {
        when (workDialogUiState) {
            WorkDialogUiState.AutoCompleteDialogUi -> {
                Surface(
                    color = LiftTheme.colorScheme.no5.copy(alpha = 0.7f),
                    modifier = modifier.fillMaxSize()
                ) {
                    AutoCompleteDialog(
                        onClickDialogCompleteButton = navigateWorkToCompleteInWorkGraph,
                        onClickDialogDismissButton = {
                            workScreenState.updateWorkDialogState(
                                WorkDialogUiState.None
                            )
                        },
                    )
                }
            }

            WorkDialogUiState.SuspendDialogUi -> {
                Surface(
                    color = LiftTheme.colorScheme.no5.copy(alpha = 0.7f),
                    modifier = modifier.fillMaxSize()
                ) {
                    SuspendDialog(
                        onClickDialogSuspendButton = navigateWorkGraphToHomeGraph,
                        onClickDialogDismissButton = {
                            workScreenState.updateWorkDialogState(
                                WorkDialogUiState.None
                            )
                        },
                    )
                }
            }

            WorkDialogUiState.CompleteDialogUi -> {
                Surface(
                    color = LiftTheme.colorScheme.no5.copy(alpha = 0.7f),
                    modifier = modifier.fillMaxSize()
                ) {
                    CompleteDialog(
                        completeState = workProgress == MAX_PROGRESS,
                        onClickDialogCompleteButton = navigateWorkToCompleteInWorkGraph,
                        onClickDialogDismissButton = {
                            workScreenState.updateWorkDialogState(
                                WorkDialogUiState.None
                            )
                        },
                    )
                }
            }

            WorkDialogUiState.None -> {}
        }
        when (workScreenUiState) {
            is WorkScreenUiState.ListScreenUi -> ListScreen(
                modifier,
                workTime,
                workProgress,
                workState,
                workScreenState
            )

            WorkScreenUiState.RestScreenUi -> RestScreen(
                modifier,
                currentWork,
                workTime,
                restTime,
                workProgress,
                workState,
                workScreenState
            )

            WorkScreenUiState.WorkScreenUi -> WorkScreen(
                modifier,
                currentWork,
                preWork,
                nextWork,
                workTime,
                workProgress,
                workState,
                workScreenState
            )

        }
    }
}


