package com.gradation.lift.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.home.data.WeekDate
import kotlinx.datetime.LocalDate

@Composable
internal fun WeekDateView(
    weekDate: List<WeekDate>,
    modifier: Modifier = Modifier,
    onClickWeekDateCard: (LocalDate) -> Unit,
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterHorizontally)
    ) {
        repeat(weekDate.size) {
            WeekCard(
                modifier = modifier.weight(1f),
                weekDate = weekDate[it],
                onClickWeekDateCard = onClickWeekDateCard
            )
        }
    }
}


@Composable
private fun WeekCard(
    modifier: Modifier = Modifier,
    weekDate: WeekDate,
    onClickWeekDateCard: (LocalDate) -> Unit,
) {
    Box(
        modifier = modifier
            .background(
                color = if (weekDate.selected) LiftTheme.colorScheme.no4 else LiftTheme.colorScheme.no1,
                shape = RoundedCornerShape(8.dp)
            )
            .fillMaxWidth()
            .padding(10.dp)
            .clickable(onClick = { onClickWeekDateCard(weekDate.localDate) })
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                color = if (weekDate.selected) LiftTheme.colorScheme.no5 else LiftTheme.colorScheme.no9,
                text = weekDate.day,
                textAlign = TextAlign.Center,
                style = if (weekDate.selected) LiftTheme.typography.no5 else LiftTheme.typography.no6,


                )

            Text(
                color = if (weekDate.selected) LiftTheme.colorScheme.no5 else LiftTheme.colorScheme.no9,
                text = weekDate.weekDay.getWeekdayName(),
                textAlign = TextAlign.Center,
                style = if (weekDate.selected) LiftTheme.typography.no5 else LiftTheme.typography.no6,
            )

        }
    }
}