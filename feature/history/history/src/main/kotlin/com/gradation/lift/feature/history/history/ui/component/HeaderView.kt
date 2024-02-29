package com.gradation.lift.feature.history.history.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.history.history.data.state.HistoryScreenState
import com.gradation.lift.ui.mapper.toMonthYearText
import com.gradation.lift.ui.modifier.noRippleClickable
import kotlinx.datetime.LocalDate

@Composable
fun HeaderView(
    modifier: Modifier = Modifier,
    selectedDate: LocalDate,
    historyScreenState: HistoryScreenState,
) {
    Row(
        modifier = modifier
            .padding(
                top = LiftTheme.space.space40,
                end = LiftTheme.space.space20,
                start = LiftTheme.space.space20
            )
            .noRippleClickable {
                historyScreenState.updateDatePickerBottomSheetView(
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
}