package com.gradation.lift.feature.analytics.analytics.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.gradation.lift.feature.analytics.analytics.ui.component.calendar.CalendarView
import com.gradation.lift.feature.analytics.analytics.ui.component.calendar.HeaderView
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
        state = analyticsScreenState.lazyListState
    ) {
        when (historyUiState) {
            is HistoryUiState.Fail -> {}
            HistoryUiState.Loading -> {}
            HistoryUiState.Empty -> {}
            is HistoryUiState.Success -> {
                item {
                    Column(
                        modifier = modifier
                            .background(LiftTheme.colorScheme.no5)
                            .padding(
                                vertical = LiftTheme.space.space40,
                                horizontal = LiftTheme.space.space20,
                            ),
                        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16)
                    ) {
                        HeaderView(
                            modifier,
                            selectedDate,
                            selectedDateHistoryCount,
                            analyticsScreenState
                        )
                        CalendarView(
                            modifier,
                            selectedDate,
                            calendar,
                            analyticsScreenState
                        )
                    }
                }
            }
        }
    }
}


