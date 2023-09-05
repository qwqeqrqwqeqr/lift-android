package com.gradation.lift.designsystem.chart.chart

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.chart.model.WorkHexagonChartItem
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme

/**
 * [WorkHexagonChart]
 * 운동 부위별 루틴 사용 횟수에 따른 차트
 * @param firstItem 원본 사용 횟수 (이번주, 이번달,...)
 * @param secondItem 비교할 사용 횟수 (지난주, 지난달,...)
 * @param singleView 한개의 그래프만 보여 주는지 설정
 * @since 2023-09-05 17:15:29
 */

@Composable
fun WorkHexagonChart(
    modifier: Modifier = Modifier,
    height: Dp = 360.dp,
    firstItem: WorkHexagonChartItem,
    secondItem: WorkHexagonChartItem,
    singleView: Boolean = true
) {


    val backgroundBorderColor = LiftTheme.colorScheme.no8


    val firstBorderColor = LiftTheme.colorScheme.no4
    val firstBackgroundColor = LiftTheme.colorScheme.no4.copy(alpha = 0.3f)

    val secondBorderColor = LiftTheme.colorScheme.no24
    val secondBackgroundColor = LiftTheme.colorScheme.no24.copy(alpha = 0.3f)

    val chest = "가슴"
    val shoulder = "어깨"
    val arm = "팔"
    val back = "등"
    val lowerBody = "하체"
    val abs = "복근"


    val maxValue = if (singleView) {
        maxOf(
            firstItem.absValue,
            firstItem.armValue,
            firstItem.backValue,
            firstItem.chestValue,
            firstItem.shoulderValue,
            firstItem.lowerBodyValue
        )
    } else {
        maxOf(
            firstItem.absValue,
            firstItem.armValue,
            firstItem.backValue,
            firstItem.chestValue,
            firstItem.shoulderValue,
            firstItem.lowerBodyValue,
            secondItem.absValue,
            secondItem.armValue,
            secondItem.backValue,
            secondItem.chestValue,
            secondItem.shoulderValue,
            secondItem.lowerBodyValue
        )
    }.toFloat()



    BoxWithConstraints(
        modifier = modifier
            .height(height)
            .fillMaxWidth()
            .background(LiftTheme.colorScheme.no5),
    ) {
        Column(
            modifier.align(Alignment.TopCenter),

            ) {
            WorkContent(
                modifier = modifier,
                titleText = chest,
                firstValue = firstItem.chestValue,
                secondValue = secondItem.chestValue,
                firstColor = firstBorderColor,
                secondColor = secondBorderColor,
                singleView = singleView

            )
        }

        Column(
            modifier.align(Alignment.BottomCenter),

            ) {
            WorkContent(
                modifier = modifier,
                titleText = back,
                firstValue = firstItem.backValue,
                secondValue = secondItem.backValue,
                firstColor = firstBorderColor,
                secondColor = secondBorderColor,
                singleView = singleView
            )
        }

        Column(
            modifier.align(Alignment.CenterStart),

            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            Column {
                WorkContent(
                    modifier = modifier,
                    titleText = lowerBody,
                    firstValue = firstItem.lowerBodyValue,
                    secondValue = secondItem.lowerBodyValue,
                    firstColor = firstBorderColor,
                    secondColor = secondBorderColor,
                    sideLayout = true,
                    singleView = singleView
                )
            }
            Column {
                WorkContent(
                    modifier = modifier,
                    titleText = abs,
                    firstValue = firstItem.absValue,
                    secondValue = secondItem.absValue,
                    firstColor = firstBorderColor,
                    secondColor = secondBorderColor,
                    sideLayout = true,
                    singleView = singleView
                )
            }
        }

        Column(
            modifier.align(Alignment.CenterEnd),

            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            Column {
                WorkContent(
                    modifier = modifier,
                    titleText = arm,
                    firstValue = firstItem.armValue,
                    secondValue = secondItem.armValue,
                    firstColor = firstBorderColor,
                    secondColor = secondBorderColor,
                    sideLayout = true,
                    singleView = singleView
                )
            }
            Column {
                WorkContent(
                    modifier = modifier,
                    titleText = shoulder,
                    firstValue = firstItem.shoulderValue,
                    secondValue = secondItem.shoulderValue,
                    firstColor = firstBorderColor,
                    secondColor = secondBorderColor,
                    sideLayout = true,
                    singleView = singleView
                )
            }
        }

        Canvas(
            modifier = modifier
                .height(this@BoxWithConstraints.maxHeight / 1.5.toFloat())
                .width(this@BoxWithConstraints.maxWidth / 1.5.toFloat())
                .align(Alignment.Center)

        ) {
            val canvasWidth: Float = size.width
            val canvasHeight: Float = size.height


            val heightFirstInterval: Float = (size.height / 4)
            val heightSecondInterval: Float = (size.height / 4 * 3)


            drawBackGround(
                canvasWidth,
                canvasHeight,
                heightFirstInterval,
                heightSecondInterval,
                backgroundBorderColor
            )
            if (!singleView) {
                drawField(
                    canvasWidth,
                    canvasHeight,
                    heightFirstInterval,
                    heightSecondInterval,
                    secondBorderColor,
                    secondBackgroundColor,
                    secondItem,
                    maxValue
                )
            }
            drawField(
                canvasWidth,
                canvasHeight,
                heightFirstInterval,
                heightSecondInterval,
                firstBorderColor,
                firstBackgroundColor,
                firstItem,
                maxValue
            )


        }
    }
}


