package com.gradation.lift.designsystem.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gradation.lift.designsystem.R
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme


data class BarChartItem(
    val x: String = "",
    val y: Float = 0f,
    val itemName: String = "",
)

@Composable
fun BarChart(
    modifier: Modifier = Modifier,
    items: List<BarChartItem>,
) {

    val borderColor = LiftTheme.colorScheme.no8
    val primaryBarColor = LiftTheme.colorScheme.no4
    val secondaryBarColor = LiftTheme.colorScheme.no26
    val tertiaryBarColor = LiftTheme.colorScheme.no29
    val backGroundColor = LiftTheme.colorScheme.no5
    val highlightColor = LiftTheme.colorScheme.no9
    val textColor = LiftTheme.colorScheme.no9
    val highlightTextColor = LiftTheme.colorScheme.no5

    val density = LocalDensity.current
    val strokeWidth = with(density) { 0.5.dp.toPx() }


    val textMeasurer = rememberTextMeasurer()

    Box(
        modifier = modifier
            .height(196.dp)
            .fillMaxWidth()
            .background(LiftTheme.colorScheme.no5)
    ) {


        Canvas(modifier = modifier.fillMaxSize()) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            val yAxisIntervalList = (0..canvasHeight.toInt() step canvasHeight.toInt() / 8).toList()
            val xAxisIntervalList = (0..canvasWidth.toInt() step canvasWidth.toInt() / 8).toList()

            val barWidth = 120f

            yAxisIntervalList.dropLast(2).forEach {
                drawLine(
                    color = borderColor,
                    strokeWidth = strokeWidth,
                    start = Offset(0f, y = it.toFloat()),
                    end = Offset(canvasWidth, y = it.toFloat())
                )
            }





            xAxisIntervalList.zip(items.map {
                it.copy(y = it.y.dp.toPx() * canvasHeight / 100) }
            ).forEachIndexed { index, pair ->
                if (index == items.lastIndex) {
                    drawRoundRect(
                        color = primaryBarColor,
                        size = Size(width = barWidth, height = pair.second.y),
                        topLeft = Offset(
                            pair.first.toFloat() + barWidth,

                            canvasHeight - yAxisIntervalList.drop(2).first() - pair.second.y
                        ),
                        cornerRadius = CornerRadius(12f, 12f)
                    )
                    drawRect(
                        color = primaryBarColor,
                        size = Size(width = barWidth, height = pair.second.y / 2),
                        topLeft = Offset(
                            pair.first.toFloat() + barWidth,
                            canvasHeight - yAxisIntervalList.drop(2).first() - pair.second.y / 2
                        ),
                    )
                } else if (index % 2 == 0) {

                    drawRoundRect(
                        color = secondaryBarColor,
                        size = Size(width = barWidth, height = pair.second.y.toFloat()),
                        topLeft = Offset(
                            pair.first.toFloat() + barWidth,
                            canvasHeight - yAxisIntervalList[2] - pair.second.y
                        ),
                        cornerRadius = CornerRadius(12f, 12f)
                    )
                    drawRect(
                        color = secondaryBarColor,
                        size = Size(width = barWidth, height = pair.second.y.toFloat() / 2),
                        topLeft = Offset(
                            pair.first.toFloat() + barWidth,
                            canvasHeight - yAxisIntervalList.drop(2)
                                .first() - pair.second.y.toFloat() / 2
                        ),
                    )
                } else {
                    drawRoundRect(
                        color = tertiaryBarColor,
                        size = Size(width = barWidth, height = pair.second.y.toFloat()),
                        topLeft = Offset(
                            pair.first.toFloat() + barWidth,
                            canvasHeight - yAxisIntervalList.drop(2)
                                .first() - pair.second.y.toFloat()
                        ),
                        cornerRadius = CornerRadius(12f, 12f)
                    )
                    drawRect(
                        color = tertiaryBarColor,
                        size = Size(width = barWidth, height = pair.second.y.toFloat() / 2),
                        topLeft = Offset(
                            pair.first.toFloat() + barWidth,
                            canvasHeight - yAxisIntervalList.drop(2)
                                .first() - pair.second.y.toFloat() / 2
                        ),
                    )
                }

                if (index == items.lastIndex) {
                    drawRoundRect(
                        color = highlightColor,
                        size = Size(width = barWidth, height = 72f),
                        topLeft = Offset(
                            pair.first.toFloat() + barWidth,
                            canvasHeight - yAxisIntervalList.drop(2).first() - pair.second.y - 80f
                        ),
                        cornerRadius = CornerRadius(12f, 12f)
                    )
                    drawText(
                        textMeasurer = textMeasurer,
                        text = pair.second.itemName,
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight(1000),
                            fontFamily = FontFamily(
                                Font(
                                    R.font.pretendard_bold,
                                    FontWeight.Bold,
                                    FontStyle.Normal
                                )
                            ),
                            color = highlightTextColor,
                            textAlign = TextAlign.Center,
                        ),
                        topLeft = Offset(
                            x = pair.first.toFloat() + barWidth + barWidth / 6,
                            y = canvasHeight - yAxisIntervalList.drop(2)
                                .first() - pair.second.y - 72f
                        )
                    )
                } else {
                    drawText(
                        textMeasurer = textMeasurer,
                        text = pair.second.itemName,
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight(1000),
                            fontFamily = FontFamily(
                                Font(
                                    R.font.pretendard_bold,
                                    FontWeight.Bold,
                                    FontStyle.Normal
                                )
                            ),
                            color = textColor,
                            textAlign = TextAlign.Center,
                        ),
                        topLeft = Offset(
                            x = pair.first.toFloat() + barWidth + barWidth / 6,
                            y = canvasHeight - yAxisIntervalList.drop(2)
                                .first() - pair.second.y - 72f
                        )
                    )
                }



                drawText(
                    textMeasurer = textMeasurer,
                    text = pair.second.x,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily(
                            Font(
                                R.font.pretendard_medium,
                                FontWeight.Medium,
                                FontStyle.Normal
                            )
                        ),
                        background = backGroundColor,
                        color = textColor,
                        textAlign = TextAlign.Center,
                    ),
                    topLeft = Offset(
                        x = pair.first.toFloat() + barWidth + barWidth / 4,
                        y = canvasHeight - yAxisIntervalList.drop(2).first()
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun ChartPreview() {
    val modifier: Modifier = Modifier
    LiftMaterialTheme {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(LiftTheme.colorScheme.no5)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            BarChart(
                modifier = modifier,
                items = listOf(
                    BarChartItem(x = "7월", y = 42f, itemName = "42회"),
                    BarChartItem(x = "8월", y = 4f, itemName = "4회"),
                    BarChartItem(x = "9월", y = 64f, itemName = "64회"),
                    BarChartItem(x = "10월", y = 33f, itemName = "33회"),
                    BarChartItem(x = "11월", y = 12f, itemName = "12회"),
                    BarChartItem(x = "12월", y = 15f, itemName = "15회"),
                    BarChartItem(x = "1월", y = 23f, itemName = "23회"),
                )
            )
        }
    }
}