package com.gradation.lift.feature.create_routine.routine_set.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gradation.lift.ui.extensions.noRippleClickable
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.create_routine.routine_set.data.model.WeekdaySelection
import com.gradation.lift.model.model.date.Weekday


@Composable
internal fun WeekdayCardListView(
    modifier: Modifier = Modifier,
    weekdaySelectionList: List<WeekdaySelection>,
    updateRoutineSetWeekday: (Weekday) -> Unit,
) {
    Text(
        text = "요일 선택",
        style = LiftTheme.typography.no3,
        color = LiftTheme.colorScheme.no3
    )
    Text(
        text = "무슨요일에 운동을 하실건가요?",
        style = LiftTheme.typography.no6,
        color = LiftTheme.colorScheme.no2
    )
    Spacer(modifier = modifier.padding(7.dp))

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(LiftTheme.colorScheme.no5),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterHorizontally)
    ) {
        weekdaySelectionList.forEach {
            WeekdayCard(
                modifier = modifier.weight(1f),
                weekdayCard = it,
                updateRoutineSetWeekday = updateRoutineSetWeekday
            )
        }
    }
}


@Composable
private fun WeekdayCard(
    modifier: Modifier = Modifier,
    weekdayCard: WeekdaySelection,
    updateRoutineSetWeekday: (Weekday) -> Unit,
) {
    Box(
        modifier = modifier
            .noRippleClickable { updateRoutineSetWeekday(weekdayCard.weekday) }
            .background(
                color = if (weekdayCard.selected) LiftTheme.colorScheme.no4 else LiftTheme.colorScheme.no1,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(vertical = 21.dp)
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                color = if (weekdayCard.selected) LiftTheme.colorScheme.no5 else LiftTheme.colorScheme.no9,
                text = weekdayCard.weekday.getWeekdayName(),
                textAlign = TextAlign.Center,
                style = if (weekdayCard.selected) LiftTheme.typography.no3 else LiftTheme.typography.no4,
            )

        }
    }
}

