package com.gradation.lift.feature.analytics.analytics.ui.component.hexagonChart

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.chart.model.WorkCountByMonth
import com.gradation.lift.designsystem.component.text.LiftMultiStyleText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.text.TextWithStyle
import com.gradation.lift.designsystem.theme.LiftTheme
import kotlinx.datetime.LocalDate

@Composable
fun HexagonChartHeaderView(
    modifier: Modifier = Modifier,
    selectedDate: LocalDate,
    workCountByPreCurrentMonth: List<WorkCountByMonth>,
    mostUsedWorkPartInThisMonth: String,
) {
    if (workCountByPreCurrentMonth.last().workCount == 0)
        LiftMultiStyleText(
            modifier,
            LiftTheme.colorScheme.no9,
            LiftTextStyle.No1,
            listOf(
                TextWithStyle(
                    text = "${selectedDate.monthNumber}월에는 ",
                ),
                TextWithStyle(
                    text = "운동을 진행하지 않으셨어요",
                    color = LiftTheme.colorScheme.no4,
                ),
            ),
            TextAlign.Start
        )
    else
        LiftMultiStyleText(
            modifier,
            LiftTheme.colorScheme.no9,
            LiftTextStyle.No1,
            listOf(
                TextWithStyle(
                    text = "이번달",
                    color = LiftTheme.colorScheme.no4,
                ),
                TextWithStyle(text = "은 "),
                TextWithStyle(
                    text = "${mostUsedWorkPartInThisMonth}운동",
                    color = LiftTheme.colorScheme.no4,
                ),
                TextWithStyle(text = "을 많이 했어요"),
            ),
            TextAlign.Start
        )
}