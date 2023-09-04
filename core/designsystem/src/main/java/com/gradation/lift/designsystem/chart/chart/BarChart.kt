package com.gradation.lift.designsystem.chart.chart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.chart.model.BarChartItem
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun BarChart(
    modifier: Modifier = Modifier,
    items: List<BarChartItem>,
) {

    val maxHeight = 200
    val maxValue = if (items.maxOf { it.y } > 12) items.maxOf { it.y } else 12


    val primaryBarColor = LiftTheme.colorScheme.no4
    val secondaryBarColor = LiftTheme.colorScheme.no26
    val tertiaryBarColor = LiftTheme.colorScheme.no29

    val backGroundColor = LiftTheme.colorScheme.no5
    val textColor = LiftTheme.colorScheme.no9

    val highlightColor = LiftTheme.colorScheme.no9
    val highlightTextColor = LiftTheme.colorScheme.no5


    val boundColor = LiftTheme.colorScheme.no8


    val strokeWidth = 0.5.dp


    val heightBoundList = (0..maxHeight step maxHeight / 6).toList()

    val transformBarHeight: (Int) -> (Int) = { it * maxHeight / maxValue }

    val sortedItemList =
        items.sortedByDescending { it.y }.mapIndexed { index, it -> Pair(index, it) }



    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(backGroundColor),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {

        Box {
            Column(verticalArrangement = Arrangement.Bottom) {
                heightBoundList.forEach {
                    Divider(
                        thickness = strokeWidth,
                        modifier = modifier
                            .offset(y = it.dp)
                            .fillMaxWidth(),
                        color = boundColor
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                items.forEachIndexed { index, item ->
                    Column(
                        modifier = modifier.weight(1f),
                        verticalArrangement = Arrangement.Bottom
                    ) {

                        if (index == items.lastIndex) {
                            Surface(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .offset(y = (-18).dp),
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
                                    .fillMaxWidth()
                                    .offset(y = (-18).dp),
                                textAlign = TextAlign.Center
                            )
                        }
                        sortedItemList.find { item.x == it.second.x }?.first.let {
                            when (it) {
                                0 -> {
                                    Spacer(
                                        modifier = modifier
                                            .fillMaxWidth()
                                            .height((transformBarHeight(item.y)).dp)
                                            .background(
                                                primaryBarColor,
                                                shape = RoundedCornerShape(6.dp, 6.dp)
                                            ),
                                    )
                                }

                                in 1 until 4 -> {
                                    Spacer(
                                        modifier = modifier
                                            .fillMaxWidth()
                                            .height((transformBarHeight(item.y)).dp)
                                            .background(
                                                secondaryBarColor,
                                                shape = RoundedCornerShape(6.dp, 6.dp)
                                            ),
                                    )
                                }

                                else -> {
                                    Spacer(
                                        modifier = modifier
                                            .fillMaxWidth()
                                            .height((transformBarHeight(item.y)).dp)
                                            .background(
                                                tertiaryBarColor,
                                                shape = RoundedCornerShape(6.dp, 6.dp)
                                            ),
                                    )
                                }
                            }
                        }

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
}


@Preview
@Composable
fun BarChartPreview() {
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
                    BarChartItem(x = "7월", y = 15, itemName = "15회"),
                    BarChartItem(x = "8월", y = 3, itemName = "3회"),
                    BarChartItem(x = "9월", y = 1, itemName = "1회"),
                    BarChartItem(x = "10월", y = 2, itemName = "2회"),
                    BarChartItem(x = "11월", y = 1, itemName = "1회"),
                    BarChartItem(x = "12월", y = 0, itemName = "0회"),
                    BarChartItem(x = "1월", y = 1, itemName = "1회"),
                )
            )
        }
    }
}