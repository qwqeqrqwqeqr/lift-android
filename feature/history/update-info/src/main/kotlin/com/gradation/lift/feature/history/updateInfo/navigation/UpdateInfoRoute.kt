package com.gradation.lift.feature.history.updateInfo.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.feature.history.updateInfo.data.UpdateInfoViewModel
import com.gradation.lift.feature.history.updateInfo.data.state.UpdateHistoryInfoState
import com.gradation.lift.feature.history.updateInfo.data.state.UpdateInfoScreenState
import com.gradation.lift.feature.history.updateInfo.data.state.rememberUpdateInfoScreenState
import com.gradation.lift.feature.history.updateInfo.ui.UpdateInfoScreen

@Composable
internal fun UpdateInfoRoute(
    modifier: Modifier = Modifier,
    navigateUpdateInfoToHistoryInHistoryGraph: () -> Unit,
    viewModel: UpdateInfoViewModel = hiltViewModel(),
    updateInfoScreenState: UpdateInfoScreenState = rememberUpdateInfoScreenState(),
) {
    val defaultComment: String = viewModel.defaultComment
    val defaultScore: Int = viewModel.defaultScore
    val date: String = viewModel.date

    val score: Int by viewModel.historyInfoState.score.collectAsStateWithLifecycle()
    val comment: String by viewModel.historyInfoState.comment.collectAsStateWithLifecycle()
    val commentValidator: Validator by viewModel.historyInfoState.commentValidator.collectAsStateWithLifecycle()

    val updateHistoryInfoState: UpdateHistoryInfoState by viewModel.updateHistoryInfoState.collectAsStateWithLifecycle()
    val updateUpdateHistoryInfoState: (UpdateHistoryInfoState) -> Unit =
        viewModel.updateUpdateHistoryInfoState
    val updateHistoryInfo: () -> Unit = viewModel.updateHistoryInfo
    val historyInfoState = viewModel.historyInfoState


    when (val result = updateHistoryInfoState) {
        is UpdateHistoryInfoState.Fail -> {
            LaunchedEffect(true) {
                updateInfoScreenState.snackbarHostState.showSnackbar(result.message)
                updateUpdateHistoryInfoState(UpdateHistoryInfoState.None)
            }
        }

        UpdateHistoryInfoState.None -> {}
        UpdateHistoryInfoState.Success -> {
            navigateUpdateInfoToHistoryInHistoryGraph()
        }
    }

    BackHandler(onBack = navigateUpdateInfoToHistoryInHistoryGraph)

    UpdateInfoScreen(
        modifier,
        defaultComment,
        defaultScore,
        date,
        comment,
        score,
        commentValidator,
        updateHistoryInfo,
        navigateUpdateInfoToHistoryInHistoryGraph,
        historyInfoState,
        updateInfoScreenState
    )

}