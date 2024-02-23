package com.gradation.lift.feature.analytics.analytics.ui.component.barChart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.chart.LiftBarChart
import com.gradation.lift.designsystem.component.chart.model.WorkCountByMonth
import com.gradation.lift.designsystem.component.chart.state.BarChartState
import com.gradation.lift.designsystem.theme.LiftTheme
import kotlinx.datetime.LocalDate

@Composable
fun BarChartView(
    modifier: Modifier = Modifier,
    workCountByMonthList: List<WorkCountByMonth>,
    thisMonthWorkCountForPreMonth: Int,
    selectedDate: LocalDate,
    sample: Boolean = false,
) {
    Column(
        modifier = modifier.padding(
            horizontal = LiftTheme.space.space20,
        ),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16)
    ) {
        BarChartHeaderView(
            modifier,
            workCountByMonthList,
            thisMonthWorkCountForPreMonth,
            sample
        )
        LiftBarChart(
            modifier = modifier,
            barChartState = BarChartState(
                workCountByMonthList,
                selectedDate
            ),
            sample = sample
        )
    }
}