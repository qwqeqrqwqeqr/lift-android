package com.gradation.lift.feature.analytics.analytics.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.chart.model.WorkCategoryCount
import com.gradation.lift.designsystem.component.chart.model.WorkCountByMonth
import com.gradation.lift.designsystem.component.chart.model.WorkPartCountByMonth
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.analytics.analytics.data.model.WeekDateHistoryCount
import com.gradation.lift.feature.analytics.analytics.data.state.AnalyticsScreenState
import com.gradation.lift.feature.analytics.analytics.data.state.HistoryUiState
import com.gradation.lift.feature.analytics.analytics.ui.component.barChart.BarChartView
import com.gradation.lift.feature.analytics.analytics.ui.component.calendar.CalendarView
import com.gradation.lift.feature.analytics.analytics.ui.component.empty.emptyAnalyticsScreen
import com.gradation.lift.feature.analytics.analytics.ui.component.hexagonChart.HexagonChartView
import com.gradation.lift.feature.analytics.analytics.ui.component.pieChart.PieChartView
import kotlinx.datetime.LocalDate


@Composable
internal fun AnalyticsScreen(
    modifier: Modifier = Modifier,
    historyUiState: HistoryUiState,
    selectedDate: LocalDate,
    calendar: Map<Int, List<WeekDateHistoryCount>>,
    selectedDateHistoryCount: Int,
    workCountByMonthList: List<WorkCountByMonth>,
    thisMonthWorkCountForPreMonth: Int,
    workPartCountByMonthList: List<WorkPartCountByMonth>,
    workCountByPreCurrentMonth: List<WorkCountByMonth>,
    mostUsedWorkPartInThisMonth: String,
    workPartList: List<String>,
    selectedWorkPart: String,
    workCategoryCountByWorkPartList: List<WorkCategoryCount>,
    sumOfWorkCountByWorkPart: Int,
    mostUsedWorkCategory: String,
    selectedDateTotalWorkCount: Int,
    updateSelectedWorkPart: (String) -> Unit,
    analyticsScreenState: AnalyticsScreenState,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(LiftTheme.colorScheme.no17),
        state = analyticsScreenState.lazyListState,
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space40)
    ) {
        when (historyUiState) {
            is HistoryUiState.Fail -> {}
            HistoryUiState.Loading -> {}
            HistoryUiState.Empty -> {
                emptyAnalyticsScreen(
                    modifier,
                    selectedDate,
                    workCountByMonthList,
                    thisMonthWorkCountForPreMonth,
                    workPartCountByMonthList,
                    workCountByPreCurrentMonth,
                    mostUsedWorkPartInThisMonth,
                    workPartList,
                    selectedWorkPart,
                    workCategoryCountByWorkPartList,
                    sumOfWorkCountByWorkPart,
                    mostUsedWorkCategory,
                    selectedDateTotalWorkCount,
                    updateSelectedWorkPart,
                    analyticsScreenState
                )
            }

            is HistoryUiState.Success -> {
                item {
                    CalendarView(
                        modifier,
                        selectedDate,
                        calendar,
                        selectedDateHistoryCount,
                        analyticsScreenState
                    )
                }
                item {
                    BarChartView(
                        modifier,
                        workCountByMonthList,
                        thisMonthWorkCountForPreMonth,
                        selectedDate
                    )
                }
                item {
                    HexagonChartView(
                        modifier,
                        selectedDate,
                        workPartCountByMonthList,
                        workCountByPreCurrentMonth,
                        mostUsedWorkPartInThisMonth
                    )
                }
                item {
                    PieChartView(
                        modifier,
                        selectedDate,
                        workCategoryCountByWorkPartList,
                        workPartList,
                        sumOfWorkCountByWorkPart,
                        selectedWorkPart,
                        mostUsedWorkCategory,
                        selectedDateTotalWorkCount,
                        updateSelectedWorkPart
                    )
                }
                item { Spacer(modifier = modifier) }
            }
        }
    }
}


