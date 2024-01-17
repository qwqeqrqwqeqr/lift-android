package com.gradation.lift.feature.analytics.analytics.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.designsystem.R
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.theme.LiftTheme
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

    when (historyUiState) {
        HistoryUiState.Empty -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(LiftTheme.colorScheme.no5),
                verticalArrangement = Arrangement.spacedBy(
                    LiftTheme.space.space8,
                    Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = modifier.size(LiftTheme.space.space96),
                    painter = painterResource(id = R.drawable.open_box),
                    contentDescription = "emptyBox",
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
                ) {
                    LiftText(
                        textStyle = LiftTextStyle.No2,
                        text = "운동을 진행 하신 적이 존재하지 않네요.",
                        color = LiftTheme.colorScheme.no2,
                        textAlign = TextAlign.Center
                    )
                    LiftText(
                        textStyle = LiftTextStyle.No4,
                        text = "운동을 완료하고 운동분석 정보를 확인해봐요",
                        color = LiftTheme.colorScheme.no2,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        is HistoryUiState.Fail -> {
            Spacer(
                modifier = modifier
                    .fillMaxSize()
                    .background(LiftTheme.colorScheme.no5)
            )
        }

        HistoryUiState.Loading -> {
            Spacer(
                modifier = modifier
                    .fillMaxSize()
                    .background(LiftTheme.colorScheme.no5)
            )
        }
        is HistoryUiState.Success -> {
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
    }



}