@Composable
fun WorkContent(
    modifier: Modifier = Modifier,
    titleText: String,
    firstValue: Int,
    secondValue: Int,
    firstColor: Color,
    secondColor: Color,
    sideLayout: Boolean = false,
    singleView: Boolean
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = titleText,
            color = LiftTheme.colorScheme.no9,
            style = LiftTheme.typography.no3,
            textAlign = TextAlign.Center,
            modifier = modifier.align(Alignment.CenterHorizontally)
        )
        if (sideLayout) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Spacer(
                        modifier = modifier
                            .clip(CircleShape)
                            .size(12.dp)
                            .background(firstColor)
                    )
                    Text(
                        text = "${firstValue}회",
                        color = LiftTheme.colorScheme.no9,
                        style = LiftTheme.typography.no6
                    )
                }
                if (!singleView) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Spacer(
                            modifier = modifier
                                .clip(CircleShape)
                                .size(12.dp)
                                .background(secondColor)
                        )

                        Text(
                            text = "${secondValue}회",
                            color = LiftTheme.colorScheme.no9,
                            style = LiftTheme.typography.no6
                        )
                    }
                }
            }

        } else {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Spacer(
                    modifier = modifier
                        .clip(CircleShape)
                        .size(12.dp)
                        .background(firstColor)
                )
                Text(
                    text = "${firstValue}회",
                    color = LiftTheme.colorScheme.no9,
                    style = LiftTheme.typography.no6
                )
                if (!singleView) {
                    Spacer(
                        modifier = modifier
                            .clip(CircleShape)
                            .size(12.dp)
                            .background(secondColor)
                    )
                    Text(
                        text = "${secondValue}회",
                        color = LiftTheme.colorScheme.no9,
                        style = LiftTheme.typography.no6
                    )
                }

            }
        }
    }
}

