package com.gradation.lift.designsystem.component.chart.chart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.chart.model.BarChartItem
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun BarChart(
    modifier: Modifier = Modifier,
    height: Dp = 360.dp,
    items: List<BarChartItem>,
) {


    val primaryBarColor = LiftTheme.colorScheme.no4
    val secondaryBarColor = LiftTheme.colorScheme.no26
    val tertiaryBarColor = LiftTheme.colorScheme.no29

    val backGroundColor = LiftTheme.colorScheme.no5
    val textColor = LiftTheme.colorScheme.no9

    val highlightColor = LiftTheme.colorScheme.no9
    val highlightTextColor = LiftTheme.colorScheme.no5


    val maxValue = items.maxOf { it.y }.let { if(it>30)  (( it + 9) / 10 * 10) else 30 }
    val sortedItemList =
        items.sortedByDescending { it.y }.mapIndexed { index, it -> Pair(index, it) }


    BoxWithConstraints(
        modifier = modifier
            .height(height)
            .fillMaxWidth()
            .background(backGroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = modifier.height(this@BoxWithConstraints.maxHeight),

            ) {
//            heightBoundList.reversed().forEach {
//                Row(
//                    verticalAlignment = Alignment.Bottom,
//                    horizontalArrangement = Arrangement.spacedBy(
//                        12.dp,
//                        Alignment.CenterHorizontally
//                    ),
//                    modifier = modifier.weight(1f)
//                ) {
//                    Text(
//                        text = it.toString(),
//                        color = LiftTheme.colorScheme.no9,
//                        style = LiftTheme.typography.no7,
//                        textAlign = TextAlign.Center,
//                        modifier = modifier.width(18.dp)
//                    )
//                    Divider(
//                        thickness = strokeWidth,
//                        modifier = modifier
//                            .fillMaxWidth(),
//                        color = boundColor
//                    )
//                }
//
//            }
        }

        Row(
            modifier = modifier.height(this@BoxWithConstraints.maxHeight),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        )
        {
            Spacer(modifier = modifier.width(18.dp))
            items.forEachIndexed { index, item ->
                Column(
                    modifier = modifier.weight(1f),
                ) {
                    if (index == items.lastIndex) {
                        Surface(
                            modifier = modifier
                                .fillMaxWidth(),
                            color = highlightColor,
                            shape = RoundedCornerShape(3.dp)
                        ) {
                            Text(
                                text = item.itemName,
                                color = highlightTextColor,
                                style = LiftTheme.typography.no5,
                                textAlign = TextAlign.Center
                            )
                        }
                    } else {
                        Text(
                            text = item.itemName,
                            color = textColor,
                            style = LiftTheme.typography.no5,
                            modifier = modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                    Spacer(modifier = modifier.padding(4.dp))
                    sortedItemList.find { item.x == it.second.x }?.first.let {
                        when (it) {
                            0 -> {
                                Spacer(
                                    modifier = modifier
                                        .height(((item.y.toFloat()) / maxValue * (this@BoxWithConstraints.maxHeight.value / 2)).dp)
                                        .fillMaxWidth()
                                        .background(
                                            primaryBarColor,
                                            shape = RoundedCornerShape(6.dp, 6.dp)
                                        ),
                                )
                            }

                            in 1 until 4 -> {
                                Spacer(
                                    modifier = modifier
                                        .height(((item.y.toFloat()) / maxValue * (this@BoxWithConstraints.maxHeight.value / 2)).dp)
                                        .fillMaxWidth()
                                        .background(
                                            secondaryBarColor,
                                            shape = RoundedCornerShape(6.dp, 6.dp)
                                        ),
                                )
                            }

                            else -> {
                                Spacer(
                                    modifier = modifier
                                        .height(((item.y.toFloat()) / maxValue * (this@BoxWithConstraints.maxHeight.value / 2)).dp)
                                        .fillMaxWidth()
                                        .background(
                                            tertiaryBarColor,
                                            shape = RoundedCornerShape(6.dp, 6.dp)
                                        ),
                                )
                            }
                        }
                    }
                    Spacer(modifier = modifier.height(8.dp))
                    Text(
                        text = item.x,
                        color = textColor,
                        style = LiftTheme.typography.no7,
                        modifier = modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )

                }
            }
        }


    }
}


@Preview
@Composable
fun BarChartTestPreview() {
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
                    BarChartItem(x = "7월", y = 20, itemName = "20회"),
                    BarChartItem(x = "8월", y = 3, itemName = "3회"),
                    BarChartItem(x = "9월", y = 1, itemName = "1회"),
                    BarChartItem(x = "10월", y = 2, itemName = "2회"),
                    BarChartItem(x = "11월", y = 1, itemName = "1회"),
                    BarChartItem(x = "12월", y = 0, itemName = "0회"),
                    BarChartItem(x = "1월", y = 4, itemName = "4회"),
                )
            )
        }
    }
}