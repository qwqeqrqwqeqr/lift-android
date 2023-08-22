package com.gradation.lift.feature.work.complete

import android.annotation.SuppressLint
import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.designsystem.component.LiftErrorSnackBar
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.work.complete.component.*
import com.gradation.lift.feature.work.complete.data.WorkCompleteViewModel
import com.gradation.lift.feature.work.complete.data.state.CreateWorkHistoryState
import com.gradation.lift.feature.work.work.data.model.WorkRestTime
import com.gradation.lift.feature.work.work.data.viewmodel.WorkSharedViewModel
import com.gradation.lift.model.model.history.CreateHistoryRoutine
import com.gradation.lift.navigation.Router

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnrememberedGetBackStackEntry")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkCompleteRoute(
    navController: NavController,
    navigateWorkGraphToHomeGraph: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: WorkCompleteViewModel = hiltViewModel(),
) {

    val workBackStackEntry: NavBackStackEntry =
        remember { navController.getBackStackEntry(Router.WORK_GRAPH_NAME) }
    val sharedViewModel: WorkSharedViewModel = hiltViewModel(workBackStackEntry)

    val score: Int by viewModel.score.collectAsStateWithLifecycle()
    val comment: String by viewModel.comment.collectAsStateWithLifecycle()
    val commentValidator: Validator by viewModel.commentValidator.collectAsStateWithLifecycle()
    val createWorkHistoryState: CreateWorkHistoryState by viewModel.createWorkHistoryState.collectAsStateWithLifecycle()

    val historyWorkRestTime: WorkRestTime by sharedViewModel.historyWorkRestTime.collectAsStateWithLifecycle()
    val historyRoutineList: List<CreateHistoryRoutine> by sharedViewModel.historyRoutine.collectAsStateWithLifecycle()

    val updateScore: (Int) -> Unit = viewModel.updateScore()
    val updateComment: (String) -> Unit = viewModel.updateComment()
    val updateCreateWorkHistoryState: (CreateWorkHistoryState) -> Unit =
        viewModel.updateCreateWorkHistoryState()

    val createWorkHistory: () -> Unit = viewModel.createWorkHistory(
        comment = comment,
        score = score,
        workTime = historyWorkRestTime.workTime,
        restTime = historyWorkRestTime.restTime,
        totalTime = historyWorkRestTime.totalTime,
        historyRoutine = historyRoutineList
    )
    val scrollState: ScrollState = rememberScrollState()
    val snackbarHostState: SnackbarHostState by remember { mutableStateOf(SnackbarHostState()) }
    val focusManager: FocusManager = LocalFocusManager.current

    WorkCompleteScreen(
        modifier,
        score,
        comment,
        commentValidator,
        historyWorkRestTime,
        historyRoutineList,
        updateScore,
        updateComment,
        createWorkHistory,
        scrollState,
        snackbarHostState,
        focusManager
    )


    when (val createWorkHistoryStateResult = createWorkHistoryState) {
        is CreateWorkHistoryState.Fail -> {
            LaunchedEffect(true) {
                snackbarHostState.showSnackbar(
                    message = createWorkHistoryStateResult.message
                )
                updateCreateWorkHistoryState(CreateWorkHistoryState.None)
            }
        }
        CreateWorkHistoryState.None -> {}
        CreateWorkHistoryState.Success -> {
            LaunchedEffect(true) {
                navigateWorkGraphToHomeGraph()
            }
        }
    }

    LaunchedEffect(true) {
        sharedViewModel.workState.stopTime()
    }
    BackHandler(onBack = createWorkHistory)

}

@ExperimentalMaterial3Api
@Composable
fun WorkCompleteScreen(
    modifier: Modifier = Modifier,
    score: Int,
    comment: String,
    commentValidator: Validator,
    historyWorkRestTime: WorkRestTime,
    historyRoutineList: List<CreateHistoryRoutine>,
    updateScore: (Int) -> Unit,
    updateComment: (String) -> Unit,
    createWorkHistory: () -> Unit,
    scrollState: ScrollState,
    snackbarHostState: SnackbarHostState,
    focusManager: FocusManager,
) {
    Scaffold(
        snackbarHost = {
            LiftErrorSnackBar(
                modifier = modifier,
                snackbarHostState = snackbarHostState
            )
        },
        modifier = modifier.fillMaxSize()
    ) {
        Surface(
            color = LiftTheme.colorScheme.no5,
            modifier = modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = modifier.verticalScroll(scrollState)
            ) {
                HeaderView(modifier)
                Spacer(modifier = modifier.padding(8.dp))
                TimeView(modifier, historyWorkRestTime)
                Spacer(modifier = modifier.padding(16.dp))
                EvaluationView(modifier, score, updateScore)
                Spacer(modifier = modifier.padding(16.dp))
                CommentView(modifier, comment, commentValidator, updateComment, focusManager)
                Spacer(modifier = modifier.padding(16.dp))
                HistoryRoutineView(modifier, historyRoutineList)
                Spacer(modifier = modifier.padding(16.dp))
                NavigationView(modifier, createWorkHistory)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun WorkCompleteScreenPreview() {
    LiftMaterialTheme {
        WorkCompleteScreen(
            score = 5,
            comment = "",
            commentValidator = Validator(),
            historyWorkRestTime = WorkRestTime(),
            historyRoutineList = emptyList(),
            updateScore = {},
            updateComment = {},
            createWorkHistory = {},
            scrollState = rememberScrollState(),
            snackbarHostState = SnackbarHostState(),
            focusManager = LocalFocusManager.current
        )
    }
}