package com.gradation.lift.feature.history.analytics

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
import com.gradation.lift.feature.history.analytics.component.WorkCountByMonthAnalyticsScreen
import com.gradation.lift.feature.history.analytics.component.WorkFrequencyAnalyticsScreen
import com.gradation.lift.feature.history.analytics.data.HistoryAnalyticsViewModel
import com.gradation.lift.feature.history.analytics.data.model.WorkFrequencyMonth
import com.gradation.lift.feature.history.analytics.data.model.WorkFrequencyWeekDate
import com.gradation.lift.feature.history.analytics.data.state.HistoryUiState
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HistoryAnalyticsRoute(
    modifier: Modifier = Modifier,
    viewModel: HistoryAnalyticsViewModel = hiltViewModel(),
) {

    val historyUiState: HistoryUiState by viewModel.historyUiState.collectAsStateWithLifecycle()
    val historyCountByCurrentMonth: Int by viewModel.historyCountByCurrentMonth.collectAsStateWithLifecycle()


    val selectedMonth: LocalDate by viewModel.workFrequencyAnalyticsState.selectedMonth.collectAsStateWithLifecycle()
    val workFrequencyByWeek: List<WorkFrequencyWeekDate> by viewModel.workFrequencyAnalyticsState.workFrequencyByWeek.collectAsStateWithLifecycle()


    val plusSelectedMonth: () -> Unit =
        viewModel.workFrequencyAnalyticsState.plusSelectedMonth()

    val minusSelectedMonth: () -> Unit =
        viewModel.workFrequencyAnalyticsState.minusSelectedMonth()


    val historyCountByPreMonth: Int by viewModel.workCountByMonthAnalyticsState.historyCountByPreMonth.collectAsStateWithLifecycle()
    val historyCountByMonthList: List<WorkFrequencyMonth> by viewModel.workCountByMonthAnalyticsState.historyCountByMonthList.collectAsStateWithLifecycle()
    val historyAveragePreCount: Int by viewModel.workCountByMonthAnalyticsState.historyAveragePreCount.collectAsStateWithLifecycle()
    val historyAverageCurrentCount: Int by viewModel.workCountByMonthAnalyticsState.historyAverageCurrentCount.collectAsStateWithLifecycle()


    val scrollState = rememberScrollState()
    HistoryAnalyticsScreen(
        modifier,
        selectedMonth,
        historyCountByCurrentMonth,
        workFrequencyByWeek,
        plusSelectedMonth,
        minusSelectedMonth,
        historyCountByPreMonth,
        historyCountByMonthList,
        historyAveragePreCount,
        historyAverageCurrentCount,
        scrollState
    )
}


@Composable
internal fun HistoryAnalyticsScreen(
    modifier: Modifier = Modifier,
    selectedMonth: LocalDate,
    historyCountByCurrentMonth: Int,
    workFrequencyByWeek: List<WorkFrequencyWeekDate>,
    plusSelectedMonth: () -> Unit,
    minusSelectedMonth: () -> Unit,
    historyCountByPreMonth: Int,
    historyCountByMonthList: List<WorkFrequencyMonth>,
    historyAveragePreCount: Int,
    historyAverageCurrentCount: Int,
    scrollState: ScrollState,
) {
    Surface(color = LiftTheme.colorScheme.no17, modifier = modifier.fillMaxSize()) {

        Column(
            modifier = modifier
                .padding(top = 16.dp)
                .verticalScroll(scrollState)
        ) {

            WorkFrequencyAnalyticsScreen(
                modifier,
                selectedMonth,
                historyCountByCurrentMonth,
                workFrequencyByWeek,
                plusSelectedMonth,
                minusSelectedMonth
            )
            Spacer(modifier = modifier.padding(16.dp))
            WorkCountByMonthAnalyticsScreen(
                modifier,
                historyCountByCurrentMonth,
                historyCountByPreMonth,
                historyCountByMonthList,
                historyAveragePreCount,
                historyAverageCurrentCount
            )
            Spacer(modifier = modifier.padding(16.dp))
        }
    }
}


@Preview
@Composable
fun HistoryAnalyticsScreenPreview() {
    LiftMaterialTheme {
        HistoryAnalyticsScreen(
            selectedMonth = Clock.System.todayIn(TimeZone.currentSystemDefault()),
            historyCountByCurrentMonth = 32,
            workFrequencyByWeek = emptyList(),
            plusSelectedMonth = {},
            minusSelectedMonth = {},
            historyCountByPreMonth = 15,
            historyCountByMonthList = listOf(
                WorkFrequencyMonth(),
                WorkFrequencyMonth(),
                WorkFrequencyMonth(),
                WorkFrequencyMonth(),
                WorkFrequencyMonth(),
                WorkFrequencyMonth(),
                WorkFrequencyMonth(),
            ),
            historyAverageCurrentCount = 25,
            historyAveragePreCount = 30,
            scrollState = rememberScrollState()
        )
    }
}

fun Int.weekToText(): String = when (this) {
    1 -> "첫째"
    2 -> "둘째"
    3 -> "셋째"
    4 -> "넷째"
    5 -> "다섯째"
    6 -> "여섯째"
    else -> ""
}
