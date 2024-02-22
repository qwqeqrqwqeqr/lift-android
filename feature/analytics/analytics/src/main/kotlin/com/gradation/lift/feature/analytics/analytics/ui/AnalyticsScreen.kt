package com.gradation.lift.feature.analytics.analytics.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.chart.LiftHexagonChart
import com.gradation.lift.designsystem.component.chart.model.WorkCategoryCount
import com.gradation.lift.designsystem.component.chart.model.WorkCountByMonth
import com.gradation.lift.designsystem.component.chart.model.WorkPartCountByMonth
import com.gradation.lift.designsystem.component.chart.state.HexagonChartState
import com.gradation.lift.designsystem.component.text.LiftMultiStyleText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.text.TextWithStyle
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.analytics.analytics.data.model.WeekDateHistoryCount
import com.gradation.lift.feature.analytics.analytics.data.state.AnalyticsScreenState
import com.gradation.lift.feature.analytics.analytics.data.state.HistoryUiState
import com.gradation.lift.feature.analytics.analytics.ui.component.barChart.BarChartView
import com.gradation.lift.feature.analytics.analytics.ui.component.calendar.CalendarView
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
            HistoryUiState.Empty -> {}
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
                        thisMonthWorkCountForPreMonth
                    )
                }
                item {
                    Column(
                        modifier = modifier.padding(
                            horizontal = LiftTheme.space.space20,
                        ),
                        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16)
                    ) {
                        LiftMultiStyleText(
                            modifier,
                            LiftTheme.colorScheme.no9,
                            LiftTextStyle.No1,
                            listOf(
                                TextWithStyle(
                                    text = "이번달",
                                    color = LiftTheme.colorScheme.no4,
                                ),
                                TextWithStyle(text = "은 "),
                                TextWithStyle(
                                    text = "${mostUsedWorkPartInThisMonth}운동",
                                    color = LiftTheme.colorScheme.no4,
                                ),
                                TextWithStyle(text = "을 많이 했어요"),
                            ),
                            TextAlign.Start
                        )
                        LiftHexagonChart(
                            hexagonChartState = HexagonChartState(
                                workPartCountByMonthList, workCountByPreCurrentMonth
                            )
                        )
                    }
                }
            }
        }
    }
}


