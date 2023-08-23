package com.gradation.lift.feature.history.analytics

import android.os.Build
import androidx.annotation.RequiresApi
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
import com.gradation.lift.feature.history.analytics.component.WorkFrequencyAnalyticsScreen
import com.gradation.lift.feature.history.analytics.data.HistoryAnalyticsViewModel
import com.gradation.lift.feature.history.analytics.data.model.WorkFrequencyWeekDate
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


    val selectedMonth: LocalDate by viewModel.workFrequencyAnalyticsState.selectedMonth.collectAsStateWithLifecycle()
    val historyCountByCurrentMonth: Int by viewModel.workFrequencyAnalyticsState.historyCountByCurrentMonth.collectAsStateWithLifecycle()
    val workFrequencyByWeek: List<WorkFrequencyWeekDate> by viewModel.workFrequencyAnalyticsState.workFrequencyByWeek.collectAsStateWithLifecycle()


    val plusSelectedMonth: () -> Unit =
        viewModel.workFrequencyAnalyticsState.plusSelectedMonth()

    val minusSelectedMonth: () -> Unit =
        viewModel.workFrequencyAnalyticsState.minusSelectedMonth()


    HistoryAnalyticsScreen(
        modifier,
        selectedMonth,
        historyCountByCurrentMonth,
        workFrequencyByWeek,
        plusSelectedMonth,
        minusSelectedMonth,
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
) {
    Surface(color = LiftTheme.colorScheme.no17, modifier = modifier.fillMaxSize()) {

        Column(
            modifier = modifier
                .padding(top = 16.dp)
        ) {

            WorkFrequencyAnalyticsScreen(
                modifier,
                selectedMonth,
                historyCountByCurrentMonth,
                workFrequencyByWeek,
                plusSelectedMonth,
                minusSelectedMonth
            )

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
            minusSelectedMonth = {}
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