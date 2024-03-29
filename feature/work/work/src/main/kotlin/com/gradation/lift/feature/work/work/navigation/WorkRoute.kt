package com.gradation.lift.feature.work.work.navigation

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.work.common.data.WorkSharedViewModel
import com.gradation.lift.feature.work.common.data.model.WorkRestTime
import com.gradation.lift.feature.work.common.data.state.ClearWorkState
import com.gradation.lift.feature.work.common.data.state.WorkRoutineInfoState
import com.gradation.lift.feature.work.common.data.state.WorkState
import com.gradation.lift.feature.work.work.data.WorkViewModel
import com.gradation.lift.feature.work.work.data.state.SnackBarState
import com.gradation.lift.feature.work.work.data.state.WorkDialogUiState
import com.gradation.lift.feature.work.work.data.state.WorkScreenState
import com.gradation.lift.feature.work.work.data.state.WorkScreenUiState
import com.gradation.lift.feature.work.work.data.state.rememberWorkScreenState
import com.gradation.lift.feature.work.work.ui.component.dialog.AutoCompleteDialog
import com.gradation.lift.feature.work.work.ui.component.dialog.CompleteDialog
import com.gradation.lift.feature.work.work.ui.component.dialog.SuspendDialog
import com.gradation.lift.feature.work.work.ui.list.ListScreen
import com.gradation.lift.feature.work.work.ui.rest.RestScreen
import com.gradation.lift.feature.work.work.ui.work.WorkScreen
import com.gradation.lift.model.model.history.CreateHistoryRoutine
import com.gradation.lift.model.model.work.WorkSet
import com.gradation.lift.navigation.Route.WORK_GRAPH_NAME
import com.gradation.lift.ui.extensions.showImmediatelySnackbar
import kotlinx.datetime.LocalTime
import kotlinx.datetime.LocalTime.Companion.fromSecondOfDay

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun WorkRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateWorkToCompleteInWorkGraph: () -> Unit,
    navigateWorkGraphToHomeGraph: () -> Unit,
    navigateWorkToFindWorkCategoryInWorkGraph: () -> Unit,
    sharedViewModel: WorkSharedViewModel = hiltViewModel(
        remember { navController.getBackStackEntry(WORK_GRAPH_NAME) }
    ),
    viewModel: WorkViewModel = hiltViewModel(),
    workState: WorkState = sharedViewModel.workState,
    workRoutineInfoState: WorkRoutineInfoState = sharedViewModel.workRoutineInfoState,
    workScreenState: WorkScreenState = rememberWorkScreenState(workState),
) {

    val clearWorkState: ClearWorkState by viewModel.clearWorkState.collectAsStateWithLifecycle()

    val workScreenUiState: WorkScreenUiState by workScreenState.workScreenUiState.collectAsStateWithLifecycle()
    val workDialogUiState: WorkDialogUiState by workScreenState.workDialogUiState.collectAsStateWithLifecycle()
    val autoCompleteState: Boolean by workScreenState.autoCompleteState.collectAsStateWithLifecycle()

    val workTime: LocalTime by sharedViewModel.workState.workTime.collectAsStateWithLifecycle()
    val restTime: LocalTime by sharedViewModel.workState.restTime.collectAsStateWithLifecycle()
    val currentWorkRoutineIndex: Int by sharedViewModel.workState.currentWorkRoutineIndex.collectAsStateWithLifecycle()

    val clearWork: () -> Unit = viewModel.clearWork
    val setHistoryRoutineList: (List<CreateHistoryRoutine>) -> Unit =
        sharedViewModel.setHistoryRoutineList
    val setHistoryWorkRestTime: (WorkRestTime) -> Unit = sharedViewModel.setHistoryWorkRestTime
    val setProgress: (Float) -> Unit = sharedViewModel.setHistoryProgress


    val progress: Float by animateFloatAsState(
        targetValue = getProgress(workState, workRoutineInfoState),
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessMediumLow,
        ),
        label = "workProgressAnimation"
    )


    BackHandler(onBack = { workScreenState.updateWorkDialogState(WorkDialogUiState.SuspendDialogUi) })


    DisposableEffect(workScreenState.lifecycleOwner) {
        with(LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_STOP || event == Lifecycle.Event.ON_DESTROY) {
                sharedViewModel.fetchWork()
            }
        }) {
            workScreenState.lifecycleOwner.lifecycle.addObserver(this)
            onDispose {
                workScreenState.lifecycleOwner.lifecycle.removeObserver(this)
            }
        }
    }

    LaunchedEffect(getProgress(workState, workRoutineInfoState)) {
        if (
            getProgress(workState, workRoutineInfoState) == 100f && autoCompleteState
        ) {
            workScreenState.offAutoCompleteState()
            workScreenState.updateWorkDialogState(WorkDialogUiState.CompleteDialogUi)
        }
    }


    LaunchedEffect(workScreenState.pagerState, currentWorkRoutineIndex) {
        snapshotFlow {
            Pair(workScreenState.pagerState.currentPage, currentWorkRoutineIndex)
        }.collect { it ->
            workState.updateCurrentWorkRoutineIndex(it.first)
            workScreenState.pagerState.scrollToPage(it.second)
        }
    }

    LaunchedEffect(workScreenState.snackBarState) {
        when (val result = workScreenState.snackBarState) {
            SnackBarState.None -> {}
            is SnackBarState.Success -> {
                workScreenState.snackbarHostState.showImmediatelySnackbar(result.message)
                workScreenState.updateSnackBarState(SnackBarState.None)
            }
        }
    }
    LaunchedEffect(clearWorkState) {
        when (clearWorkState) {
            ClearWorkState.None -> {}
            ClearWorkState.Success -> {
                navigateWorkGraphToHomeGraph()
            }
        }
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
                            createHistory(
                                workTime,
                                restTime,
                                workState,
                                workRoutineInfoState,
                                setHistoryRoutineList,
                                setHistoryWorkRestTime,
                                setProgress
                            )
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
                        onClickDialogSuspendButton = clearWork,
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
                        completeState = workState.workRoutineList.flatMap { it.workSetList }.size == workRoutineInfoState.checkedWorkSetList.size,
                        onClickDialogCompleteButton = {
                            createHistory(
                                workTime,
                                restTime,
                                workState,
                                workRoutineInfoState,
                                setHistoryRoutineList,
                                setHistoryWorkRestTime,
                                setProgress
                            )
                            navigateWorkToCompleteInWorkGraph()
                        },
                        onClickDialogDismissButton = {
                            workScreenState.updateWorkDialogState(WorkDialogUiState.None)
                        },
                    )
                }
            }

            WorkDialogUiState.None -> {}
        }



        Crossfade(
            targetState = workScreenUiState, label = "workTransition",
            animationSpec =
            when (workScreenUiState) {
                is WorkScreenUiState.ListScreenUi -> tween()
                WorkScreenUiState.RestScreenUi -> tween()
                WorkScreenUiState.WorkScreenUi -> tween()

            },
        ) { screen ->
            when (screen) {
                is WorkScreenUiState.ListScreenUi ->
                    ListScreen(
                        modifier,
                        progress,
                        workTime,
                        workState,
                        navigateWorkToFindWorkCategoryInWorkGraph,
                        workRoutineInfoState,
                        workScreenState
                    )


                WorkScreenUiState.RestScreenUi ->
                    RestScreen(
                        modifier,
                        progress,
                        workTime,
                        restTime,
                        workState,
                        workRoutineInfoState,
                        workScreenState
                    )


                WorkScreenUiState.WorkScreenUi ->
                    WorkScreen(
                        modifier,
                        progress,
                        currentWorkRoutineIndex,
                        workTime,
                        workState,
                        workRoutineInfoState,
                        workScreenState
                    )
            }
        }

    }
}


