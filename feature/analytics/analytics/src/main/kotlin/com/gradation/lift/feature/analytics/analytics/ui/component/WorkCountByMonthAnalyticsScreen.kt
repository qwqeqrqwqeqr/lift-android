package com.gradation.lift.feature.analytics.analytics.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.R
import com.gradation.lift.designsystem.temp.chart.chart.BarChart
import com.gradation.lift.designsystem.temp.chart.model.BarChartItem
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.analytics.analytics.data.model.WorkFrequencyMonth
import kotlin.math.abs

@Composable
fun WorkCountByMonthAnalyticsScreen(
    modifier: Modifier = Modifier,
    historyCountByCurrentMonth: Int,
    historyCountByPreMonth: Int,
    historyCountByMonthList: List<WorkFrequencyMonth>,
    historyAveragePreCount: Int,
    historyAverageCurrentCount: Int,
) {
    Column(
        modifier = modifier
            .background(LiftTheme.colorScheme.no5)
            .padding(16.dp)
            .fillMaxWidth()
    ) {

    Spacer(modifier = modifier.padding(16.dp))
        Text(
            text = buildAnnotatedString {
                if (historyCountByCurrentMonth == historyCountByPreMonth) {
                    append("지난 달이랑 동일하게 운동했어요")
                } else {
                    append("지난달 보다 ")

                    withStyle(
                        style = SpanStyle(color = LiftTheme.colorScheme.no4),
                    ) {
                        append("${abs(historyCountByCurrentMonth - historyCountByPreMonth)}회")
                    }
                    if (historyCountByCurrentMonth > historyCountByPreMonth) {
                        append(" 더 운동했어요")
                    } else {
                        append(" 덜 운동했어요")
                    }
                }
            },
            modifier = modifier.fillMaxWidth(),
            color = LiftTheme.colorScheme.no9,
            style = LiftTheme.typography.no1,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = modifier.padding(16.dp))
        if(historyCountByMonthList.isNotEmpty()){
            BarChart(
                height= 144.dp,
                items = historyCountByMonthList.map {
                    BarChartItem(
                        x= "${it.month}월",
                        y= it.frequency,
                        itemName = "${it.frequency}회"
                    )
                },
            )
        }


        Spacer(modifier = modifier.padding(16.dp))

        Box {


            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Row(
                    modifier = modifier
                        .weight(1f)
                        .border(
                            width = 1.dp,
                            color = LiftTheme.colorScheme.no8,
                            shape = RoundedCornerShape(size = 6.dp)
                        )
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "지난달",
                        color = LiftTheme.colorScheme.no11,
                        style = LiftTheme.typography.no6
                    )
                    Spacer(modifier = modifier.padding(4.dp))
                    Text(
                        text = "${historyCountByPreMonth}회",
                        color = LiftTheme.colorScheme.no9,
                        style = LiftTheme.typography.no5
                    )
                }
                Row(
                    modifier = modifier
                        .weight(1f)
                        .border(
                            width = 1.dp,
                            color = LiftTheme.colorScheme.no8,
                            shape = RoundedCornerShape(size = 6.dp)
                        )
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "이번달",
                        color = LiftTheme.colorScheme.no11,
                        style = LiftTheme.typography.no6
                    )
                    Spacer(modifier = modifier.padding(4.dp))
                    Text(
                        text = "${historyCountByCurrentMonth}회",
                        color = LiftTheme.colorScheme.no9,
                        style = LiftTheme.typography.no5
                    )
                }

                Row(
                    modifier = modifier
                        .weight(1f)
                        .border(
                            width = 1.dp,
                            color = LiftTheme.colorScheme.no8,
                            shape = RoundedCornerShape(size = 6.dp)
                        )
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "평균",
                        color = LiftTheme.colorScheme.no11,
                        style = LiftTheme.typography.no6
                    )
                    Spacer(modifier = modifier.padding(4.dp))
                    Text(
                        text = "${historyAverageCurrentCount}회",
                        color = LiftTheme.colorScheme.no9,
                        style = LiftTheme.typography.no5
                    )
                }
            }







            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Row(
                    modifier = modifier
                        .weight(1f)
                        .background(LiftTheme.colorScheme.no5)
                ) {

                }
                Row(
                    modifier = modifier
                        .weight(1f)
                        .offset(y = (-12).dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    with(historyCountByCurrentMonth - historyCountByPreMonth) {
                        if (this > 0) {
                            Text(
                                text = "${abs(this)}회 ",
                                color = LiftTheme.colorScheme.no27,
                                style = LiftTheme.typography.no4,
                                modifier = modifier.background(LiftTheme.colorScheme.no5)
                            )
                            Icon(
                                painter = painterResource(LiftIcon.IncreaseArrow),
                                contentDescription = "",
                                tint = LiftTheme.colorScheme.no27,
                                modifier = modifier.background(LiftTheme.colorScheme.no5)
                            )
                        } else if (this < 0) {
                            Text(
                                text = "${abs(this)}회 ",
                                color = LiftTheme.colorScheme.no28,
                                style = LiftTheme.typography.no4,
                                modifier = modifier.background(LiftTheme.colorScheme.no5)
                            )
                            Icon(
                                painter = painterResource(LiftIcon.DecreaseArrow),
                                contentDescription = "",
                                tint = LiftTheme.colorScheme.no28,
                                modifier = modifier.background(LiftTheme.colorScheme.no5)
                            )
                        }
                    }

                }
                Row(
                    modifier = modifier
                        .weight(1f)
                        .offset(y = (-12).dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    with(historyAverageCurrentCount - historyAveragePreCount) {
                        if (this > 0) {
                            Text(
                                text = "${abs(this)}회 ",
                                color = LiftTheme.colorScheme.no27,
                                style = LiftTheme.typography.no4,
                                modifier = modifier.background(LiftTheme.colorScheme.no5)
                            )
                            Icon(
                                painter = painterResource(LiftIcon.IncreaseArrow),
                                contentDescription = "",
                                tint = LiftTheme.colorScheme.no27,
                                modifier = modifier.background(LiftTheme.colorScheme.no5)

                            )
                        } else if (this < 0) {
                            Text(
                                text = "${abs(this)}회 ",
                                color = LiftTheme.colorScheme.no28,
                                style = LiftTheme.typography.no4,
                                modifier = modifier.background(LiftTheme.colorScheme.no5)
                            )
                            Icon(
                                painter = painterResource(LiftIcon.DecreaseArrow),
                                contentDescription = "",
                                tint = LiftTheme.colorScheme.no28,
                                modifier = modifier.background(LiftTheme.colorScheme.no5)

                            )
                        }
                    }

                }
            }
            Spacer(modifier = modifier.padding(16.dp))
        }
    }
}

