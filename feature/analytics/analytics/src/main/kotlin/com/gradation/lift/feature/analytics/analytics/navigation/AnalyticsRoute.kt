package com.gradation.lift.feature.analytics.analytics.navigation

import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.feature.analytics.analytics.ui.AnalyticsScreen
import com.gradation.lift.feature.analytics.analytics.data.AnalyticsViewModel
import com.gradation.lift.feature.analytics.analytics.data.model.WorkFrequencyMonth
import com.gradation.lift.feature.analytics.analytics.data.model.WorkFrequencyWeekDate
import com.gradation.lift.feature.analytics.analytics.data.model.WorkPartAnalyticsTargetDate
import com.gradation.lift.feature.analytics.analytics.data.model.WorkPartAnalyticsTargetType
import com.gradation.lift.feature.analytics.analytics.data.model.WorkPartFrequency
import com.gradation.lift.feature.analytics.analytics.data.state.HistoryUiState
import kotlinx.datetime.LocalDate

@Composable
internal fun AnalyticsRoute(
    modifier: Modifier = Modifier,
    viewModel: AnalyticsViewModel = hiltViewModel(),
) {

    val historyUiState: HistoryUiState by viewModel.historyUiState.collectAsStateWithLifecycle()


    val selectedMonth: LocalDate by viewModel.workFrequencyAnalyticsState.selectedMonth.collectAsStateWithLifecycle()
    val workFrequencyByWeek: List<WorkFrequencyWeekDate> by viewModel.workFrequencyAnalyticsState.workFrequencyByWeek.collectAsStateWithLifecycle()


    val historyCountByPreMonth: Int by viewModel.workCountByMonthAnalyticsState.historyCountByPreMonth.collectAsStateWithLifecycle()
    val historyCountByCurrentMonth: Int by viewModel.workCountByMonthAnalyticsState.historyCountByCurrentMonth.collectAsStateWithLifecycle()
    val historyCountByMonthList: List<WorkFrequencyMonth> by viewModel.workCountByMonthAnalyticsState.historyCountByMonthList.collectAsStateWithLifecycle()
    val historyAveragePreCount: Int by viewModel.workCountByMonthAnalyticsState.historyAveragePreCount.collectAsStateWithLifecycle()
    val historyAverageCurrentCount: Int by viewModel.workCountByMonthAnalyticsState.historyAverageCurrentCount.collectAsStateWithLifecycle()

    val plusSelectedMonth: () -> Unit =
        viewModel.workFrequencyAnalyticsState.plusSelectedMonth()

    val minusSelectedMonth: () -> Unit =
        viewModel.workFrequencyAnalyticsState.minusSelectedMonth()

    val workPartAnalyticsTargetDate: WorkPartAnalyticsTargetDate by viewModel.workPartAnalyticsState.workPartAnalyticsTargetDate.collectAsStateWithLifecycle()
    val workPartAnalyticsTargetType: WorkPartAnalyticsTargetType by viewModel.workPartAnalyticsState.workPartAnalyticsTargetType.collectAsStateWithLifecycle()
    val historyWorkPartCountByPre: WorkPartFrequency by viewModel.workPartAnalyticsState.historyWorkPartCountByPre.collectAsStateWithLifecycle()
    val historyWorkPartCountByCurrent: WorkPartFrequency by viewModel.workPartAnalyticsState.historyWorkPartCountByCurrent.collectAsStateWithLifecycle()
    val historyCountByPre: Int by viewModel.workPartAnalyticsState.historyCountByPre.collectAsStateWithLifecycle()
    val historyCountByCurrent: Int by viewModel.workPartAnalyticsState.historyCountByCurrent.collectAsStateWithLifecycle()
    val maxOfWorkPartFrequency: String by viewModel.workPartAnalyticsState.maxOfWorkPartFrequency.collectAsStateWithLifecycle()


    val updateWorkPartAnalyticsTargetDate: (WorkPartAnalyticsTargetDate) -> Unit =
        viewModel.workPartAnalyticsState.updateWorkPartAnalyticsTargetDate()

    val updateWorkPartAnalyticsTargetType: (WorkPartAnalyticsTargetType) -> Unit =
        viewModel.workPartAnalyticsState.updateWorkPartAnalyticsTargetType()

    val scrollState = rememberScrollState()
    AnalyticsScreen(
        modifier,
        selectedMonth,
        historyCountByCurrentMonth,
        workFrequencyByWeek,
        plusSelectedMonth,
        minusSelectedMonth,
        historyCountByPreMonth,
        historyCountByMonthList,
        historyAveragePreCount,
        historyAverageCurrentCount,
        workPartAnalyticsTargetDate,
        workPartAnalyticsTargetType,
        historyWorkPartCountByPre,
        historyWorkPartCountByCurrent,
        historyCountByPre,
        historyCountByCurrent,
        maxOfWorkPartFrequency,
        updateWorkPartAnalyticsTargetDate,
        updateWorkPartAnalyticsTargetType,
        scrollState
    )

}