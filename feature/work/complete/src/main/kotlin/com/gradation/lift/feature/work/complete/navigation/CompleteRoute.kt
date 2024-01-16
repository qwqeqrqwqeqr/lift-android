package com.gradation.lift.feature.work.complete.navigation

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
import com.gradation.lift.feature.work.common.data.model.WorkRestTime
import com.gradation.lift.feature.work.common.data.WorkSharedViewModel
import com.gradation.lift.feature.work.complete.data.CompleteViewModel
import com.gradation.lift.feature.work.complete.data.state.CompleteScreenState
import com.gradation.lift.feature.work.complete.data.state.CreateWorkHistoryState
import com.gradation.lift.feature.work.complete.data.state.HistoryInfoState
import com.gradation.lift.feature.work.complete.data.state.rememberCompleteScreenState
import com.gradation.lift.feature.work.complete.ui.CompleteScreen
import com.gradation.lift.model.model.history.CreateHistoryRoutine
import com.gradation.lift.navigation.Route


@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
internal fun CompleteRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateWorkGraphToHomeGraph: () -> Unit,
    viewModel: CompleteViewModel = hiltViewModel(),
    sharedViewModel: WorkSharedViewModel = hiltViewModel(
        remember { navController.getBackStackEntry(Route.WORK_GRAPH_NAME) }
    ),
    completeScreenState: CompleteScreenState = rememberCompleteScreenState(),
) {

    val score: Int by viewModel.historyInfoState.score.collectAsStateWithLifecycle()
    val comment: String by viewModel.historyInfoState.comment.collectAsStateWithLifecycle()
    val commentValidator: Validator by viewModel.historyInfoState.commentValidator.collectAsStateWithLifecycle()
    val createWorkHistoryState: CreateWorkHistoryState by viewModel.createWorkHistoryState.collectAsStateWithLifecycle()

    val historyWorkRestTime: WorkRestTime by sharedViewModel.historyWorkRestTime.collectAsStateWithLifecycle()
    val historyRoutineList: List<CreateHistoryRoutine> by sharedViewModel.historyRoutineList.collectAsStateWithLifecycle()


    val historyInfoState: HistoryInfoState = viewModel.historyInfoState
    val updateCreateWorkHistoryState: (CreateWorkHistoryState) -> Unit =
        viewModel.updateCreateWorkHistoryState
    val createHistory: (WorkRestTime, List<CreateHistoryRoutine>) -> Unit = viewModel.createHistory

    when (val createWorkHistoryStateResult = createWorkHistoryState) {
        is CreateWorkHistoryState.Fail -> {
            LaunchedEffect(true) {
                completeScreenState.snackbarHostState.showSnackbar(
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


    BackHandler(onBack = { createHistory(historyWorkRestTime, historyRoutineList) })


    CompleteScreen(
        modifier,
        score,
        comment,
        historyWorkRestTime,
        historyRoutineList,
        commentValidator,
        historyInfoState,
        completeScreenState,
        createHistory
    )
}