fun DrawScope.drawBackGround(
    canvasWidth: Float,
    canvasHeight: Float,
    heightFirstInterval: Float,
    heightSecondInterval: Float,
    borderColor: Color,
) {
    drawPath(
        path = Path().apply {
            moveTo(center.x, 0f)
            lineTo(canvasWidth, heightFirstInterval)
            lineTo(canvasWidth, heightSecondInterval)
            lineTo(center.x, canvasHeight)
            lineTo(0f, heightSecondInterval)
            lineTo(0f, heightFirstInterval)
            close()
        },
        style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round),
        color = borderColor
    )

    drawPath(
        path = Path().apply {
            moveTo(
                center.x - (center.x * (0.5.toFloat())),
                center.y - (center.y - heightFirstInterval) * (0.5.toFloat())
            )
            lineTo(
                center.x, center.y - (center.y * (0.5.toFloat()))
            )
            lineTo(
                (canvasWidth - center.x) + (center.x * (0.5.toFloat())),
                center.y - (center.y - heightFirstInterval) * (0.5.toFloat())
            )
            lineTo(
                (canvasWidth - center.x) + (center.x * (0.5.toFloat())),
                center.y + (heightSecondInterval - center.y) * (0.5.toFloat())
            )
            lineTo(
                center.x, (canvasHeight - center.y) + (center.y * (0.5.toFloat()))
            )

            lineTo(
                center.x - (center.x * (0.5.toFloat())),
                center.y + (heightSecondInterval - center.y) * (0.5.toFloat())
            )
            close()
        },
        style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round),
        color = borderColor
    )
}

fun DrawScope.drawField(
    canvasWidth: Float,
    canvasHeight: Float,
    heightFirstInterval: Float,
    heightSecondInterval: Float,
    borderColor: Color,
    backgroundColor: Color,
    item: WorkHexagonChartItem,
    maxValue: Float,
) {
    drawPath(
        path = Path().apply {
            moveTo(
                center.x - (center.x * (item.lowerBodyValue / maxValue)),
                center.y - (center.y - heightFirstInterval) * (item.lowerBodyValue / maxValue)
            )
            lineTo(
                center.x, center.y - (center.y * (item.chestValue / maxValue))
            )
            lineTo(
                (canvasWidth - center.x) + (center.x * (item.armValue / maxValue)),
                center.y - (center.y - heightFirstInterval) * (item.armValue / maxValue)
            )
            lineTo(
                (canvasWidth - center.x) + (center.x * (item.shoulderValue / maxValue)),
                center.y + (heightSecondInterval - center.y) * (item.shoulderValue / maxValue)
            )
            lineTo(
                center.x, (canvasHeight - center.y) + (center.y * (item.backValue / maxValue))
            )

            lineTo(
                center.x - (center.x * (item.absValue / maxValue)),
                center.y + (heightSecondInterval - center.y) * (item.absValue / maxValue)
            )
            close()
        }, style = Fill, color = backgroundColor
    )

    drawPath(
        path = Path().apply {
            moveTo(
                center.x - (center.x * (item.lowerBodyValue / maxValue)),
                center.y - (center.y - heightFirstInterval) * (item.lowerBodyValue / maxValue)
            )
            lineTo(
                center.x, center.y - (center.y * (item.chestValue / maxValue))
            )
            lineTo(
                (canvasWidth - center.x) + (center.x * (item.armValue / maxValue)),
                center.y - (center.y - heightFirstInterval) * (item.armValue / maxValue)
            )
            lineTo(
                (canvasWidth - center.x) + (center.x * (item.shoulderValue / maxValue)),
                center.y + (heightSecondInterval - center.y) * (item.shoulderValue / maxValue)
            )
            lineTo(
                center.x, (canvasHeight - center.y) + (center.y * (item.backValue / maxValue))
            )

            lineTo(
                center.x - (center.x * (item.absValue / maxValue)),
                center.y + (heightSecondInterval - center.y) * (item.absValue / maxValue)
            )
            close()
        },
        style = Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round),
        color = borderColor
    )
}


@Preview
@Composable
fun WorkHexagonChartPreview() {
    val modifier: Modifier = Modifier
    LiftMaterialTheme {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(LiftTheme.colorScheme.no5)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            WorkHexagonChart(
                modifier = modifier,
                height = 360.dp,
                firstItem = WorkHexagonChartItem(
                    chestValue = 3,
                    shoulderValue = 0,
                    armValue = 2,
                    backValue = 10,
                    lowerBodyValue = 0,
                    absValue = 0,
                ),
                secondItem = WorkHexagonChartItem(
                    chestValue = 30,
                    shoulderValue = 20,
                    armValue = 50,
                    backValue = 60,
                    lowerBodyValue = 10,
                    absValue = 40,
                )
            )
        }
    }
}