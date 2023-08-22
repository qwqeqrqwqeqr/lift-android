package com.gradation.lift.feature.work.routine_selection.component.week_date_card_view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.extensions.noRippleClickable
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.work.routine_selection.data.model.WeekDateSelection
import kotlinx.datetime.LocalDate




@Composable
fun WeekDateCardView(
    modifier: Modifier = Modifier,
    weekday: WeekDateSelection,
    onClickWeekDayCard: (LocalDate) -> Unit,
) {
    Box(
        modifier = modifier
            .noRippleClickable{ onClickWeekDayCard(weekday.localDate) }
            .background(
                color = if (weekday.selected) LiftTheme.colorScheme.no4 else LiftTheme.colorScheme.no1,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(vertical = 18.dp)
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                color = if (weekday.selected) LiftTheme.colorScheme.no5 else LiftTheme.colorScheme.no9,
                text = weekday.weekday.getWeekdayName(),
                textAlign = TextAlign.Center,
                style = if (weekday.selected) LiftTheme.typography.no3 else LiftTheme.typography.no4,
            )

        }
    }
}
