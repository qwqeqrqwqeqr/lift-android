package com.gradation.lift.feature.routine.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.routine.WeekCardUiState

@Composable
fun WeekCard(
    weekCardUiState :List<WeekCardUiState>,
    modifier:  Modifier = Modifier,
    cardViewOnClick : () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Spacer(modifier.weight(0.1f))
        weekCardUiState.forEach {
            WeekCardItem(
                modifier =modifier.weight(2f),
                selected = it.selected,
                dateText = it.day,
                weekText = it.weekDay,
                onClick = cardViewOnClick
            )
            Spacer(modifier.weight(0.1f))
        }
    }
    
    
}


@Composable
fun WeekCardItem(
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    dateText: String = "12",
    weekText: String = "월",
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .background(
                color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(onClick = onClick)
            .padding(8.dp, 16.dp, 8.dp, 16.dp)
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                color = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                text = dateText,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                color = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                text = weekText,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}


@Preview
@Composable
fun WeekCardListViewPreview() {
    LiftTheme {
        RoutineBody(
            currentDate = "12월 4일",
            weekCardUiState = listOf(
                WeekCardUiState("4", "월",true),
                WeekCardUiState("5", "화",false),
                WeekCardUiState("6", "수",false),
                WeekCardUiState("7", "목",false),
                WeekCardUiState("8", "금",false),
                WeekCardUiState("9", "토",false),
                WeekCardUiState("10", "일",false),
            )
        )
    }

}