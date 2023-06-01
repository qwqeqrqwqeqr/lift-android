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
import com.gradation.lift.feature.routine.viewmodel.WeekDate
import com.gradation.lift.feature.routine.viewmodel.WeekDateRoutineUiState


@Composable
fun WeekDateRoutineView(
    weekDateRoutineUiState: WeekDateRoutineUiState,
    modifier: Modifier = Modifier,
    weekCardClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        WeekCard(weekDateRoutineUiState = weekDateRoutineUiState, weekCardClick = weekCardClick)
    }
}


@Composable
fun WeekCard(
    modifier: Modifier = Modifier,
    weekDateRoutineUiState: WeekDateRoutineUiState,
    weekCardClick: () -> Unit
) {
    Row(modifier = modifier.fillMaxWidth()) {
        when (weekDateRoutineUiState) {
            is WeekDateRoutineUiState.Empty -> {
                WeekCardList(
                    modifier = modifier.weight(1f),
                    weekDateList = weekDateRoutineUiState.weekDateRoutine.weekDate,
                    weekCardClick = weekCardClick
                )
            }
            WeekDateRoutineUiState.Error -> {
                WeekCardList(
                    modifier = modifier.weight(1f),
                    weekDateList = emptyCardList,
                    weekCardClick = weekCardClick
                )
            }
            WeekDateRoutineUiState.Loading -> {
                WeekCardList(
                    modifier = modifier.weight(1f),
                    weekDateList = emptyCardList,
                    weekCardClick = weekCardClick
                )
            }
            is WeekDateRoutineUiState.Success -> {
                WeekCardList(
                    modifier = modifier.weight(1f),
                    weekDateList = weekDateRoutineUiState.weekDateRoutine.weekDate,
                    weekCardClick = weekCardClick
                )
            }
        }
    }
}


@Composable
fun WeekCardList(
    modifier: Modifier = Modifier,
    weekDateList: List<WeekDate>,
    weekCardClick: () -> Unit
) {
    repeat(weekDateList.size) {
        WeekCardItem(
            modifier= modifier.fillMaxWidth(),
            weekDate = weekDateList[it],
            weekCardClick = weekCardClick
        )

    }
}

@Composable
fun WeekCardItem(
    modifier: Modifier = Modifier,
    weekDate: WeekDate,
    weekCardClick: () -> Unit
) {
    Box(
        modifier = modifier
            .background(
                color = if (weekDate.selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable(onClick = weekCardClick)
            .padding(8.dp, 24.dp, 8.dp, 24.dp)
    ) {
        Column(
            modifier= modifier,
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


@Preview
@Composable
fun WeekCardListViewPreview() {
    LiftTheme {
        WeekDateRoutineView(
            weekDateRoutineUiState = WeekDateRoutineUiState.Loading,
            weekCardClick = {})
    }

}

private val emptyCardList = listOf(
    WeekDate("", "", null, false),
    WeekDate("", "", null, false),
    WeekDate("", "", null, false),
    WeekDate("", "", null, false),
    WeekDate("", "", null, false),
    WeekDate("", "", null, false),
    WeekDate("", "", null, false)
)