package com.gradation.lift.feature.analytics.analytics.ui.component.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.text.LiftMultiStyleText
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.text.TextWithStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.analytics.analytics.data.state.AnalyticsScreenState
import com.gradation.lift.ui.mapper.toMonthYearText
import com.gradation.lift.ui.modifier.noRippleClickable
import kotlinx.datetime.LocalDate

@Composable
fun CalendarHeaderView(
    modifier: Modifier = Modifier,
    selectedDate: LocalDate,
    selectedDateHistoryCount: Int,
    analyticsScreenState: AnalyticsScreenState,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16)
    ) {
        Row(
            modifier = modifier
                .noRippleClickable {
                    analyticsScreenState.updateDatePickerBottomSheetView(
                        true
                    )
                },
            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LiftText(
                textStyle = LiftTextStyle.No1,
                text = selectedDate.toMonthYearText(),
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Center
            )
            Icon(
                modifier = modifier,
                painter = painterResource(id = LiftIcon.ChevronDown),
                contentDescription = "ChevronDown",
                tint = LiftTheme.colorScheme.no9
            )
        }
        LiftMultiStyleText(
            modifier,
            LiftTheme.colorScheme.no9,
            LiftTextStyle.No1,
            listOf(
                TextWithStyle(
                    text = "${selectedDate.monthNumber}월",
                    color = LiftTheme.colorScheme.no4,
                ),
                TextWithStyle(text = "에는 "),
                TextWithStyle(
                    text = "${selectedDateHistoryCount}회 ",
                    color = LiftTheme.colorScheme.no4,
                ),
                TextWithStyle(text = "운동했어요"),
            ),
            TextAlign.Start
        )

    }
}