package com.gradation.lift.feature.home.component.weekdate_routine_view.weekdate_view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.home.data.model.WeekDateSelection
import kotlinx.datetime.LocalDate


@Composable
internal fun WeekDateCardView(
    modifier: Modifier = Modifier,
    weekDateSelectionList: List<WeekDateSelection>,
    updateSelectedDate: (LocalDate) -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterHorizontally)
    ) {
        weekDateSelectionList.forEach { weekDateSelection ->
            WeekDateCard(
                modifier = modifier.weight(1f),
                weekDateSelection = weekDateSelection,
                updateSelectedDate = updateSelectedDate
            )
        }
    }
}


@Composable
private fun WeekDateCard(
    modifier: Modifier = Modifier,
    weekDateSelection: WeekDateSelection,
    updateSelectedDate: (LocalDate) -> Unit,
) {
    Box(
        modifier = modifier
            .clickable(onClick = { updateSelectedDate(weekDateSelection.localDate) })
            .background(
                color = if (weekDateSelection.selected) LiftTheme.colorScheme.no4 else LiftTheme.colorScheme.no1,
                shape = RoundedCornerShape(8.dp)
            )
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                color = if (weekDateSelection.selected) LiftTheme.colorScheme.no5 else LiftTheme.colorScheme.no9,
                text = weekDateSelection.day,
                textAlign = TextAlign.Center,
                style = if (weekDateSelection.selected) LiftTheme.typography.no5 else LiftTheme.typography.no6,
            )
            Text(
                color = if (weekDateSelection.selected) LiftTheme.colorScheme.no5 else LiftTheme.colorScheme.no9,
                text = weekDateSelection.weekday.getWeekdayName(),
                textAlign = TextAlign.Center,
                style = if (weekDateSelection.selected) LiftTheme.typography.no5 else LiftTheme.typography.no6,
            )

        }
    }
}