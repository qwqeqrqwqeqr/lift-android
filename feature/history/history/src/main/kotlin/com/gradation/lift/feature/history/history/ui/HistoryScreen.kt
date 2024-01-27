package com.gradation.lift.feature.history.history.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.history.history.data.model.WeekDateHistoryCount
import com.gradation.lift.feature.history.history.data.state.HistoryScreenState
import com.gradation.lift.model.model.history.History
import kotlinx.datetime.LocalDate

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    today: LocalDate,
    selectedDate: LocalDate,
    selectedHistoryContentIndex: Int,
    calendar: Map<Int, List<WeekDateHistoryCount>>,
    selectedHistory: History?,
    updateSelectedDate: (LocalDate) -> Unit,
    updateSelectedHistoryContentIndex: (Int) -> Unit,
    historyScreenState: HistoryScreenState,
) {
    Column(
        modifier = modifier
            .background(LiftTheme.colorScheme.no5)
            .fillMaxSize()
            .padding(top = LiftTheme.space.space40, bottom = LiftTheme.space.space76)
            .verticalScroll(historyScreenState.scrollState),
    ) {
        CalendarView(modifier, today, selectedDate, calendar, updateSelectedDate)
    }
}


