package com.gradation.lift.feature.work.complete.navigation

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.feature.work.common.data.WorkSharedViewModel
import com.gradation.lift.feature.work.common.data.model.WorkRestTime
import com.gradation.lift.feature.work.complete.data.CompleteViewModel
import com.gradation.lift.feature.work.complete.data.state.CompleteScreenState
import com.gradation.lift.feature.work.complete.data.state.rememberCompleteScreenState
import com.gradation.lift.feature.work.complete.ui.CompleteScreen
import com.gradation.lift.model.model.history.CreateHistoryRoutine
import com.gradation.lift.navigation.Route
import kotlinx.datetime.LocalDateTime


@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
internal fun CompleteRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateCompleteToCompleteDetailInWorkGraph: () -> Unit,
    viewModel: CompleteViewModel = hiltViewModel(),
    sharedViewModel: WorkSharedViewModel = hiltViewModel(
        remember { navController.getBackStackEntry(Route.WORK_GRAPH_NAME) }
    ),
    completeScreenState: CompleteScreenState = rememberCompleteScreenState(),
) {


    val historyRoutineList: List<CreateHistoryRoutine> by sharedViewModel.historyRoutineList.collectAsStateWithLifecycle()
    val historyWorkRestTime: WorkRestTime by sharedViewModel.historyWorkRestTime.collectAsStateWithLifecycle()
    val historyProgress: Float by sharedViewModel.historyProgress.collectAsStateWithLifecycle()
    val currentTime :LocalDateTime = viewModel.currentTime


    BackHandler(enabled = false, onBack = {})

    CompleteScreen(
        modifier,
        historyRoutineList,
        historyWorkRestTime,
        historyProgress,
        currentTime,
        navigateCompleteToCompleteDetailInWorkGraph,
        completeScreenState
    )


}