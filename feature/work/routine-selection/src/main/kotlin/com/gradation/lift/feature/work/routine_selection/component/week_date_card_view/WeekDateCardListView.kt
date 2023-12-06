package com.gradation.lift.feature.work.routine_selection.component.week_date_card_view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.work.routine_selection.data.model.WeekDateSelection
import kotlinx.datetime.LocalDate

@Composable
internal fun WeekDateCardListView(
    modifier: Modifier = Modifier,
    weekDate: List<WeekDateSelection>,
    updateCurrentDate: (LocalDate) -> Unit,
) {

    Surface(color = LiftTheme.colorScheme.no5, modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterHorizontally)
        ) {
            weekDate.forEach {
                WeekDateCardView(
                    modifier = modifier.weight(1f),
                    weekday = it,
                    onClickWeekDayCard = updateCurrentDate
                )
            }
        }
    }
}