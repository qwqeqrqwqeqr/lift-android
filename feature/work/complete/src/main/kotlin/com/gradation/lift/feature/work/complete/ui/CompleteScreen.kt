package com.gradation.lift.feature.work.complete.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.designsystem.component.LiftErrorSnackBar
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.work.common.data.WorkRestTime
import com.gradation.lift.feature.work.complete.data.state.CompleteScreenState
import com.gradation.lift.feature.work.complete.data.state.HistoryInfoState
import com.gradation.lift.feature.work.complete.ui.component.CommentView
import com.gradation.lift.feature.work.complete.ui.component.EvaluationView
import com.gradation.lift.feature.work.complete.ui.component.HeaderView
import com.gradation.lift.feature.work.complete.ui.component.HistoryRoutineView
import com.gradation.lift.feature.work.complete.ui.component.NavigationView
import com.gradation.lift.feature.work.complete.ui.component.TimeView
import com.gradation.lift.model.model.history.CreateHistoryRoutine


@Composable
internal fun CompleteScreen(
    modifier: Modifier = Modifier,
    score: Int,
    comment: String,
    historyWorkRestTime: WorkRestTime,
    historyRoutineList: List<CreateHistoryRoutine>,
    commentValidator: Validator,
    historyInfoState: HistoryInfoState,
    completeScreenState: CompleteScreenState,
    createHistory: (WorkRestTime, List<CreateHistoryRoutine>) -> Unit,
) {
    Scaffold(
        snackbarHost = {
            LiftErrorSnackBar(
                modifier = modifier,
                snackbarHostState = completeScreenState.snackbarHostState
            )
        },
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier.padding(it)
        ) {
            Column(
                modifier = modifier
                    .background(LiftTheme.colorScheme.no5)
                    .fillMaxSize()
                    .weight(1f)
                    .verticalScroll(completeScreenState.scrollState),
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16)
            ) {
                HeaderView(modifier)
                TimeView(modifier, historyWorkRestTime)
                EvaluationView(modifier, score, historyInfoState)
                CommentView(
                    modifier,
                    comment,
                    commentValidator,
                    historyInfoState,
                    completeScreenState
                )
                HistoryRoutineView(modifier, historyRoutineList)
            }
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .background(LiftTheme.colorScheme.no5)
                    .padding(LiftTheme.space.paddingSpace)
            ) {
                NavigationView(modifier, historyWorkRestTime, historyRoutineList, createHistory)
            }
        }
    }
}


