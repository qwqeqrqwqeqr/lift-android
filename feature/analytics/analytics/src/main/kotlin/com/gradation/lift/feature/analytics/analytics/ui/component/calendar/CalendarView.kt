package com.gradation.lift.feature.analytics.analytics.ui.component.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.analytics.analytics.data.model.WeekDateHistoryCount
import com.gradation.lift.feature.analytics.analytics.data.state.AnalyticsScreenState
import kotlinx.datetime.LocalDate

@Composable
fun CalendarView(
    modifier: Modifier = Modifier,
    selectedDate: LocalDate,
    calendar: Map<Int, List<WeekDateHistoryCount>>,
    selectedDateHistoryCount: Int,
    analyticsScreenState: AnalyticsScreenState,
) {
    Column(
        modifier = modifier
            .background(LiftTheme.colorScheme.no5)
            .padding(
                vertical = LiftTheme.space.space40,
                horizontal = LiftTheme.space.space20,
            ),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16)
    ) {
        CalendarHeaderView(
            modifier,
            selectedDate,
            selectedDateHistoryCount,
            analyticsScreenState
        )
        CalendarContentView(
            modifier,
            selectedDate,
            calendar,
            analyticsScreenState
        )
    }
}