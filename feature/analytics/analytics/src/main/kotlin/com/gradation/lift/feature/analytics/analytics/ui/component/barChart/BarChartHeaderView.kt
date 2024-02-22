package com.gradation.lift.feature.analytics.analytics.ui.component.barChart

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.chart.model.WorkCountByMonth
import com.gradation.lift.designsystem.component.text.LiftMultiStyleText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.text.TextWithStyle
import com.gradation.lift.designsystem.theme.LiftTheme
import kotlin.math.abs

@Composable
fun BarChartHeaderView(
    modifier: Modifier = Modifier,
    workCountByMonthList: List<WorkCountByMonth>,
    thisMonthWorkCountForPreMonth: Int,
) {
    LiftMultiStyleText(
        modifier,
        LiftTheme.colorScheme.no9,
        LiftTextStyle.No1,
        listOf(
            TextWithStyle(
                text = "${workCountByMonthList[workCountByMonthList.lastIndex - 1].month.monthNumber}월",
                color = LiftTheme.colorScheme.no4,
            ),
            TextWithStyle(text = if (thisMonthWorkCountForPreMonth != 0) "보다 " else "이랑 동일하게 "),
            TextWithStyle(
                text = when {
                    thisMonthWorkCountForPreMonth > 0 -> "${thisMonthWorkCountForPreMonth}회 더 운동"
                    thisMonthWorkCountForPreMonth < 0 -> "${
                        abs(
                            thisMonthWorkCountForPreMonth
                        )
                    }회 덜 운동"

                    else -> "운동"
                },
                color = LiftTheme.colorScheme.no4,
            ),

            TextWithStyle(text = "했어요"),
        ),
        TextAlign.Start
    )
}