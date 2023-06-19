package com.gradation.lift.feature.routine.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.feature.routine.viewmodel.WeekDateRoutineUiState

@Composable
fun RoutineView(
    weekDateRoutineUiState: WeekDateRoutineUiState,
    modifier: Modifier = Modifier,
) {
    when(weekDateRoutineUiState){
        WeekDateRoutineUiState.Empty -> Text("empty")
        WeekDateRoutineUiState.Error -> Text("error")
        WeekDateRoutineUiState.Loading ->Text("loading")
        is WeekDateRoutineUiState.Success ->Text("${weekDateRoutineUiState.weekDateRoutine.weekDateRoutine}")
    }
}