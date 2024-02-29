package com.gradation.lift.designsystem.component.chart

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.card.LiftInfoCard
import com.gradation.lift.designsystem.component.chart.model.SampleData.BAR_CHART_SAMPLE_DATA
import com.gradation.lift.designsystem.component.chart.state.BarChartState
import com.gradation.lift.designsystem.component.container.LiftDefaultContainer
import com.gradation.lift.designsystem.component.container.LiftPrimaryContainer
import com.gradation.lift.designsystem.component.icon.IconBoxSize
import com.gradation.lift.designsystem.component.icon.IconType
import com.gradation.lift.designsystem.component.icon.LiftIconBox
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.minus
import kotlin.math.abs


@Composable
fun LiftBarChart(
    modifier: Modifier = Modifier,
    barChartState: BarChartState,
    sample: Boolean = false,
) {
    val isSample = sample || barChartState.workCountByMonthList.none { it.workCount != 0 }
    val state =
        if (isSample) BAR_CHART_SAMPLE_DATA else barChartState
    val maxCount = state.workCountByMonthList.maxOf { it.workCount }


    LiftDefaultContainer(
        modifier = modifier
            .fillMaxWidth(),
        horizontalPadding = LiftTheme.space.space10,
        verticalPadding = LiftTheme.space.space20
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space20)
        ) {
            if (isSample) LiftInfoCard(
                modifier = modifier,
                text = "운동을 진행하면 운동 결과에 따른 분석을 볼 수 있어요"
            )
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = LiftTheme.space.space20,
                        vertical = LiftTheme.space.space12
                    ),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom
            ) {
                state.workCountByMonthList.forEachIndexed { index, workCountByMonth ->
                    val barHeight by animateDpAsState(
                        targetValue = if (maxCount == 0) 0.dp else ((workCountByMonth.workCount.toFloat() / maxCount.toFloat()) * 180).dp,
                        label = "heightAnimation",
                        animationSpec = tween(2000)
                    )
                    Column(
                        modifier = modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space4),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = modifier
                                .background(
                                    if (state.workCountByMonthList.lastIndex == index) LiftTheme.colorScheme.no4 else Color.Transparent,
                                    RoundedCornerShape(LiftTheme.space.space4)
                                )
                                .padding(
                                    horizontal = LiftTheme.space.space4,
                                    vertical = LiftTheme.space.space2
                                )
                        ) {
                            LiftText(
                                text = if (isSample) "???" else "${workCountByMonth.workCount}회",
                                color = when (index) {
                                    state.workCountByMonthList.lastIndex - 1 -> LiftTheme.colorScheme.no26
                                    state.workCountByMonthList.lastIndex -> LiftTheme.colorScheme.no5
                                    else -> LiftTheme.colorScheme.no2
                                },
                                textAlign = TextAlign.Center,
                                textStyle = LiftTextStyle.No5
                            )
                        }
                        Spacer(
                            modifier = modifier
                                .padding(horizontal = LiftTheme.space.space8)
                                .height(barHeight)
                                .fillMaxWidth()
                                .background(
                                    color = when (index) {
                                        state.workCountByMonthList.lastIndex - 1 -> LiftTheme.colorScheme.no26
                                        state.workCountByMonthList.lastIndex -> LiftTheme.colorScheme.no4
                                        else -> LiftTheme.colorScheme.no39
                                    },
                                    RoundedCornerShape(LiftTheme.space.space6)
                                )

                        )
                        LiftText(
                            text = "${if (isSample) "?" else workCountByMonth.month.monthNumber}월",
                            color = LiftTheme.colorScheme.no9,
                            textAlign = TextAlign.Center,
                            textStyle = LiftTextStyle.No7
                        )
                    }
                }
            }
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space4),
            ) {
                SummaryContainer(
                    modifier = modifier.weight(1f),
                    title = "${if (isSample) "${barChartState.selectedDate.minus(DatePeriod(months = 2)).monthNumber}" else state.workCountByMonthList[state.workCountByMonthList.lastIndex - 2].month.monthNumber}월",
                    originData = state.workCountByMonthList.last().workCount,
                    targetData = state.workCountByMonthList[state.workCountByMonthList.lastIndex - 2].workCount,
                    isSample = isSample,
                )
                SummaryContainer(
                    modifier = modifier.weight(1f),
                    title = "${if (isSample) "${barChartState.selectedDate.minus(DatePeriod(months = 1)).monthNumber}" else state.workCountByMonthList[state.workCountByMonthList.lastIndex - 1].month.monthNumber}월",
                    originData = state.workCountByMonthList.last().workCount,
                    targetData = state.workCountByMonthList[state.workCountByMonthList.lastIndex - 1].workCount,
                    isSample = isSample,
                )
                SummaryContainer(
                    modifier = modifier.weight(1f),
                    title = "평균",
                    originData = state.workCountByMonthList.last().workCount,
                    targetData = state.workCountByMonthList.map { it.workCount }.average()
                        .toInt(),
                    isSample = isSample
                )
            }
        }
    }
}


@Composable
fun SummaryContainer(
    modifier: Modifier = Modifier,
    title: String,
    targetData: Int,
    originData: Int,
    isSample: Boolean,
) {
    LiftPrimaryContainer(
        modifier = modifier,
        horizontalPadding = LiftTheme.space.space8,
        verticalPadding = LiftTheme.space.space12,
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                LiftTheme.space.space4,
                Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LiftText(
                text = title,
                color = LiftTheme.colorScheme.no2,
                textAlign = TextAlign.Center,
                textStyle = LiftTextStyle.No5
            )
            LiftText(
                text = "${if (isSample) "?" else targetData}회",
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Center,
                textStyle = LiftTextStyle.No5
            )
            (originData - targetData).also {
                when {
                    it > 0 -> {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            LiftText(
                                text = "${if (isSample) "?" else abs(it)}",
                                color = LiftTheme.colorScheme.no4,
                                textAlign = TextAlign.Center,
                                textStyle = LiftTextStyle.No5
                            )
                            LiftIconBox(
                                icon = LiftIcon.Up,
                                iconType = IconType.Vector,
                                iconBoxSize = IconBoxSize.Size12
                            )
                        }
                    }

                    it < 0 -> {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            LiftText(
                                text = "${if (isSample) "?" else abs(it)}",
                                color = LiftTheme.colorScheme.no12,
                                textAlign = TextAlign.Center,
                                textStyle = LiftTextStyle.No5
                            )
                            LiftIconBox(
                                icon = LiftIcon.Down,
                                iconType = IconType.Vector,
                                iconBoxSize = IconBoxSize.Size12
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
@Preview
fun LiftBarChartPreview(
    modifier: Modifier = Modifier,
) {
    LiftBarChart(
        modifier,
        barChartState = BAR_CHART_SAMPLE_DATA,
    )
}