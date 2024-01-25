package com.gradation.lift.feature.history.history.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.history.history.data.model.HistoryScoreWeekDate
import com.gradation.lift.feature.history.history.ui.component.CalenderView
import com.gradation.lift.feature.history.history.ui.component.EmptyHistoryView
import com.gradation.lift.feature.history.history.ui.component.HistoryView
import com.gradation.lift.model.model.history.History
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    selectedDate: LocalDate,
    selectedHistoryList: List<History>,
    selectedHistory: History,
    historyScoreByMonth: List<HistoryScoreWeekDate>,
    updateSelectedDate: (LocalDate) -> Unit,
    plusMonthSelectedDate: () -> Unit,
    minusMonthSelectedDate: () -> Unit,
    updateSelectedHistoryIndex: (Int) -> Unit,
    scrollState: ScrollState,
) {
    Column(
        modifier = modifier
            .background(LiftTheme.colorScheme.no17)
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(top = 16.dp, bottom = LiftTheme.space.space72)
    ) {
        Column(
            modifier = modifier
                .background(
                    LiftTheme.colorScheme.no5,
                    shape = RoundedCornerShape(18.dp, 18.dp, 0.dp, 0.dp)
                )
                .padding(16.dp),
        ) {
            CalenderView(
                modifier,
                selectedDate,
                historyScoreByMonth,
                updateSelectedDate,
                plusMonthSelectedDate,
                minusMonthSelectedDate
            )
            Spacer(modifier = modifier.padding(16.dp))

            if (selectedHistoryList.isEmpty()) {
                EmptyHistoryView()
            } else {
                HistoryView(
                    modifier,
                    selectedDate,
                    selectedHistoryList,
                    selectedHistory,
                    updateSelectedHistoryIndex
                )
            }
        }
    }
}

@Composable
@Preview
fun HistoryScreenPreview() {
    LiftMaterialTheme {
        HistoryScreen(
            Modifier,
            selectedDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
            selectedHistoryList = emptyList(),
            selectedHistory = History(),
            historyScoreByMonth = emptyList(),
            updateSelectedDate = {},
            plusMonthSelectedDate = {},
            minusMonthSelectedDate = {},
            updateSelectedHistoryIndex = {},
            rememberScrollState()
        )
    }
}