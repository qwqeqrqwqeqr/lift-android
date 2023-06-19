package com.gradation.lift.feature.routine.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gradation.lift.feature.routine.viewmodel.WeekDate
import com.gradation.lift.feature.routine.viewmodel.WeekDateUiState
import kotlinx.datetime.LocalDate

@Composable
fun WeekDateView(
    weekDateUiState: WeekDateUiState,
    modifier: Modifier = Modifier,
    weekCardClick: (LocalDate) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        WeekCard(
            weekDateUiState = weekDateUiState,
            weekCardClick = weekCardClick
        )
    }
}


@Composable
fun WeekCard(
    modifier: Modifier = Modifier,
    weekDateUiState: WeekDateUiState,
    weekCardClick: (LocalDate) -> Unit,
) {
    Row(modifier = modifier.fillMaxWidth()) {
        WeekCardList(
            modifier = modifier.weight(1f),
            weekDateList = weekDateUiState,
            weekCardClick = weekCardClick)
    }
}


@Composable
fun WeekCardList(
    modifier: Modifier = Modifier,
    weekDateList: WeekDateUiState,
    weekCardClick: (LocalDate) -> Unit,
) {
    repeat(weekDateList.weekDate.size) {
        WeekCardItem(
            modifier = modifier.fillMaxWidth(),
            weekDate = weekDateList.weekDate[it],
            weekCardClick = weekCardClick
        )
    }
}

@Composable
fun WeekCardItem(
    modifier: Modifier = Modifier,
    weekDate: WeekDate,
    weekCardClick: (LocalDate) -> Unit,
) {
    Box(
        modifier = modifier
            .background(
                color = if (weekDate.selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable(onClick = { weekCardClick(weekDate.localDate!!) })
            .padding(8.dp, 24.dp, 8.dp, 24.dp)
    ) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                color = if (weekDate.selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                text = weekDate.day,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                color = if (weekDate.selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                text = weekDate.weekDay,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}