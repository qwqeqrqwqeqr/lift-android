package com.gradation.lift.feature.analytics.analytics.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.bottomBar.LiftDefaultBottomBar
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.chart.model.WorkCategoryCount
import com.gradation.lift.designsystem.component.chart.model.WorkCountByMonth
import com.gradation.lift.designsystem.component.chart.model.WorkPartCountByMonth
import com.gradation.lift.feature.analytics.analytics.data.state.AnalyticsScreenState
import com.gradation.lift.feature.analytics.analytics.ui.component.barChart.BarChartView
import com.gradation.lift.feature.analytics.analytics.ui.component.hexagonChart.HexagonChartView
import com.gradation.lift.feature.analytics.analytics.ui.component.pieChart.PieChartView
import kotlinx.datetime.LocalDate

fun LazyListScope.emptyAnalyticsScreen(
    modifier: Modifier = Modifier,
    selectedDate: LocalDate,
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
    item { Spacer(modifier = modifier) }
    item {
        BarChartView(
            modifier,
            workCountByMonthList,
            thisMonthWorkCountForPreMonth,
            selectedDate,
            true
        )
    }
    item {
        HexagonChartView(
            modifier,
            selectedDate,
            workPartCountByMonthList,
            workCountByPreCurrentMonth,
            mostUsedWorkPartInThisMonth,
            true
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
            updateSelectedWorkPart,
            true
        )
    }
    item {
        LiftDefaultBottomBar(
            modifier = modifier
        ) {
            LiftSolidButton(
                modifier = modifier.fillMaxWidth(),
                text = "운동하기",
                onClick = {
                    analyticsScreenState.updateWorkBottomSheetView(true)
                }
            )
        }
    }
}