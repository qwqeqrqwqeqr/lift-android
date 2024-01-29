package com.gradation.lift.feature.work.completedetail.navigation

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.feature.work.common.data.WorkSharedViewModel
import com.gradation.lift.feature.work.common.data.model.WorkRestTime
import com.gradation.lift.feature.work.completedetail.CompleteDetailScreen
import com.gradation.lift.feature.work.completedetail.data.CompleteDetailViewModel
import com.gradation.lift.feature.work.completedetail.data.state.CompleteDetailScreenState
import com.gradation.lift.feature.work.completedetail.data.state.CreateWorkHistoryState
import com.gradation.lift.feature.work.completedetail.data.state.rememberCompleteDetailScreenState
import com.gradation.lift.model.model.history.CreateHistoryRoutine
import com.gradation.lift.navigation.Route
import kotlinx.datetime.LocalDateTime


@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
internal fun CompleteDetailRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateWorkGraphToHomeGraph: () -> Unit,
    viewModel: CompleteDetailViewModel = hiltViewModel(),
    sharedViewModel: WorkSharedViewModel = hiltViewModel(
        remember { navController.getBackStackEntry(Route.WORK_GRAPH_NAME) }
    ),
    completeDetailScreenState: CompleteDetailScreenState = rememberCompleteDetailScreenState(),
) {

    val score: Int by viewModel.historyInfoState.score.collectAsStateWithLifecycle()
    val comment: String by viewModel.historyInfoState.comment.collectAsStateWithLifecycle()
    val commentValidator: Validator by viewModel.historyInfoState.commentValidator.collectAsStateWithLifecycle()
    val currentTime: LocalDateTime = viewModel.currentTime

    val historyWorkRestTime: WorkRestTime by sharedViewModel.historyWorkRestTime.collectAsStateWithLifecycle()
    val historyRoutineList: List<CreateHistoryRoutine> by sharedViewModel.historyRoutineList.collectAsStateWithLifecycle()
    val historyProgress: Float by sharedViewModel.historyProgress.collectAsStateWithLifecycle()
    val usedRoutineSetIdList: List<Int> by sharedViewModel.usedRoutineSetIdList.collectAsStateWithLifecycle()

    val createHistory: () -> Unit = {
        viewModel.createHistory(
            historyProgress.toInt(),
            historyWorkRestTime,
            historyRoutineList,
            usedRoutineSetIdList
        )
    }

    val createHistoryPassMemo: () -> Unit = {
        viewModel.createHistoryPassMemo(
            historyProgress.toInt(),
            historyWorkRestTime,
            historyRoutineList,
            usedRoutineSetIdList
        )
    }


    val historyInfoState = viewModel.historyInfoState

    val updateCreateWorkHistoryState: (CreateWorkHistoryState) -> Unit =
        viewModel.updateCreateWorkHistoryState
    val createWorkHistoryState: CreateWorkHistoryState by viewModel.createWorkHistoryState.collectAsStateWithLifecycle()

    LaunchedEffect(createWorkHistoryState) {
        when (val result = createWorkHistoryState) {
            is CreateWorkHistoryState.Fail -> {
                completeDetailScreenState.snackbarHostState.showSnackbar(result.message)
                updateCreateWorkHistoryState(CreateWorkHistoryState.None)
            }

            CreateWorkHistoryState.None -> {}
            CreateWorkHistoryState.Success -> {
                navigateWorkGraphToHomeGraph()
            }
        }
    }

    BackHandler(enabled = false, onBack = {})


    CompleteDetailScreen(
        modifier,
        score,
        comment,
        commentValidator,
        currentTime,
        createHistory,
        createHistoryPassMemo,
        historyInfoState,
        completeDetailScreenState
    )
}