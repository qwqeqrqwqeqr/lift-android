package com.gradation.lift.feature.analytics.analytics.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.analytics.analytics.ui.component.WorkCountByMonthAnalyticsScreen
import com.gradation.lift.feature.analytics.analytics.ui.component.WorkFrequencyAnalyticsScreen
import com.gradation.lift.feature.analytics.analytics.data.model.WorkFrequencyMonth
import com.gradation.lift.feature.analytics.analytics.data.model.WorkFrequencyWeekDate
import com.gradation.lift.feature.analytics.analytics.data.model.WorkPartAnalyticsTargetDate
import com.gradation.lift.feature.analytics.analytics.data.model.WorkPartAnalyticsTargetType
import com.gradation.lift.feature.analytics.analytics.data.model.WorkPartFrequency
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn


@Composable
internal fun AnalyticsScreen(
    modifier: Modifier = Modifier,
    selectedMonth: LocalDate,
    historyCountByCurrentMonth: Int,
    workFrequencyByWeek: List<WorkFrequencyWeekDate>,
    plusSelectedMonth: () -> Unit,
    minusSelectedMonth: () -> Unit,
    historyCountByPreMonth: Int,
    historyCountByMonthList: List<WorkFrequencyMonth>,
    historyAveragePreCount: Int,
    historyAverageCurrentCount: Int,
    workPartAnalyticsTargetDate: WorkPartAnalyticsTargetDate,
    workPartAnalyticsTargetType: WorkPartAnalyticsTargetType,
    historyWorkPartCountByPre: WorkPartFrequency,
    historyWorkPartCountByCurrent: WorkPartFrequency,
    historyCountByPre: Int,
    historyCountByCurrent: Int,
    maxOfWorkPartFrequency: String,
    updateWorkPartAnalyticsTargetDate: (WorkPartAnalyticsTargetDate) -> Unit,
    updateWorkPartAnalyticsTargetType: (WorkPartAnalyticsTargetType) -> Unit,
    scrollState: ScrollState,
) {
    Column(
        modifier = modifier
            .background(LiftTheme.colorScheme.no17)
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(top = 16.dp, bottom = LiftTheme.space.space72)
    ) {

        WorkFrequencyAnalyticsScreen(
            modifier,
            selectedMonth,
            historyCountByCurrentMonth,
            workFrequencyByWeek,
            plusSelectedMonth,
            minusSelectedMonth
        )
        Spacer(modifier = modifier.padding(16.dp))
        WorkCountByMonthAnalyticsScreen(
            modifier,
            historyCountByCurrentMonth,
            historyCountByPreMonth,
            historyCountByMonthList,
            historyAveragePreCount,
            historyAverageCurrentCount
        )
        Spacer(modifier = modifier.padding(16.dp))
        WorkFrequencyAnalyticsScreen(
            modifier,
            workPartAnalyticsTargetDate,
            workPartAnalyticsTargetType,
            historyWorkPartCountByPre,
            historyWorkPartCountByCurrent,
            historyCountByPre,
            historyCountByCurrent,
            maxOfWorkPartFrequency,
            updateWorkPartAnalyticsTargetDate,
            updateWorkPartAnalyticsTargetType
        )

    }
}


@Preview(
    device = "spec:width=1080px,height=4800px,dpi=440"
)
@Composable
fun HistoryAnalyticsScreenPreview() {
    LiftMaterialTheme {
        AnalyticsScreen(
            selectedMonth = Clock.System.todayIn(TimeZone.currentSystemDefault()),
            historyCountByCurrentMonth = 32,
            workFrequencyByWeek = emptyList(),
            plusSelectedMonth = {},
            minusSelectedMonth = {},
            historyCountByPreMonth = 15,
            historyCountByMonthList = listOf(
                WorkFrequencyMonth(1, 2),
                WorkFrequencyMonth(2, 1),
                WorkFrequencyMonth(3, 12),
                WorkFrequencyMonth(4, 0),
                WorkFrequencyMonth(5, 15),
                WorkFrequencyMonth(6, 25),
                WorkFrequencyMonth(7, 30),
            ),
            historyAverageCurrentCount = 25,
            historyAveragePreCount = 30,
            workPartAnalyticsTargetDate = WorkPartAnalyticsTargetDate.Week,
            workPartAnalyticsTargetType = WorkPartAnalyticsTargetType.All,
            historyWorkPartCountByPre = WorkPartFrequency(),
            historyWorkPartCountByCurrent = WorkPartFrequency(),
            historyCountByPre = 30,
            historyCountByCurrent = 40,
            maxOfWorkPartFrequency = "등",
            updateWorkPartAnalyticsTargetDate = {},
            updateWorkPartAnalyticsTargetType = {},
            scrollState = rememberScrollState()
        )
    }
}