private fun createHistory(
    workTime: LocalTime,
    restTime: LocalTime,
    workState: WorkState,
    workRoutineInfoState: WorkRoutineInfoState,
    setHistoryRoutineList: (List<CreateHistoryRoutine>) -> Unit,
    setHistoryWorkRestTime: (WorkRestTime) -> Unit,
    setProgress: (Float) -> Unit,
) {
    setHistoryWorkRestTime(
        WorkRestTime(
            workTime = workTime,
            restTime = restTime,
            totalTime = fromSecondOfDay(workTime.toSecondOfDay() + restTime.toSecondOfDay())
        )
    )
    setHistoryRoutineList(
        workState.workRoutineList.map { workRoutine ->
            CreateHistoryRoutine(
                workCategory = workRoutine.workCategoryName,
                workSetList = workRoutine.workSetList.toList()
                    .filter { workSet ->
                        workRoutineInfoState.isChecked(
                            workRoutine.workRoutineId,
                            workSet.id
                        )
                    }.map { workSet ->
                        WorkSet(
                            workSet.id,
                            workSet.weight.toFloat(),
                            workSet.repetition.toInt()
                        )
                    }
            )
        }.filter { it.workSetList.isNotEmpty() }
    )
    setProgress(
        getProgress(workState, workRoutineInfoState)
    )
    workState.stopTime()
}

internal val getProgress: (WorkState, WorkRoutineInfoState) -> Float =
    { workState, workRoutineCheckedInfoState ->
        workState.workRoutineList.flatMap { it.workSetList }.size.let { workSetSize ->
            if (workSetSize == 0) 0f
            else ((workRoutineCheckedInfoState.checkedWorkSetList.size.toFloat() /
                    workSetSize.toFloat()) * 100)
        }
    }


