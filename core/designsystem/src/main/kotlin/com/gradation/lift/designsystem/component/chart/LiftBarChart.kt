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
import com.gradation.lift.designsystem.component.chart.model.WorkCountByMonth
import com.gradation.lift.designsystem.component.chart.state.BarChartState
import com.gradation.lift.designsystem.component.container.LiftDefaultContainer
import com.gradation.lift.designsystem.component.container.LiftSecondaryContainer
import com.gradation.lift.designsystem.component.icon.LiftIconBox
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.theme.LiftTheme
import kotlinx.datetime.LocalDate
import kotlin.math.abs


@Composable
fun LiftBarChart(
    modifier: Modifier = Modifier,
    barChartState: BarChartState,
) {
    val maxCount = barChartState.workCountByMonthList.maxOf { it.workCount }
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
                barChartState.workCountByMonthList.forEachIndexed { index, workCountByMonth ->
                    val barHeight by animateDpAsState(
                        targetValue = ((workCountByMonth.workCount.toFloat() / maxCount.toFloat()) * 180).dp,
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
                                    if (barChartState.workCountByMonthList.lastIndex == index) LiftTheme.colorScheme.no4 else Color.Transparent,
                                    RoundedCornerShape(LiftTheme.space.space4)
                                )
                                .padding(
                                    horizontal = LiftTheme.space.space4,
                                    vertical = LiftTheme.space.space2
                                )
                        ) {
                            LiftText(
                                text = "${workCountByMonth.workCount}회",
                                color = when (index) {
                                    barChartState.workCountByMonthList.lastIndex - 1 -> LiftTheme.colorScheme.no26
                                    barChartState.workCountByMonthList.lastIndex -> LiftTheme.colorScheme.no5
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
                                        barChartState.workCountByMonthList.lastIndex - 1 -> LiftTheme.colorScheme.no26
                                        barChartState.workCountByMonthList.lastIndex -> LiftTheme.colorScheme.no4
                                        else -> LiftTheme.colorScheme.no39
                                    },
                                    RoundedCornerShape(LiftTheme.space.space6)
                                )

                        )
                        LiftText(
                            text = "${workCountByMonth.month.monthNumber}월",
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
                    title = "${barChartState.workCountByMonthList[barChartState.workCountByMonthList.lastIndex - 2].month.monthNumber}월",
                    originData = barChartState.workCountByMonthList.last().workCount,
                    targetData = barChartState.workCountByMonthList[barChartState.workCountByMonthList.lastIndex - 2].workCount,
                )
                SummaryContainer(
                    modifier = modifier.weight(1f),
                    title = "${barChartState.workCountByMonthList[barChartState.workCountByMonthList.lastIndex - 1].month.monthNumber}월",
                    originData = barChartState.workCountByMonthList.last().workCount,
                    targetData = barChartState.workCountByMonthList[barChartState.workCountByMonthList.lastIndex - 1].workCount,
                )
                SummaryContainer(
                    modifier = modifier.weight(1f),
                    title = "평균",
                    originData = barChartState.workCountByMonthList.last().workCount,
                    targetData = barChartState.workCountByMonthList.map { it.workCount }.average()
                        .toInt(),
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
) {
    LiftSecondaryContainer(
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
                text = "${targetData}회",
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
                                text = "${abs(it)}",
                                color = LiftTheme.colorScheme.no4,
                                textAlign = TextAlign.Center,
                                textStyle = LiftTextStyle.No5
                            )
                            LiftIconBox(
                                icon = com.gradation.lift.designsystem.resource.LiftIcon.Up,
                                iconType = com.gradation.lift.designsystem.component.icon.IconType.Vector,
                                iconBoxSize = com.gradation.lift.designsystem.component.icon.IconBoxSize.Size12
                            )
                        }
                    }

                    it < 0 -> {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            LiftText(
                                text = "${abs(it)}",
                                color = LiftTheme.colorScheme.no12,
                                textAlign = TextAlign.Center,
                                textStyle = LiftTextStyle.No5
                            )
                            LiftIconBox(
                                icon = com.gradation.lift.designsystem.resource.LiftIcon.Down,
                                iconType = com.gradation.lift.designsystem.component.icon.IconType.Vector,
                                iconBoxSize = com.gradation.lift.designsystem.component.icon.IconBoxSize.Size12
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
        barChartState = BarChartState(
            listOf(
                WorkCountByMonth(25, LocalDate(2023, 9, 5)),
                WorkCountByMonth(8, LocalDate(2023, 10, 5)),
                WorkCountByMonth(28, LocalDate(2023, 11, 5)),
                WorkCountByMonth(1, LocalDate(2023, 12, 5)),
                WorkCountByMonth(25, LocalDate(2024, 1, 5)),
                WorkCountByMonth(15, LocalDate(2024, 2, 5)),
            )
        )
    )
}