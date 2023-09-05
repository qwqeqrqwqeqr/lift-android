package com.gradation.lift.feature.history.daily_log

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.history.daily_log.component.CalenderView
import com.gradation.lift.feature.history.daily_log.component.EmptyHistoryView
import com.gradation.lift.feature.history.daily_log.component.HistoryView
import com.gradation.lift.feature.history.daily_log.data.HistoryDailyLogViewModel
import com.gradation.lift.feature.history.daily_log.data.model.HistoryScoreWeekDate
import com.gradation.lift.feature.history.daily_log.data.state.HistoryUiState
import com.gradation.lift.model.model.history.History
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HistoryDailyLogRoute(
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

@Composable
fun HistoryDailyLogScreen(
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
    Surface(color = LiftTheme.colorScheme.no17, modifier = modifier.fillMaxSize()) {

        Column(
            modifier = modifier
                .padding(top = 16.dp)
                .verticalScroll(scrollState)
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
}

@Composable
@Preview
fun HistoryDailyLogScreenPreview() {
    LiftMaterialTheme {
        HistoryDailyLogScreen(
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