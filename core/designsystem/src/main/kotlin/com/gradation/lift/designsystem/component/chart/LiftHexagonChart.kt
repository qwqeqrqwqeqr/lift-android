package com.gradation.lift.designsystem.component.chart

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import com.gradation.lift.designsystem.component.chart.model.WorkCountByMonth
import com.gradation.lift.designsystem.component.chart.model.WorkPartCount
import com.gradation.lift.designsystem.component.chart.state.HexagonChartState
import com.gradation.lift.designsystem.component.container.LiftDefaultContainer
import com.gradation.lift.designsystem.component.container.LiftEmptyContainer
import com.gradation.lift.designsystem.component.container.LiftSecondaryContainer
import com.gradation.lift.designsystem.component.icon.IconBoxSize
import com.gradation.lift.designsystem.component.icon.IconType
import com.gradation.lift.designsystem.component.icon.LiftIconBox
import com.gradation.lift.designsystem.component.text.LiftMultiStyleText
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.text.TextWithStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import kotlinx.datetime.LocalDate
import kotlin.math.cos
import kotlin.math.sin


@Composable
fun LiftHexagonChart(
    modifier: Modifier = Modifier,
    hexagonChartState: HexagonChartState,
) {
    val backgroundBorderColor = LiftTheme.colorScheme.no8
    val backgroundColor = LiftTheme.colorScheme.no17

    val currentBorderColor = LiftTheme.colorScheme.no4
    val currentColor = LiftTheme.colorScheme.no4.copy(0.7f)

    val preBorderColor = LiftTheme.colorScheme.no47
    val preColor = LiftTheme.colorScheme.no47.copy(0.7f)

    val localDensity = LocalDensity.current

    val maxValue = hexagonChartState.workPartCountList.maxOf { it.currentCount }
        .coerceAtLeast(hexagonChartState.workPartCountList.maxOf { it.preCount }).toFloat()

    LiftDefaultContainer(
        modifier = modifier,
        horizontalPadding = LiftTheme.space.space10,
        verticalPadding = LiftTheme.space.space12,
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space32),
        ) {
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

                    draw(
                        color = preColor,
                        borderColor = preBorderColor,
                        value = hexagonChartState.workPartCountList.map { it.preCount.toFloat() },
                        maxValue
                    )

                    draw(
                        color = currentColor,
                        borderColor = currentBorderColor,
                        value = hexagonChartState.workPartCountList.map { it.currentCount.toFloat() },
                        maxValue
                    )
                }

                localDensity.getChartOffsetList(boxSize).forEachIndexed { index, pair ->
                    LiftWorkPartCountView(
                        modifier = modifier
                            .align(pair.first),
                        value = hexagonChartState.workPartCountList[index],
                        offset = pair.second
                    )
                }


            }
            DescriptionContentView(modifier, hexagonChartState.workCountByMonthList)
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
fun LiftWorkPartCountView(
    modifier: Modifier = Modifier,
    value: WorkPartCount,
    offset: Offset,
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
                        text = "${value.currentCount}",
                        color = LiftTheme.colorScheme.no4,
                    ),
                    TextWithStyle(text = " / "),
                    TextWithStyle(
                        text = "${value.preCount}",
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
) {
    LiftSecondaryContainer(
        modifier = modifier,
        horizontalPadding = LiftTheme.space.space16,
        verticalPadding = LiftTheme.space.space12,
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
                            text = "${this@with.month.monthNumber}월",
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
                            text = "${this@with.month.monthNumber}월 운동횟수",
                            color = LiftTheme.colorScheme.no3,
                            textAlign = TextAlign.End,
                            textStyle = LiftTextStyle.No7
                        )
                        LiftText(
                            text = "${this@with.workCount}회",
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
                            text = "${this@with.month.monthNumber}월",
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
                            text = "${this@with.month.monthNumber}월 운동횟수",
                            color = LiftTheme.colorScheme.no3,
                            textAlign = TextAlign.End,
                            textStyle = LiftTextStyle.No7
                        )
                        LiftText(
                            text = "${this@with.workCount}회",
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
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        LiftHexagonChart(
            modifier,
            HexagonChartState(
                workPartCountList = listOf(
                    WorkPartCount("등", 27, 8),
                    WorkPartCount("복근", 5, 13),
                    WorkPartCount("가슴", 30, 30),
                    WorkPartCount("어깨", 10, 25),
                    WorkPartCount("하체", 15, 32),
                    WorkPartCount("팔", 19, 3)
                ),
                workCountByMonthList = listOf(
                    WorkCountByMonth(12, LocalDate(2024, 1, 5)),
                    WorkCountByMonth(25, LocalDate(2024, 2, 5)),
                )
            )
        )
    }
}