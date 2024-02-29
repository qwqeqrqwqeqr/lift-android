package com.gradation.lift.designsystem.component.chart.state

import com.gradation.lift.designsystem.component.chart.model.WorkCountByMonth
import com.gradation.lift.designsystem.component.chart.model.WorkPartCountByMonth
import kotlinx.datetime.LocalDate

/**
 *  [workPartCountByMonthList] 총 6개의 부위
 *  [workCountByMonthList] 이번 달과 현재달 (2개만 사용할 것)
 *  @since 2024-02-20 12:57:04
 */
data class HexagonChartState(
    val workPartCountByMonthList: List<WorkPartCountByMonth>,
    val workCountByMonthList: List<WorkCountByMonth>,
    val selectedDate: LocalDate,
)
