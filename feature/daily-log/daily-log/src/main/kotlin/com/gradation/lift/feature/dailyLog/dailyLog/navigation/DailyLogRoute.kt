package com.gradation.lift.feature.dailyLog.dailyLog.navigation

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.feature.dailyLog.dailyLog.data.HistoryDailyLogViewModel
import com.gradation.lift.feature.dailyLog.dailyLog.data.model.HistoryScoreWeekDate
import com.gradation.lift.feature.dailyLog.dailyLog.data.state.HistoryUiState
import com.gradation.lift.feature.dailyLog.dailyLog.ui.HistoryDailyLogScreen
import com.gradation.lift.model.model.history.History
import kotlinx.datetime.LocalDate

@Composable
internal fun DailyLogRoute(
    modifier: Modifier = Modifier,
    viewModel: HistoryDailyLogViewModel = hiltViewModel(),
) {
    val historyUiState: HistoryUiState by viewModel.historyUiState.collectAsStateWithLifecycle()

    val selectedDate: LocalDate by viewModel.selectedDate.collectAsStateWithLifecycle()
    val selectedHistoryList: List<History> by viewModel.selectedHistoryList.collectAsStateWithLifecycle()
    val selectedHistory: History by viewModel.selectedHistory.collectAsStateWithLifecycle()
    val historyScoreByMonth: List<HistoryScoreWeekDate> by viewModel.historyScoreByMonth.collectAsStateWithLifecycle()

    val updateSelectedDate: (LocalDate) -> Unit = viewModel.updateSelectedDate()
    val plusMonthSelectedDate: () -> Unit = viewModel.plusMonthSelectedDate()
    val minusMonthSelectedDate: () -> Unit = viewModel.minusMonthSelectedDate()
    val updateSelectedHistoryIndex: (Int) -> Unit = viewModel.updateSelectedHistoryIndex()

    val scrollState: ScrollState = rememberScrollState()


    HistoryDailyLogScreen(
        modifier,
        selectedDate,
        selectedHistoryList,
        selectedHistory,
        historyScoreByMonth,
        updateSelectedDate,
        plusMonthSelectedDate,
        minusMonthSelectedDate,
        updateSelectedHistoryIndex,
        scrollState
    )

}