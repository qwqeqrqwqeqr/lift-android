package com.gradation.lift.feature.ready_work.selection.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.ready_work.selection.ReadyWorkSelectionScreen
import com.gradation.lift.feature.ready_work.selection.data.WeekdayCard
import com.gradation.lift.model.common.Weekday

@Composable
internal fun WeekdayView(
    weekday: List<WeekdayCard>,
    modifier: Modifier = Modifier,
    onClickWeekDayCard: (Weekday) -> Unit,
) {

    Row(
        modifier = modifier.fillMaxWidth().background(LiftTheme.colorScheme.no5).padding(vertical = 10.dp, horizontal = 20.dp),
        verticalAlignment=Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterHorizontally)
    ) {
        repeat(weekday.size) {
            WeekdayCard(
                modifier = modifier.weight(1f),
                weekday = weekday[it],
                onClickWeekDayCard = onClickWeekDayCard
            )
        }
    }
}


@Composable
private fun WeekdayCard(
    modifier: Modifier = Modifier,
    weekday: WeekdayCard,
    onClickWeekDayCard: (Weekday) -> Unit,
) {
    Box(
        modifier = modifier
            .background(
                color = if (weekday.selected) LiftTheme.colorScheme.no4 else LiftTheme.colorScheme.no1,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(vertical = 18.dp)
            .clickable(onClick = { onClickWeekDayCard(weekday.weekday) })
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

@Composable
@Preview
fun ReadyWorkSelectionPreview() {
    LiftMaterialTheme {
        ReadyWorkSelectionScreen(
            modifier = Modifier,
            weekday = listOf(
                WeekdayCard(weekday = Weekday.Monday()),
                WeekdayCard(weekday = Weekday.Tuesday()),
                WeekdayCard(weekday = Weekday.Wednesday()),
                WeekdayCard(weekday = Weekday.Thursday()),
                WeekdayCard(weekday = Weekday.Friday()),
                WeekdayCard(weekday = Weekday.Saturday()),
                WeekdayCard(weekday = Weekday.Sunday(),selected = true)
            ),
            onBackClickTopBar = {},
            onClickWeekDayCard = {}
        )

    }
}