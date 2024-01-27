package com.gradation.lift.feature.history.history.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.feature.history.history.data.HistoryViewModel
import com.gradation.lift.feature.history.history.data.model.WeekDateHistoryCount
import com.gradation.lift.feature.history.history.data.state.HistoryScreenState
import com.gradation.lift.feature.history.history.data.state.rememberHistoryScreenState
import com.gradation.lift.feature.history.history.ui.HistoryScreen
import com.gradation.lift.model.model.history.History
import kotlinx.datetime.LocalDate

@Composable
internal fun HistoryRoute(
    modifier: Modifier = Modifier,
    viewModel: HistoryViewModel = hiltViewModel(),
    historyScreenState: HistoryScreenState = rememberHistoryScreenState(),
) {

    val today: LocalDate = viewModel.today
    val selectedDate: LocalDate by viewModel.selectedDate.collectAsStateWithLifecycle()
    val selectedHistoryContentIndex: Int by viewModel.selectedHistoryContentIndex.collectAsStateWithLifecycle()
    val calendar: Map<Int, List<WeekDateHistoryCount>> by viewModel.calendar.collectAsStateWithLifecycle()
    val selectedHistory: History? by viewModel.selectedHistory.collectAsStateWithLifecycle()


    val updateSelectedDate: (LocalDate) -> Unit = viewModel.updateSelectedDate
    val updateSelectedHistoryContentIndex: (Int) -> Unit =
        viewModel.updateSelectedHistoryContentIndex



    HistoryScreen(
        modifier,
        today,
        selectedDate,
        selectedHistoryContentIndex,
        calendar,
        selectedHistory,
        updateSelectedDate,
        updateSelectedHistoryContentIndex,
        historyScreenState
    )
}