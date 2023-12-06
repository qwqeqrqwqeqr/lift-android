package com.gradation.lift.feature.update_routine.routine_set.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.component.LiftOutlineButton
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.model.routine.UpdateRoutineSetRoutine


@Composable
fun NavigationView(
    modifier: Modifier = Modifier,
    selectedRoutineSetRoutine: UpdateRoutineSetRoutine,
    navigationCondition: Boolean,
    updateRoutineSetRoutine: (UpdateRoutineSetRoutine) -> Unit,
    visibleDeleteDialog: () -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        LiftOutlineButton(
            modifier = modifier.weight(1f),
            shape = RoundedCornerShape(size = 12.dp),
            onClick = visibleDeleteDialog,
        ) {
            Text(
                text = "삭제",
                style = LiftTheme.typography.no3,
                color = LiftTheme.colorScheme.no4,
            )
        }
        LiftButton(
            modifier = modifier.weight(1f),
            onClick = { updateRoutineSetRoutine(selectedRoutineSetRoutine) },
            enabled = navigationCondition
        ) {
            Text(
                text = "저장",
                style = LiftTheme.typography.no3,
                color = LiftTheme.colorScheme.no5,
            )
        }
    }
}