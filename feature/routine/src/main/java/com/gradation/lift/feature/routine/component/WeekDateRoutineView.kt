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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.routine.viewmodel.WeekDateRoutineUiState

@Composable
fun WeekDateRoutineView(
    weekDateRoutineUiState: WeekDateRoutineUiState,
    modifier:  Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Spacer(modifier.weight(0.1f))
        when(weekDateRoutineUiState){
            WeekDateRoutineUiState.Empty -> Text(text = "빔")
            WeekDateRoutineUiState.Error -> Text(text = "에러")
            WeekDateRoutineUiState.Loading -> Text(text = "로딩")
            is WeekDateRoutineUiState.Success -> Text(text = weekDateRoutineUiState.weekDateRoutine.weekDateRoutine.toString())
        }
    }
    
    
}


@Composable
fun WeekCardItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    dateText: String,
    weekText: String,
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
        WeekDateRoutineView(WeekDateRoutineUiState.Loading)
    }

}