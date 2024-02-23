package com.gradation.lift.feature.analytics.analytics.ui.component.hexagonChart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.chart.LiftHexagonChart
import com.gradation.lift.designsystem.component.chart.model.WorkCountByMonth
import com.gradation.lift.designsystem.component.chart.model.WorkPartCountByMonth
import com.gradation.lift.designsystem.component.chart.state.HexagonChartState
import com.gradation.lift.designsystem.theme.LiftTheme
import kotlinx.datetime.LocalDate

@Composable
fun HexagonChartView(
    modifier: Modifier = Modifier,
    selectedDate: LocalDate,
    workPartCountByMonthList: List<WorkPartCountByMonth>,
    workCountByPreCurrentMonth: List<WorkCountByMonth>,
    mostUsedWorkPartInThisMonth: String,
    sample: Boolean = false,
) {
    Column(
        modifier = modifier.padding(
            horizontal = LiftTheme.space.space20,
        ),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16)
    ) {
        HexagonChartHeaderView(
            modifier, selectedDate, workCountByPreCurrentMonth, mostUsedWorkPartInThisMonth, sample
        )
        LiftHexagonChart(
            modifier = modifier,
            hexagonChartState = HexagonChartState(
                workPartCountByMonthList, workCountByPreCurrentMonth, selectedDate
            ),
            sample = sample
        )

    }
}