package com.gradation.lift.designsystem.component.chart

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.card.LiftInfoCard
import com.gradation.lift.designsystem.component.chart.model.SampleData.HEXAGON_CHART_SAMPLE_DATA
import com.gradation.lift.designsystem.component.chart.model.WorkCountByMonth
import com.gradation.lift.designsystem.component.chart.model.WorkPartCountByMonth
import com.gradation.lift.designsystem.component.chart.state.HexagonChartState
import com.gradation.lift.designsystem.component.container.LiftDefaultContainer
import com.gradation.lift.designsystem.component.container.LiftEmptyContainer
import com.gradation.lift.designsystem.component.container.LiftPrimaryContainer
import com.gradation.lift.designsystem.component.icon.IconBoxSize
import com.gradation.lift.designsystem.component.icon.IconType
import com.gradation.lift.designsystem.component.icon.LiftIconBox
import com.gradation.lift.designsystem.component.text.LiftMultiStyleText
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.text.TextWithStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlin.math.cos
import kotlin.math.sin


@Composable
fun LiftHexagonChart(
    modifier: Modifier = Modifier,
    hexagonChartState: HexagonChartState,
    sample: Boolean = false,
) {
    val isSample = hexagonChartState.workCountByMonthList.none { it.workCount != 0 } || sample
    val state = if (isSample) HEXAGON_CHART_SAMPLE_DATA else hexagonChartState

    val backgroundBorderColor = LiftTheme.colorScheme.no8
    val backgroundColor = LiftTheme.colorScheme.no17

    val currentBorderColor = LiftTheme.colorScheme.no4
    val currentColor = LiftTheme.colorScheme.no4.copy(0.7f)

    val preBorderColor = LiftTheme.colorScheme.no47
    val preColor = LiftTheme.colorScheme.no47.copy(0.7f)

    val localDensity = LocalDensity.current

    val maxValue = state.workPartCountByMonthList.maxOf { it.currentCount }
        .coerceAtLeast(state.workPartCountByMonthList.maxOf { it.preCount })
        .toFloat()

    val preMonthCountValueList = state.workPartCountByMonthList.map {
        animateFloatAsState(
            targetValue = it.preCount.toFloat(),
            label = "preMonthCountAnimation",
            animationSpec = tween(2000)
        ).value
    }

    val currentMonthCountValueList = state.workPartCountByMonthList.map {
        animateFloatAsState(
            targetValue = it.currentCount.toFloat(),
            label = "currentMonthCountAnimation",
            animationSpec = tween(2000)
        ).value
    }

    LiftDefaultContainer(
        modifier = modifier,
        horizontalPadding = LiftTheme.space.space10,
        verticalPadding = LiftTheme.space.space12,
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space32),
        ) {
            if (sample) LiftInfoCard(
                modifier = modifier,
                text = "운동을 진행하면 운동결과에 따른 분석을 볼 수 있어요"
            )
            BoxWithConstraints(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = LiftTheme.space.space48)
            ) {
                val boxSize = this.maxWidth / 2
                Canvas(
                    modifier = modifier
                        .height(boxSize)
                        .width(boxSize)
                        .align(Alignment.Center),
                ) {
                    drawBackGround(
                        backgroundColor,
                        backgroundBorderColor
                    )

                    if (preMonthCountValueList.any { it != 0f })
                        draw(
                            color = preColor,
                            borderColor = preBorderColor,
                            value = preMonthCountValueList,
                            maxValue
                        )

                    if (currentMonthCountValueList.any { it != 0f })
                        draw(
                            color = currentColor,
                            borderColor = currentBorderColor,
                            value = currentMonthCountValueList,
                            maxValue
                        )
                }

                localDensity.getChartOffsetList(boxSize).forEachIndexed { index, pair ->
                    LiftWorkPartCountView(
                        modifier = modifier
                            .align(pair.first),
                        value = state.workPartCountByMonthList[index],
                        offset = pair.second,
                        isSample = isSample
                    )
                }


            }
            DescriptionContentView(
                modifier,
                state.workCountByMonthList,
                state.selectedDate,
                isSample
            )
        }
    }
}


