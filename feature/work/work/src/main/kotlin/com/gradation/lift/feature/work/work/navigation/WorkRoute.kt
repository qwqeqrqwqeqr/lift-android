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
import com.gradation.lift.feature.work.common.data.model.WorkRestTime
import com.gradation.lift.feature.work.common.data.WorkSharedViewModel
import com.gradation.lift.feature.work.common.data.model.WorkRoutine
import com.gradation.lift.feature.work.work.ui.component.dialog.AutoCompleteDialog
import com.gradation.lift.feature.work.work.ui.component.dialog.CompleteDialog
import com.gradation.lift.feature.work.work.ui.component.dialog.SuspendDialog
import com.gradation.lift.feature.work.work.data.state.WorkDialogUiState
import com.gradation.lift.feature.work.work.data.state.WorkScreenState
import com.gradation.lift.feature.work.work.data.state.WorkScreenUiState
import com.gradation.lift.feature.work.work.data.state.WorkState
import com.gradation.lift.feature.work.work.data.state.WorkState.Companion.MAX_PROGRESS
import com.gradation.lift.feature.work.work.data.state.rememberWorkScreenState
import com.gradation.lift.feature.work.work.data.viewmodel.WorkViewModel
import com.gradation.lift.feature.work.work.ui.list.ListScreen
import com.gradation.lift.feature.work.work.ui.rest.RestScreen
import com.gradation.lift.feature.work.work.ui.work.WorkScreen
import com.gradation.lift.model.model.history.CreateHistoryRoutine
import com.gradation.lift.model.model.work.WorkSet
import com.gradation.lift.navigation.Route
import kotlinx.datetime.LocalTime
import kotlinx.datetime.LocalTime.Companion.fromSecondOfDay

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun WorkRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateWorkToCompleteInWorkGraph: () -> Unit,
    navigateWorkGraphToHomeGraph: () -> Unit,
    viewModel: WorkViewModel = hiltViewModel(),
    sharedViewModel: WorkSharedViewModel = hiltViewModel(
        remember { navController.getBackStackEntry(Route.WORK_GRAPH_NAME) }
    ),
    workScreenState: WorkScreenState = rememberWorkScreenState(),
) {
    val workScreenUiState: WorkScreenUiState by workScreenState.workScreenUiState.collectAsStateWithLifecycle()
    val workDialogUiState: WorkDialogUiState by workScreenState.workDialogUiState.collectAsStateWithLifecycle()
    val autoCompleteState: Boolean by workScreenState.autoCompleteState.collectAsStateWithLifecycle()

    val workState: WorkState = viewModel.workState
    val workTime: LocalTime by viewModel.workState.workTime.collectAsStateWithLifecycle()
    val restTime: LocalTime by viewModel.workState.restTime.collectAsStateWithLifecycle()
    val currentWork: WorkRoutine? by viewModel.workState.currentWork.collectAsStateWithLifecycle()
    val preWork: WorkRoutine? by viewModel.workState.preWork.collectAsStateWithLifecycle()
    val nextWork: WorkRoutine? by viewModel.workState.nextWork.collectAsStateWithLifecycle()
    val workProgress: Int by viewModel.workState.workProgress.collectAsStateWithLifecycle()

    val setHistoryRoutineList: (List<CreateHistoryRoutine>) -> Unit =
        sharedViewModel.setHistoryRoutineList
    val setHistoryWorkRestTime: (WorkRestTime) -> Unit = sharedViewModel.setHistoryWorkRestTime

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
                        onClickDialogCompleteButton = {
                            setHistoryWorkRestTime(
                                WorkRestTime(
                                    workTime = workTime,
                                    restTime = restTime,
                                    totalTime = fromSecondOfDay(workTime.toSecondOfDay() + restTime.toSecondOfDay())
                                )
                            )
                            setHistoryRoutineList(
                                workState.currentWorkRoutineList.toList().map {
                                    CreateHistoryRoutine(
                                        workCategory = it.workCategory.name,
                                        workSetList = it.workSetList.toList().map { workSet ->
                                            WorkSet(
                                                workSet.weight.toFloat(),
                                                workSet.repetition.toInt()
                                            )
                                        }
                                    )
                                }
                            )
                            workState.stopTime()
                            navigateWorkToCompleteInWorkGraph()
                        },
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
                        onClickDialogCompleteButton = {
                            setHistoryWorkRestTime(
                                WorkRestTime(
                                    workTime = workTime,
                                    restTime = restTime,
                                    totalTime = fromSecondOfDay(workTime.toSecondOfDay() + restTime.toSecondOfDay())
                                )
                            )
                            setHistoryRoutineList(
                                workState.currentWorkRoutineList.toList().map { workRoutine ->
                                    CreateHistoryRoutine(
                                        workCategory = workRoutine.workCategory.name,
                                        workSetList = workRoutine.workSetList.toList()
                                            .filterIndexed { index, _ ->
                                                workState.isChecked(
                                                    workRoutine.id,
                                                    index
                                                )
                                            }.map { workSet ->
                                            WorkSet(
                                                workSet.weight.toFloat(),
                                                workSet.repetition.toInt()
                                            )
                                        }
                                    )
                                }.filter { it.workSetList.isNotEmpty() }
                            )
                            workState.stopTime()
                            navigateWorkToCompleteInWorkGraph()
                        },
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


