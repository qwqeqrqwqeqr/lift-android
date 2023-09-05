package com.gradation.lift.feature.history.analytics

import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.designsystem.chart.chart.WorkHexagonChart
import com.gradation.lift.designsystem.chart.model.WorkHexagonChartItem
import com.gradation.lift.designsystem.component.LiftFilterChip
import com.gradation.lift.designsystem.component.LiftOutlineFilterChip
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.history.analytics.component.WorkCountByMonthAnalyticsScreen
import com.gradation.lift.feature.history.analytics.component.WorkFrequencyAnalyticsScreen
import com.gradation.lift.feature.history.analytics.data.HistoryAnalyticsViewModel
import com.gradation.lift.feature.history.analytics.data.model.WorkPartFrequency
import com.gradation.lift.feature.history.analytics.data.model.WorkFrequencyMonth
import com.gradation.lift.feature.history.analytics.data.model.WorkFrequencyWeekDate
import com.gradation.lift.feature.history.analytics.data.model.WorkPartAnalyticsTargetDate
import com.gradation.lift.feature.history.analytics.data.model.WorkPartAnalyticsTargetType
import com.gradation.lift.feature.history.analytics.data.state.HistoryUiState
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HistoryAnalyticsRoute(
    modifier: Modifier = Modifier,
    viewModel: HistoryAnalyticsViewModel = hiltViewModel(),
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
    HistoryAnalyticsScreen(
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


@Composable
internal fun HistoryAnalyticsScreen(
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
    Surface(color = LiftTheme.colorScheme.no17, modifier = modifier.fillMaxSize()) {

        Column(
            modifier = modifier
                .padding(top = 16.dp)
                .verticalScroll(scrollState)
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

            Column(
                modifier = modifier
                    .background(LiftTheme.colorScheme.no5)
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = buildAnnotatedString {
                        when (workPartAnalyticsTargetDate) {
                            WorkPartAnalyticsTargetDate.Month -> {
                                withStyle(
                                    style = SpanStyle(color = LiftTheme.colorScheme.no4),
                                ) {
                                    append("이번달")
                                }
                                append("은")
                            }

                            WorkPartAnalyticsTargetDate.Week -> {
                                withStyle(
                                    style = SpanStyle(color = LiftTheme.colorScheme.no4),
                                ) {
                                    append("이번주")
                                }
                                append("는")
                            }

                            WorkPartAnalyticsTargetDate.Year -> {
                                withStyle(
                                    style = SpanStyle(color = LiftTheme.colorScheme.no4),
                                ) {
                                    append("올해")
                                }
                                append("는")
                            }
                        }
                        withStyle(
                            style = SpanStyle(color = LiftTheme.colorScheme.no4),
                        ) {
                            append(" $maxOfWorkPartFrequency 운동")
                        }
                        append("을 많이 했어요")
                    },
                    modifier = modifier.fillMaxWidth(),
                    color = LiftTheme.colorScheme.no9,
                    style = LiftTheme.typography.no1,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = modifier.padding(8.dp))
                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = modifier.fillMaxWidth()
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        LiftFilterChip(
                            modifier = modifier,
                            text = "이번주",
                            selected = (workPartAnalyticsTargetDate is WorkPartAnalyticsTargetDate.Week),
                            onClick = {
                                updateWorkPartAnalyticsTargetDate(
                                    WorkPartAnalyticsTargetDate.Week
                                )
                            }
                        )
                        LiftFilterChip(
                            modifier = modifier,
                            text = "이번달",
                            selected = (workPartAnalyticsTargetDate is WorkPartAnalyticsTargetDate.Month),
                            onClick = {
                                updateWorkPartAnalyticsTargetDate(
                                    WorkPartAnalyticsTargetDate.Month
                                )
                            }
                        )
                        LiftFilterChip(
                            modifier = modifier,
                            text = "올해",
                            selected = (workPartAnalyticsTargetDate is WorkPartAnalyticsTargetDate.Year),
                            onClick = {
                                updateWorkPartAnalyticsTargetDate(
                                    WorkPartAnalyticsTargetDate.Year
                                )
                            }
                        )
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = buildAnnotatedString {
                                when (workPartAnalyticsTargetDate) {
                                    WorkPartAnalyticsTargetDate.Month -> {
                                        append("이번달")
                                    }

                                    WorkPartAnalyticsTargetDate.Week -> {
                                        append("이번주")
                                    }

                                    WorkPartAnalyticsTargetDate.Year -> {
                                        append("올해")
                                    }
                                }
                                withStyle(
                                    style = SpanStyle(fontWeight = FontWeight(700)),
                                ) {
                                    append(" $historyCountByCurrent ")
                                }
                                append("회")
                            },
                            color = LiftTheme.colorScheme.no9,
                            style = LiftTheme.typography.no4,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = buildAnnotatedString {
                                when (workPartAnalyticsTargetDate) {
                                    WorkPartAnalyticsTargetDate.Month -> {
                                        append("지난달")
                                    }

                                    WorkPartAnalyticsTargetDate.Week -> {
                                        append("지난주")
                                    }

                                    WorkPartAnalyticsTargetDate.Year -> {
                                        append("작년")
                                    }
                                }
                                withStyle(
                                    style = SpanStyle(fontWeight = FontWeight(700)),
                                ) {
                                    append(" $historyCountByPre ")
                                }
                                append("회")
                            },
                            color = LiftTheme.colorScheme.no9,
                            style = LiftTheme.typography.no4,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Spacer(modifier = modifier.padding(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = modifier.fillMaxWidth()
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        LiftOutlineFilterChip(
                            modifier = modifier,
                            text = "전체",
                            selected = (workPartAnalyticsTargetType is WorkPartAnalyticsTargetType.All),
                            onClick = {
                                updateWorkPartAnalyticsTargetType(
                                    WorkPartAnalyticsTargetType.All
                                )
                            }
                        )
                        LiftOutlineFilterChip(
                            modifier = modifier,
                            text = when (workPartAnalyticsTargetDate) {
                                WorkPartAnalyticsTargetDate.Month -> "이번달"
                                WorkPartAnalyticsTargetDate.Week -> "이번주"
                                WorkPartAnalyticsTargetDate.Year -> "올해"
                            },
                            selected = (workPartAnalyticsTargetType is WorkPartAnalyticsTargetType.Current),
                            onClick = {
                                updateWorkPartAnalyticsTargetType(
                                    WorkPartAnalyticsTargetType.Current
                                )
                            }
                        )
                        LiftOutlineFilterChip(
                            modifier = modifier,
                            text = when (workPartAnalyticsTargetDate) {
                                WorkPartAnalyticsTargetDate.Month -> "지난달"
                                WorkPartAnalyticsTargetDate.Week -> "지난주"
                                WorkPartAnalyticsTargetDate.Year -> "작년"
                            },
                            selected = (workPartAnalyticsTargetType is WorkPartAnalyticsTargetType.Pre),
                            onClick = {
                                updateWorkPartAnalyticsTargetType(
                                    WorkPartAnalyticsTargetType.Pre
                                )
                            }
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        when (workPartAnalyticsTargetType) {
                            WorkPartAnalyticsTargetType.All -> {
                                Spacer(
                                    modifier = modifier
                                        .clip(CircleShape)
                                        .size(12.dp)
                                        .background(LiftTheme.colorScheme.no4)
                                )
                                Text(
                                    text = when (workPartAnalyticsTargetDate) {
                                        WorkPartAnalyticsTargetDate.Month -> "이번달"
                                        WorkPartAnalyticsTargetDate.Week -> "이번주"
                                        WorkPartAnalyticsTargetDate.Year -> "올해"
                                    },
                                    color = LiftTheme.colorScheme.no9,
                                    style = LiftTheme.typography.no6
                                )
                                Spacer(
                                    modifier = modifier
                                        .clip(CircleShape)
                                        .size(12.dp)
                                        .background(LiftTheme.colorScheme.no24)
                                )
                                Text(
                                    text = when (workPartAnalyticsTargetDate) {
                                        WorkPartAnalyticsTargetDate.Month -> "지난달"
                                        WorkPartAnalyticsTargetDate.Week -> "지난주"
                                        WorkPartAnalyticsTargetDate.Year -> "작년"
                                    },
                                    color = LiftTheme.colorScheme.no9,
                                    style = LiftTheme.typography.no6
                                )
                            }

                            WorkPartAnalyticsTargetType.Current -> {
                                Spacer(
                                    modifier = modifier
                                        .clip(CircleShape)
                                        .size(12.dp)
                                        .background(LiftTheme.colorScheme.no4)
                                )
                                Text(
                                    text = when (workPartAnalyticsTargetDate) {
                                        WorkPartAnalyticsTargetDate.Month -> "이번달"
                                        WorkPartAnalyticsTargetDate.Week -> "이번주"
                                        WorkPartAnalyticsTargetDate.Year -> "올해"
                                    },
                                    color = LiftTheme.colorScheme.no9,
                                    style = LiftTheme.typography.no6
                                )
                            }

                            WorkPartAnalyticsTargetType.Pre -> {
                                Spacer(
                                    modifier = modifier
                                        .clip(CircleShape)
                                        .size(12.dp)
                                        .background(LiftTheme.colorScheme.no4)
                                )
                                Text(
                                    text = when (workPartAnalyticsTargetDate) {
                                        WorkPartAnalyticsTargetDate.Month -> "지난달"
                                        WorkPartAnalyticsTargetDate.Week -> "지난주"
                                        WorkPartAnalyticsTargetDate.Year -> "작년"
                                    },
                                    color = LiftTheme.colorScheme.no9,
                                    style = LiftTheme.typography.no6
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = modifier.padding(8.dp))

                Column {
                    val workHexagonChartItemPre = WorkHexagonChartItem(
                        chestValue = historyWorkPartCountByPre.chestFrequency,
                        shoulderValue = historyWorkPartCountByPre.shoulderFrequency,
                        armValue = historyWorkPartCountByPre.armFrequency,
                        backValue = historyWorkPartCountByPre.backFrequency,
                        lowerBodyValue = historyWorkPartCountByPre.lowerBodyFrequency,
                        absValue = historyWorkPartCountByPre.absFrequency,
                    )

                    val workHexagonChartItemCurrent = WorkHexagonChartItem(
                        chestValue = historyWorkPartCountByCurrent.chestFrequency,
                        shoulderValue = historyWorkPartCountByCurrent.shoulderFrequency,
                        armValue = historyWorkPartCountByCurrent.armFrequency,
                        backValue = historyWorkPartCountByCurrent.backFrequency,
                        lowerBodyValue = historyWorkPartCountByCurrent.lowerBodyFrequency,
                        absValue = historyWorkPartCountByCurrent.absFrequency,
                    )

                    when (workPartAnalyticsTargetType) {
                        WorkPartAnalyticsTargetType.All -> {
                            WorkHexagonChart(
                                firstItem = workHexagonChartItemCurrent,
                                secondItem = workHexagonChartItemPre,
                                singleView = false
                            )
                        }

                        WorkPartAnalyticsTargetType.Current -> {
                            WorkHexagonChart(
                                firstItem = workHexagonChartItemCurrent,
                                secondItem = workHexagonChartItemCurrent,
                                singleView = true
                            )
                        }

                        WorkPartAnalyticsTargetType.Pre -> {
                            WorkHexagonChart(
                                firstItem = workHexagonChartItemPre,
                                secondItem = workHexagonChartItemPre,
                                singleView = true
                            )
                        }
                    }
                }

            }
        }
    }
}


@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    device = "spec:width=1080px,height=4800px,dpi=440"
)
@Composable
fun HistoryAnalyticsScreenPreview() {
    LiftMaterialTheme {
        HistoryAnalyticsScreen(
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
