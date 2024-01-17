package com.gradation.lift.feature.analytics.analytics.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.chart.chart.WorkHexagonChart
import com.gradation.lift.designsystem.component.chart.model.WorkHexagonChartItem
import com.gradation.lift.designsystem.component.chip.LiftDefaultChip
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.analytics.analytics.data.model.WorkPartAnalyticsTargetDate
import com.gradation.lift.feature.analytics.analytics.data.model.WorkPartAnalyticsTargetType
import com.gradation.lift.feature.analytics.analytics.data.model.WorkPartFrequency

@Composable
fun WorkFrequencyAnalyticsScreen(
    modifier: Modifier = Modifier,
    workPartAnalyticsTargetDate: WorkPartAnalyticsTargetDate,
    workPartAnalyticsTargetType: WorkPartAnalyticsTargetType,
    historyWorkPartCountByPre: WorkPartFrequency,
    historyWorkPartCountByCurrent: WorkPartFrequency,
    historyCountByPre: Int,
    historyCountByCurrent: Int,
    maxOfWorkPartFrequency: String,
    updateWorkPartAnalyticsTargetDate: (WorkPartAnalyticsTargetDate) -> Unit,
    updateWorkPartAnalyticsTargetType: (WorkPartAnalyticsTargetType) -> Unit
) {
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
                LiftDefaultChip(
                    modifier = modifier,
                    text = "이번주",
                    isSelected = (workPartAnalyticsTargetDate is WorkPartAnalyticsTargetDate.Week),
                    onClick = {
                        updateWorkPartAnalyticsTargetDate(
                            WorkPartAnalyticsTargetDate.Week
                        )
                    }
                )
                LiftDefaultChip(
                    modifier = modifier,
                    text = "이번달",
                    isSelected = (workPartAnalyticsTargetDate is WorkPartAnalyticsTargetDate.Month),
                    onClick = {
                        updateWorkPartAnalyticsTargetDate(
                            WorkPartAnalyticsTargetDate.Month
                        )
                    }
                )
                LiftDefaultChip(
                    modifier = modifier,
                    text = "올해",
                    isSelected = (workPartAnalyticsTargetDate is WorkPartAnalyticsTargetDate.Year),
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
                LiftDefaultChip(
                    modifier = modifier,
                    text = "전체",
                    isSelected = (workPartAnalyticsTargetType is WorkPartAnalyticsTargetType.All),
                    onClick = {
                        updateWorkPartAnalyticsTargetType(
                            WorkPartAnalyticsTargetType.All
                        )
                    }
                )
                LiftDefaultChip(
                    modifier = modifier,
                    text = when (workPartAnalyticsTargetDate) {
                        WorkPartAnalyticsTargetDate.Month -> "이번달"
                        WorkPartAnalyticsTargetDate.Week -> "이번주"
                        WorkPartAnalyticsTargetDate.Year -> "올해"
                    },
                    isSelected = (workPartAnalyticsTargetType is WorkPartAnalyticsTargetType.Current),
                    onClick = {
                        updateWorkPartAnalyticsTargetType(
                            WorkPartAnalyticsTargetType.Current
                        )
                    }
                )
                LiftDefaultChip(
                    modifier = modifier,
                    text = when (workPartAnalyticsTargetDate) {
                        WorkPartAnalyticsTargetDate.Month -> "지난달"
                        WorkPartAnalyticsTargetDate.Week -> "지난주"
                        WorkPartAnalyticsTargetDate.Year -> "작년"
                    },
                    isSelected = (workPartAnalyticsTargetType is WorkPartAnalyticsTargetType.Pre),
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
        Spacer(modifier = modifier.padding(8.dp))
    }
}