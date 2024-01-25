package com.gradation.lift.feature.history.history.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.history.history.ui.component.history_view.HistoryCommentView
import com.gradation.lift.feature.history.history.ui.component.history_view.HistoryRoutineView
import com.gradation.lift.feature.history.history.ui.component.history_view.HistoryScoreView
import com.gradation.lift.feature.history.history.ui.component.history_view.HistoryTimeView
import com.gradation.lift.feature.history.history.ui.component.history_view.HistoryTimesListView
import com.gradation.lift.model.model.history.History
import com.gradation.lift.ui.mapper.toDayMonthText
import kotlinx.datetime.LocalDate

@Composable
fun HistoryView(
    modifier: Modifier = Modifier,
    selectedDate: LocalDate,
    selectedHistoryList: List<History>,
    selectedHistory: History,
    updateSelectedHistoryIndex: (Int) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = selectedDate.toDayMonthText(),
            color = LiftTheme.colorScheme.no9,
            style = LiftTheme.typography.no1
        )
        Spacer(modifier = modifier.padding(16.dp))
        HistoryScoreView(modifier, selectedHistory)
        Spacer(modifier = modifier.padding(16.dp))
        HistoryTimesListView(
            modifier,
            selectedHistoryList,
            selectedHistory,
            updateSelectedHistoryIndex
        )
        Spacer(modifier = modifier.padding(16.dp))
        HistoryTimeView(modifier, selectedHistory)
        Spacer(modifier = modifier.padding(16.dp))
        HistoryCommentView(modifier, selectedHistory)
        Spacer(modifier = modifier.padding(16.dp))
        HistoryRoutineView(modifier, selectedHistory)
    }
}
