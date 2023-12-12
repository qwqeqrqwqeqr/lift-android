package com.gradation.lift.feature.work.complete.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.work.common.data.WorkRestTime
import com.gradation.lift.model.model.history.CreateHistoryRoutine

@Composable
fun NavigationView(
    modifier: Modifier = Modifier,
    historyWorkRestTime: WorkRestTime,
    historyRoutineList: List<CreateHistoryRoutine>,
    createHistory: (WorkRestTime, List<CreateHistoryRoutine>) -> Unit,
) {
    Column {
        LiftButton(
            modifier = modifier.fillMaxWidth(),
            onClick = { createHistory(historyWorkRestTime, historyRoutineList) },
        ) {
            Text(
                text = "완료",
                style = LiftTheme.typography.no3,
                color = LiftTheme.colorScheme.no5,
            )
        }
    }
}