@Composable
fun LiftWorkPartCountView(
    modifier: Modifier = Modifier,
    value: WorkPartCountByMonth,
    offset: Offset,
    isSample: Boolean,
) {
    LiftEmptyContainer(
        modifier = modifier.offset(x = offset.x.dp, y = offset.y.dp),
        verticalPadding = LiftTheme.space.space4,
        horizontalPadding = LiftTheme.space.space4,
        shape = RoundedCornerShape(size = LiftTheme.space.space4)
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space4),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LiftMultiStyleText(
                defaultColor = LiftTheme.colorScheme.no39,
                defaultTextStyle = LiftTextStyle.No5,
                textWithStyleList = listOf(
                    TextWithStyle(
                        text = "${if (isSample) "?" else value.currentCount}",
                        color = LiftTheme.colorScheme.no4,
                    ),
                    TextWithStyle(text = " / "),
                    TextWithStyle(
                        text = "${if (isSample) "?" else value.preCount}",
                        color = LiftTheme.colorScheme.no47,
                    ),
                ),
                textAlign = TextAlign.Center
            )
            LiftText(
                text = value.name,
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Center,
                textStyle = LiftTextStyle.No7
            )
        }
    }
}

fun DrawScope.draw(
    color: Color,
    borderColor: Color,
    value: List<Float>,
    maxValue: Float,
) {
    /**
     * @a 삼각함수 주기에 따른 지점을 의마함
     * @since 2024-02-19 22:51:20
     */
    val a = (2 * Math.PI) / 6

    drawPath(
        path = Path().apply {
            moveTo(
                (center.x + (value[0] / maxValue) * center.x * cos(a * 0)).toFloat(),
                (center.y + (value[0] / maxValue) * center.y * sin(a * 0)).toFloat()
            )
            repeat(6) {
                lineTo(
                    (center.x + ((value[it] / maxValue) * center.x * cos(a * it))).toFloat(),
                    (center.y + ((value[it] / maxValue) * center.y * sin(a * it))).toFloat()
                )
            }

            close()
        },
        style = Fill,
        color = color
    )
    drawPath(
        path = Path().apply {
            moveTo(
                (center.x + (value[0] / maxValue) * center.x * cos(a * 0)).toFloat(),
                (center.y + (value[0] / maxValue) * center.y * sin(a * 0)).toFloat()
            )
            repeat(6) {
                lineTo(
                    (center.x + ((value[it] / maxValue) * center.x * cos(a * it))).toFloat(),
                    (center.y + ((value[it] / maxValue) * center.y * sin(a * it))).toFloat()
                )
            }
            close()
        },
        style = Stroke(
            width = 3.5.dp.toPx(),
            cap = StrokeCap.Round,
            join = StrokeJoin.Bevel
        ),
        color = borderColor
    )
}

fun DrawScope.drawBackGround(
    backgroundColor: Color,
    backgroundBorderColor: Color,
) {
    /**
     * @a 삼각함수 주기에 따른 지점을 의마함
     * @since 2024-02-19 22:51:20
     */
    val a = (2 * Math.PI) / 6

    drawPath(
        path = Path().apply {
            moveTo(
                (center.x + center.x * cos(a * 0)).toFloat(),
                (center.y + center.y * sin(a * 0)).toFloat()
            )
            repeat(6) {
                lineTo(
                    (center.x + center.x * cos(a * it)).toFloat(),
                    (center.y + center.y * sin(a * it)).toFloat()
                )
            }

            close()
        },
        style = Fill,
        color = backgroundColor
    )
    drawPath(
        path = Path().apply {
            moveTo(
                (center.x + center.x * cos(a * 0)).toFloat(),
                (center.y + center.y * sin(a * 0)).toFloat()
            )
            repeat(6) {
                lineTo(
                    (center.x + center.x * cos(a * it)).toFloat(),
                    (center.y + center.y * sin(a * it)).toFloat()
                )
            }

            close()
        },
        style = Stroke(
            width = 2.dp.toPx(),
            cap = StrokeCap.Round,
            join = StrokeJoin.Bevel
        ),
        color = backgroundBorderColor
    )
}

