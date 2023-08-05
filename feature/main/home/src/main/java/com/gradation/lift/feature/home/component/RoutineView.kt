package com.gradation.lift.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.home.component.routine_list_view.EmptyRoutineListView
import com.gradation.lift.feature.home.component.routine_list_view.LoadingRoutineListView
import com.gradation.lift.feature.home.component.routine_list_view.RoutineListView
import com.gradation.lift.feature.home.data.WeekDate
import com.gradation.lift.feature.home.data.WeekDateRoutineUiState
import com.gradation.lift.model.routine.RoutineSetRoutine
import kotlinx.datetime.LocalDate


@Composable
internal fun RoutineView(
    modifier: Modifier = Modifier,
    today: State<LocalDate>,
    weekDateRoutineUiState: WeekDateRoutineUiState,
    weekDate: List<WeekDate>,
    onClickWeekDateCard: (LocalDate) -> Unit,
    onClickCreateRoutine: () -> Unit,
    onClickStartWorkWithRoutineSetId: (Int)->Unit,
    onClickAddRoutine: () -> Unit,
    onClickUpdateRoutine: () -> Unit,
) {
    Column(
        modifier = modifier
            .background(
                color = LiftTheme.colorScheme.no5,
                shape = RoundedCornerShape(24.dp,24.dp,0.dp,0.dp)
            )
            .padding(16.dp),
    ) {
        Text(
            text = "내 루틴 리스트",
            style = LiftTheme.typography.no1,
            color = LiftTheme.colorScheme.no9,
        )
        Text(
            text = buildAnnotatedString {
                append("오늘은 ")
                withStyle(
                    style = SpanStyle(fontWeight = FontWeight.Bold),
                ) {
                    append("${today.value.monthNumber}월 ${today.value.dayOfMonth}일")
                }
                append("이에요!")
            },
            style = LiftTheme.typography.no4,
            color = LiftTheme.colorScheme.no9,
        )
        Spacer(modifier = modifier.padding(8.dp))
        WeekDateView(weekDate = weekDate, onClickWeekDateCard = onClickWeekDateCard)

        when (weekDateRoutineUiState) {
            WeekDateRoutineUiState.Empty -> {
                EmptyRoutineListView(
                    modifier = modifier,
                    onClickCreateRoutine = onClickCreateRoutine
                )
            }
            is WeekDateRoutineUiState.Fail -> {

            }
            WeekDateRoutineUiState.Loading -> {
                LoadingRoutineListView(modifier = modifier)
            }
            is WeekDateRoutineUiState.Success -> {
                RoutineListView(
                    modifier = modifier,
                    onClickUpdateRoutine = onClickUpdateRoutine,
                    onClickAddRoutine = onClickAddRoutine,
                    onClickStartWorkWithRoutineSetId=onClickStartWorkWithRoutineSetId,
                    routineSetRoutineList = weekDateRoutineUiState.weekDateRoutine
                )
            }
        }
    }
}

