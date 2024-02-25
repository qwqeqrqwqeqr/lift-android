package com.gradation.lift.feature.analytics.analytics.ui.component.pieChart

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.text.LiftMultiStyleText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.text.TextWithStyle
import com.gradation.lift.designsystem.theme.LiftTheme
import kotlinx.datetime.LocalDate

@Composable
fun PieChartHeaderView(
    modifier: Modifier = Modifier,
    selectedDate: LocalDate,
    sumOfWorkCountByWorkPart: Int,
    selectedWorkPart: String,
    mostUsedWorkCategory: String,
    sample: Boolean = false,
) {
    if (!sample) HeaderTextView(
        modifier,
        selectedDate,
        sumOfWorkCountByWorkPart,
        selectedWorkPart,
        mostUsedWorkCategory,
    )
}

@Composable
private fun HeaderTextView(
    modifier: Modifier = Modifier,
    selectedDate: LocalDate,
    sumOfWorkCountByWorkPart: Int,
    selectedWorkPart: String,
    mostUsedWorkCategory: String,
) {
    if (sumOfWorkCountByWorkPart == 0)
        LiftMultiStyleText(
            modifier,
            LiftTheme.colorScheme.no9,
            LiftTextStyle.No1,
            listOf(
                TextWithStyle(
                    text = "${selectedDate.monthNumber}월에는 ",
                ),
                TextWithStyle(
                    text = "$selectedWorkPart 운동",
                    color = LiftTheme.colorScheme.no4,
                ),
                TextWithStyle("을 진행하지 않으셨어요")
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
                    text = "${mostUsedWorkCategory}\n",
                    color = LiftTheme.colorScheme.no4,
                ),
                TextWithStyle(text = "운동을 선호하시는군요"),
            ),
            TextAlign.Start
        )
}