@Composable
fun DescriptionContentView(
    modifier: Modifier = Modifier,
    workCountByMonthList: List<WorkCountByMonth>,
    selectedDate: LocalDate,
    isSample: Boolean,
) {
    LiftPrimaryContainer(
        modifier = modifier,
        horizontalPadding = LiftTheme.space.space16,
        verticalPadding = LiftTheme.space.space12,
        shape = RoundedCornerShape(size = LiftTheme.space.space6),
    ) {

        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            with(workCountByMonthList.last()) {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = modifier.weight(1f),
                        horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space4),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        LiftIconBox(
                            icon = LiftIcon.HexagonBlue,
                            iconType = IconType.Vector,
                            iconBoxSize = IconBoxSize.Size12
                        )
                        LiftText(
                            text = "${if (isSample) selectedDate.monthNumber else this@with.month.monthNumber}월",
                            color = LiftTheme.colorScheme.no3,
                            textAlign = TextAlign.Start,
                            textStyle = LiftTextStyle.No7
                        )
                    }
                    Row(
                        modifier = modifier.weight(1f),
                        horizontalArrangement = Arrangement.spacedBy(
                            LiftTheme.space.space4,
                            Alignment.End
                        )
                    ) {
                        LiftText(
                            text = "${if (isSample) selectedDate.monthNumber else this@with.month.monthNumber}월 운동횟수",
                            color = LiftTheme.colorScheme.no3,
                            textAlign = TextAlign.End,
                            textStyle = LiftTextStyle.No7
                        )
                        LiftText(
                            text = "${if (isSample) "?" else this@with.workCount}회",
                            color = LiftTheme.colorScheme.no3,
                            textAlign = TextAlign.End,
                            textStyle = LiftTextStyle.No8
                        )
                    }
                }
            }

            with(workCountByMonthList.first()) {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = modifier.weight(1f),
                        horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space4),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        LiftIconBox(
                            icon = LiftIcon.HexagonOrange,
                            iconType = IconType.Vector,
                            iconBoxSize = IconBoxSize.Size12
                        )
                        LiftText(
                            text = "${if (isSample) selectedDate.minus(DatePeriod(months = 1)).monthNumber else this@with.month.monthNumber}월",
                            color = LiftTheme.colorScheme.no3,
                            textAlign = TextAlign.Start,
                            textStyle = LiftTextStyle.No7
                        )
                    }
                    Row(
                        modifier = modifier.weight(1f),
                        horizontalArrangement = Arrangement.spacedBy(
                            LiftTheme.space.space4,
                            Alignment.End
                        )
                    ) {
                        LiftText(
                            text = "${if (isSample) selectedDate.minus(DatePeriod(months = 1)).monthNumber else this@with.month.monthNumber}월 운동횟수",
                            color = LiftTheme.colorScheme.no3,
                            textAlign = TextAlign.End,
                            textStyle = LiftTextStyle.No7
                        )
                        LiftText(
                            text = "${if (isSample) "?" else this@with.workCount}회",
                            color = LiftTheme.colorScheme.no3,
                            textAlign = TextAlign.End,
                            textStyle = LiftTextStyle.No8
                        )
                    }
                }
            }
        }
    }
}

private fun Density.getChartOffsetList(boxSize: Dp) =
    listOf(
        Pair(Alignment.CenterEnd, Offset(x = -24f, y = 0f)),
        Pair(
            Alignment.BottomCenter,
            Offset(
                x = (boxSize.toPx() / 12),
                y = 48f
            ),
        ),
        Pair(
            Alignment.BottomCenter,
            Offset(
                x = (-boxSize.toPx() / 12),
                y = 48f
            ),
        ),
        Pair(Alignment.CenterStart, Offset(x = 24f, y = 0f)),
        Pair(
            Alignment.TopCenter,
            Offset(
                x = (-boxSize.toPx() / 12),
                y = -48f
            ),
        ),
        Pair(
            Alignment.TopCenter,
            Offset(
                x = (boxSize.toPx() / 12),
                y = -48f
            ),
        ),
    )


@Composable
@Preview(
    name = "SAMSUNG Galaxy S20",
    device = "spec:shape=Normal,width=360,height=800,unit=dp,dpi=563"
)
@Preview(
    name = "SAMSUNG Galaxy S10",
    device = "spec:shape=Normal,width=360,height=740,unit=dp,dpi=360"
)
@Preview(name = "PIXEL_3", device = Devices.PIXEL_3)
fun LiftHexagonChartPreview(
    modifier: Modifier = Modifier,
) {

    LiftHexagonChart(
        modifier,
        HEXAGON_CHART_SAMPLE_DATA,
        false
    )
}