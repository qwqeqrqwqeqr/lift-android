package com.gradation.lift.feature.home.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.feature.home.data.WeekDateRoutineUiState

@Composable
fun RoutineView(
    weekDateRoutineUiState: WeekDateRoutineUiState,
    modifier: Modifier = Modifier,
) {
    when(weekDateRoutineUiState){
        WeekDateRoutineUiState.Empty -> Text("empty")
        WeekDateRoutineUiState.Loading ->Text("loading")
        is WeekDateRoutineUiState.Success ->Text("${weekDateRoutineUiState.weekDateRoutine}")
        is WeekDateRoutineUiState.Fail ->  Text("error")
    }
}