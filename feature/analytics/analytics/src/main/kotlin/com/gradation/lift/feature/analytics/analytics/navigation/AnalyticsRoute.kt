package com.gradation.lift.feature.analytics.analytics.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.designsystem.component.chart.model.WorkCountByMonth
import com.gradation.lift.designsystem.component.chart.model.WorkPartCountByMonth
import com.gradation.lift.feature.analytics.analytics.data.AnalyticsViewModel
import com.gradation.lift.feature.analytics.analytics.data.model.WeekDateHistoryCount
import com.gradation.lift.feature.analytics.analytics.data.state.AnalyticsBarChartState
import com.gradation.lift.feature.analytics.analytics.data.state.AnalyticsCalendarState
import com.gradation.lift.feature.analytics.analytics.data.state.AnalyticsHexagonChartState
import com.gradation.lift.feature.analytics.analytics.data.state.HistoryUiState
import kotlinx.datetime.LocalDate

@Composable
internal fun AnalyticsRoute(
    modifier: Modifier = Modifier,
    viewModel: AnalyticsViewModel = hiltViewModel(),
    analyticsCalendarState: AnalyticsCalendarState = viewModel.analyticsCalendarState,
    analyticsBarChartState: AnalyticsBarChartState = viewModel.analyticsBarChartState,
    analyticsHexagonChartState: AnalyticsHexagonChartState = viewModel.analyticsHexagonChartState,
) {

    val historyUiState: HistoryUiState by viewModel.historyUiState.collectAsStateWithLifecycle()
    val selectedDate: LocalDate by viewModel.selectedDate.collectAsStateWithLifecycle()
    val updateSelectedDate: (LocalDate) -> Unit = viewModel.updateSelectedDate


    val calendar: Map<Int, List<WeekDateHistoryCount>> by analyticsCalendarState.calendar.collectAsStateWithLifecycle()

    val workCountByMonthList: List<WorkCountByMonth> by analyticsBarChartState.workCountByMonthList.collectAsStateWithLifecycle()
    val thisMonthWorkCountForPreMonth: Int by analyticsBarChartState.thisMonthWorkCountForPreMonth.collectAsStateWithLifecycle()

    val workPartCountByMonthList: List<WorkPartCountByMonth> by analyticsHexagonChartState.workPartCountByMonthList.collectAsStateWithLifecycle()
    val workCountByPreCurrentMonth: List<WorkCountByMonth> by analyticsHexagonChartState.workCountByPreCurrentMonth.collectAsStateWithLifecycle()




    Column {
        Text(text = workPartCountByMonthList.toString())
        Text(text = workCountByPreCurrentMonth.toString())
    }

}