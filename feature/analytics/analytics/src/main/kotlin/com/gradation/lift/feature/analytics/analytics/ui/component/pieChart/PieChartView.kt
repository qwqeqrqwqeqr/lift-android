package com.gradation.lift.feature.analytics.analytics.ui.component.pieChart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.chart.LiftPieChart
import com.gradation.lift.designsystem.component.chart.model.WorkCategoryCount
import com.gradation.lift.designsystem.component.chart.state.PieChartState
import com.gradation.lift.designsystem.theme.LiftTheme
import kotlinx.datetime.LocalDate

@Composable
fun PieChartView(
    modifier: Modifier = Modifier,
    selectedDate: LocalDate,
    workCategoryCountByWorkPartList: List<WorkCategoryCount>,
    workPartList: List<String>,
    sumOfWorkCountByWorkPart: Int,
    selectedWorkPart: String,
    mostUsedWorkCategory: String,
    selectedDateTotalWorkCount: Int,
    updateSelectedWorkPart: (String) -> Unit,
    sample: Boolean = false,
) {
    Column(
        modifier = modifier.padding(
            horizontal = LiftTheme.space.space20,
        ),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16)
    ) {
        PieChartHeaderView(
            modifier,
            selectedDate,
            sumOfWorkCountByWorkPart,
            selectedWorkPart,
            mostUsedWorkCategory,
            sample
        )

        LiftPieChart(
            modifier = modifier,
            pieChartState = PieChartState(
                workPartList = workPartList,
                selectedWorkPart = selectedWorkPart,
                workCategoryCountByWorkPartList = workCategoryCountByWorkPartList,
                sumOfWorkCategoryCountByWorkPart = sumOfWorkCountByWorkPart,
                selectedDateTotalWorkCount = selectedDateTotalWorkCount,
                updateSelectedWorkPart = updateSelectedWorkPart
            ),
            sample = sample
        )
    }
}