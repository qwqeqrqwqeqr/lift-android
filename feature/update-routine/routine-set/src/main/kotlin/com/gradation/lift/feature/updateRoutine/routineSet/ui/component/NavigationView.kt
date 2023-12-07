package com.gradation.lift.feature.updateRoutine.routineSet.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.component.LiftOutlineButton
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.updateRoutine.routineSet.data.state.RoutineSetScreenState
import com.gradation.lift.model.model.routine.RoutineSetRoutine

@Composable
internal fun NavigationView(
    modifier: Modifier = Modifier,
    updateCondition: Boolean,
    currentRoutineSetRoutine: RoutineSetRoutine,
    updateRoutineSetRoutine: (RoutineSetRoutine) -> Unit,
    routineSetScreenState: RoutineSetScreenState
) {
    Row(
        modifier = modifier
            .background(LiftTheme.colorScheme.no5)
            .fillMaxWidth()
            .padding(LiftTheme.space.paddingSpace)
        ,
        horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space10),
        verticalAlignment = Alignment.Bottom
    ) {
        LiftOutlineButton(
            modifier = modifier.weight(1f),

            shape = RoundedCornerShape(size = LiftTheme.space.space12),
            onClick = { routineSetScreenState.updateDeleteDialogView(true) }
        ) {
            Text(
                text = "삭제",
                style = LiftTheme.typography.no3,
                color = LiftTheme.colorScheme.no4,
            )
        }
        LiftButton(
            modifier = modifier.weight(1f),
            enabled = updateCondition,
            onClick = { updateRoutineSetRoutine(currentRoutineSetRoutine) }
        ) {
            Text(
                text = "저장",
                style = LiftTheme.typography.no3,
                color = LiftTheme.colorScheme.no5,
            )
        }
    }
}
