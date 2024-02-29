package com.gradation.lift.designsystem.component.chart.model

import com.gradation.lift.designsystem.component.chart.state.BarChartState
import com.gradation.lift.designsystem.component.chart.state.HexagonChartState
import com.gradation.lift.designsystem.component.chart.state.PieChartState
import kotlinx.datetime.LocalDate

object SampleData {

    val BAR_CHART_SAMPLE_DATA = BarChartState(
        listOf(
            WorkCountByMonth(25, LocalDate(2023, 9, 5)),
            WorkCountByMonth(8, LocalDate(2023, 10, 5)),
            WorkCountByMonth(28, LocalDate(2023, 11, 5)),
            WorkCountByMonth(1, LocalDate(2023, 12, 5)),
            WorkCountByMonth(25, LocalDate(2024, 1, 5)),
            WorkCountByMonth(15, LocalDate(2024, 2, 5)),
        ),
        LocalDate(2024, 1, 1)
    )

    val HEXAGON_CHART_SAMPLE_DATA = HexagonChartState(
        workPartCountByMonthList = listOf(
            WorkPartCountByMonth("등", 27, 8),
            WorkPartCountByMonth("복근", 5, 13),
            WorkPartCountByMonth("가슴", 30, 30),
            WorkPartCountByMonth("어깨", 10, 25),
            WorkPartCountByMonth("하체", 15, 32),
            WorkPartCountByMonth("팔", 19, 3)
        ),
        workCountByMonthList = listOf(
            WorkCountByMonth(12, LocalDate(2024, 1, 5)),
            WorkCountByMonth(25, LocalDate(2024, 2, 5)),
        ),
        LocalDate(2024, 1, 1)

    )

    val PIE_CHART_SAMPLE_DATA = PieChartState(
        workPartList = listOf("가슴", "어깨", "팔", "등", "복근", "하체"),
        selectedWorkPart = "가슴",
        workCategoryCountByWorkPartList = listOf(
            WorkCategoryCount("벤치프레스", 10),
            WorkCategoryCount("푸쉬업", 20),
            WorkCategoryCount("벤치프레스 머신", 35),
            WorkCategoryCount("인클라인 스미스머신 벤치프레스", 20),
        ),
        sumOfWorkCategoryCountByWorkPart = 72,
        selectedDateTotalWorkCount = 101,
        updateSelectedWorkPart = {}
    )
}