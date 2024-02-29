package com.gradation.lift.designsystem.component.chart.state

import com.gradation.lift.designsystem.component.chart.model.WorkCountByMonth
import kotlinx.datetime.LocalDate

data class BarChartState(
    val workCountByMonthList: List<WorkCountByMonth>,
    val selectedDate: LocalDate,
)



